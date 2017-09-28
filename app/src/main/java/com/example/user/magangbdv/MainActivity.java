package com.example.user.magangbdv;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.magangbdv.data.EmailData;
import com.example.user.magangbdv.data.EmailData_Table;
import com.example.user.magangbdv.data.MemberModel;
import com.raizlabs.android.dbflow.sql.language.Select;

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

    private ProgressBar progressBar;

    private Button btnCheckEmail;

    public static MemberModel member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //menyembunyikan checkUI
        setViewCheckedEmail(View.GONE,false);

        progressBar = (ProgressBar)findViewById(R.id.progress);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

        TextView txtSign = (TextView)findViewById(R.id.txt_signup);
        txtSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        //inisialisasi dan eksekusi Button check Email
        btnCheckEmail = (Button)findViewById(R.id.btn_check);
        btnCheckEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //harus di cek kalo misalkan emailnya terdaftar maka munculkan tentang
                //dia berhak mendapatkan fasilitas BDV, jika tidak maka memunculkan
                //arahan untuk registrasi member BDV


                edtCheckEmail = (EditText)findViewById(R.id.check_email);
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(edtCheckEmail.getWindowToken(), 0);
                if (TextUtils.isEmpty(edtCheckEmail.getText())){
                    Snackbar.make(view,"Email doesn't exist", BaseTransientBottomBar.LENGTH_SHORT)
                            .setAction("Action",null).show();
                }else{
                    //get email from user
                    email = edtCheckEmail.getText().toString();

                    edtCheckEmail.setText("");

                    if (isEmailValid(email)){
                        //check apakah email sudah terdaftar member atau belum
                        progressBar.setVisibility(View.VISIBLE);
                        btnCheckEmail.setVisibility(View.GONE);
                        checkRegistered(email);

                    }else{
                        Snackbar.make(view,"Email doesn't valid", BaseTransientBottomBar.LENGTH_SHORT)
                                .setAction("Action",null).show();
                    }
                }
            }

        });

        edtCheckEmail = (EditText)findViewById(R.id.check_email);
        checkDatabaseEmal(edtCheckEmail);
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(edtCheckEmail.getWindowToken(), 0);

    }

    private void checkDatabaseEmal(EditText edtCheckEmail) {
        EmailData data = new Select()
                .from(EmailData.class)
                .where(EmailData_Table.id.is(1))
                .querySingle();
        if (data != null){
            edtCheckEmail.setText(data.getEmail());
        }
    }

    /*
    metode yang mengecek apakah email yang di input termasuk member BDV
     */
    private void checkRegistered(final String email) {
        EmailAPI service = MemberHelper.client().create(EmailAPI.class);
        Call<MemberModel> call = service.getMahasiswaList("full",email);
        final Intent intent = new Intent(getApplicationContext(), NewsEventActivity.class);
        call.enqueue(new Callback<MemberModel>() {
            @Override
            public void onResponse(Call<MemberModel> call, Response<MemberModel> response) {
                member = response.body();

                if (member.getStatusCode().equals("error")){
                    setViewCheckedEmail(View.VISIBLE,false);
                }/*else if (member.getUserList().get(0).getActive().equals("1")){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "email anda belum terverifikasi", Toast.LENGTH_SHORT).show();
                }*/else{
                    intent.putExtra("emailMember",email);
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);

                    EmailData data = new EmailData();
                    data.setId(1);
                    data.setEmail(email);
                    data.save();
                }

                progressBar.setVisibility(View.GONE);
                btnCheckEmail.setVisibility(View.VISIBLE);


            }

            @Override
            public void onFailure(Call<MemberModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "can't load data", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                btnCheckEmail.setVisibility(View.VISIBLE);
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
