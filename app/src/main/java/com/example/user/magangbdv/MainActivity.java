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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //variabel untuk mengecek apakah layout checked Email sudah tampil
    boolean cekCheckedEmail = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Check Member");

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

                EditText edtCheckEmail = (EditText)findViewById(R.id.check_email);
                if (TextUtils.isEmpty(edtCheckEmail.getText())){
                    Snackbar.make(view,"Email doesn't exist", BaseTransientBottomBar.LENGTH_SHORT)
                            .setAction("Action",null).show();
                }else{
                    //get email from user
                    String email = edtCheckEmail.getText().toString();

                    edtCheckEmail.setText("");

                    if (isEmailValid(email)){
                        //check apakah email sudah terdaftar member atau belum
                        boolean cekSudahTerdaftar = checkRegistered(email);

                        //setViewsetelah di cek emailnya
                        setViewCheckedEmail(View.VISIBLE,cekSudahTerdaftar);
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
    private boolean checkRegistered(String email) {

        if (email.equals("benar@gmail.com")){
            return true;
        }

        return false;
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
                        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
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
