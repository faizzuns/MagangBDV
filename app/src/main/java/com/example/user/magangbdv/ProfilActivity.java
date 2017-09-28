package com.example.user.magangbdv;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.magangbdv.data.EmailData;
import com.example.user.magangbdv.data.MemberModel;
import com.example.user.magangbdv.data.UserList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {

    String email;

    @BindView(R.id.nama_profil)
    TextView txtNama;
    @BindView(R.id.email_profil) TextView txtEmail;
    @BindView(R.id.nomor_profil) TextView txtNomor;
    @BindView(R.id.tgl_lahir_profil) TextView txtLahir;
    @BindView(R.id.lokasi_profil) TextView txtLokasi;
    @BindView(R.id.gender_profil) TextView txtGender;
    @BindView(R.id.profesi_profil) TextView txtProfesi;
    @BindView(R.id.institusi_profil) TextView txtInstitusi;
    @BindView(R.id.keahlian_profil) TextView txtKeahlian;
    @BindView(R.id.ig_profil) TextView txtInstagram;
    @BindView(R.id.fb_profil) TextView txtFacebook;
    @BindView(R.id.linkedin_profil) TextView txtLinkedin;

    public void setFont(){
        Typeface typface= Typeface.createFromAsset(getAssets(),"fonts/asap_regular.ttf");

        txtNama.setTypeface(typface);
        txtEmail.setTypeface(typface);
        txtNomor.setTypeface(typface);
        txtLahir.setTypeface(typface);
        txtLokasi.setTypeface(typface);
        txtGender.setTypeface(typface);
        txtProfesi.setTypeface(typface);
        txtInstitusi.setTypeface(typface);
        txtKeahlian.setTypeface(typface);
        txtInstagram.setTypeface(typface);
        txtFacebook.setTypeface(typface);
        txtLinkedin.setTypeface(typface);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");
        email = getIntent().getStringExtra("email");

        ButterKnife.bind(this);
        setFont();

        makeProfil(MainActivity.member.getUserList().get(0));


    }

    private void makeProfil(UserList user) {
        txtNama.setText(user.getNama());
        txtEmail.setText(user.getEmail());
        txtNomor.setText(user.getNoHp());
        String lahir = setTglLahir(user.getTglLahir());
        txtLahir.setText(lahir);
        txtLokasi.setText(user.getKota());
        txtGender.setText(user.getGender());
        txtProfesi.setText(user.getProfesi());
        txtInstitusi.setText(user.getPerusahaan());
        txtKeahlian.setText(user.getKeahlian());

        if (user.getInstagram().equals("")){
            txtInstagram.setText("_");
        }else{
            txtInstagram.setText(user.getInstagram());
        }

        if (user.getFacebook().equals("")){
            txtFacebook.setText("_");
        }else{
            txtFacebook.setText(user.getFacebook());
        }

        if (user.getLinkedln().equals("")){
            txtLinkedin.setText("-");
        }else{
            txtLinkedin.setText(user.getLinkedln());
        }
    }

    private String setTglLahir(String tglLahir) {
        String edt ;

        String tahun = tglLahir.substring(0,4);
        String bulan = tglLahir.substring(5,7);
        String day = tglLahir.substring(8,10);

        int bln = 0;
        try {
            bln = Integer.parseInt(bulan);
        }catch (NumberFormatException e){

        }
        bln--;

        edt = day + " " + getMonth(bln) + " "+ tahun;

        return edt;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            goToNewsEvent();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        goToNewsEvent();
    }

    private void goToNewsEvent(){
        Intent intent = new Intent(getApplicationContext(),NewsEventActivity.class);
        intent.putExtra("stat",1);
        intent.putExtra("emailMember",email);
        startActivity(intent);
        finish();
    }
    private String getMonth(int startMonth) {
        String month;

        switch (startMonth){
            case 0:
                month = "Januari";
                break;
            case 1:
                month = "Februari";
                break;
            case 2:
                month = "Maret";
                break;
            case 3:
                month = "April";
                break;
            case 4:
                month = "Mei";
                break;
            case 5:
                month = "Juni";
                break;
            case 6:
                month = "Juli";
                break;
            case 7:
                month = "Agustus";
                break;
            case 8:
                month = "September";
                break;
            case 9:
                month = "Oktober";
                break;
            case 10:
                month ="November";
                break;
            case 11:
                month ="Desember";
                break;
            default:
                month = "";
                break;
        }

        return month;
    }
}
