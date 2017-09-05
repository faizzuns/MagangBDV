package com.example.user.magangbdv;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.magangbdv.data.MemberModel;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //variabel untuk mengecek apakah layout checked Email sudah tampil
    boolean cekCheckedEmail = false;

    private EditText edtCheckEmail;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //menyembunyikan checkUI
        setViewCheckedEmail(View.GONE,false);

        //inisialisasi dan eksekusi Button check Email
        Button btnCheckEmail = (Button)findViewById(R.id.btn_check);
        btnCheckEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //harus di cek kalo misalkan emailnya terdaftar maka munculkan tentang
                //dia berhak mendapatkan fasilitas BDV, jika tidak maka memunculkan
                //arahan untuk registrasi member BDV

                edtCheckEmail = (EditText)findViewById(R.id.check_email);
                if (TextUtils.isEmpty(edtCheckEmail.getText())){
                    Snackbar.make(view,"Email doesn't exist", BaseTransientBottomBar.LENGTH_SHORT)
                            .setAction("Action",null).show();
                }else{
                    //get email from user
                    email = edtCheckEmail.getText().toString();

                    edtCheckEmail.setText("");

                    if (isEmailValid(email)){
                        //check apakah email sudah terdaftar member atau belum
                        checkRegistered(email);

                    }else{
                        Snackbar.make(view,"Email doesn't valid", BaseTransientBottomBar.LENGTH_SHORT)
                                .setAction("Action",null).show();
                    }
                }
            }

        });

    }

    /*
    metode yang mengecek apakah email yang di input termasuk member BDV
     */
    private void checkRegistered(String email) {
        EmailAPI service = MemberHelper.client().create(EmailAPI.class);
        Call<MemberModel> call = service.getMahasiswaList(email);
        call.enqueue(new Callback<MemberModel>() {
            @Override
            public void onResponse(Call<MemberModel> call, Response<MemberModel> response) {
                MemberModel member = response.body();

                if (member.getStatusCode().equals("error")){
                    setViewCheckedEmail(View.VISIBLE,false);
                }else{
                    setViewCheckedEmail(View.VISIBLE,true);
                }
            }

            @Override
            public void onFailure(Call<MemberModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "can't load data", Toast.LENGTH_SHORT).show();
            }
        });

    }


    /*
    metode ini dipanggil untuk menset tampilan
    tampilan checked email ketika pengguna meng-klik Button "Check!"
     */
    private void setViewCheckedEmail(int v, final boolean cekEmailRegistered) {
        final RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.background_yes);
        relativeLayout.setVisibility(v);
        cekCheckedEmail = false;
        if (v == View.VISIBLE){
            cekCheckedEmail = true;
            final Button btnValidasi = (Button)findViewById(R.id.btn_validasi_checked);
            TextView txtValidasi = (TextView)findViewById(R.id.txt_detail_check_email);
            if (cekEmailRegistered){
                btnValidasi.setText(R.string.ok);
                txtValidasi.setText(R.string.detail_registered);
            }else{
                btnValidasi.setText(R.string.register);
                txtValidasi.setText(R.string.detail_notRegistered);
            }
            btnValidasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!cekEmailRegistered){
                        Bundle args = new Bundle();
                        args.putString("email", email);
                        Log.d("TEST-A", email);

                        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                        intent.putExtras(args);
                        startActivity(intent);
                    }
                    setViewCheckedEmail(View.GONE,cekEmailRegistered);
                }
            });
        }
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();}

    @Override
    public void onBackPressed() {

        //ketika layout checked Email tampi maka ketika user menekan tombol
        //back akan tetap pada aplikasi tersebut
        if (cekCheckedEmail){
            setViewCheckedEmail(View.GONE,false);
            return;
        }

        super.onBackPressed();
    }
}
