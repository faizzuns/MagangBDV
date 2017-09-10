package com.example.user.magangbdv;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.magangbdv.data.Date;
import com.example.user.magangbdv.data.Member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Calendar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends AppCompatActivity {

    private final String URL_ROOT = "http://member.bandungdigitalvalley.com/";

    //variabel form
    EditText formNama
            ,formEmail
            ,formPassword
            ,formConfirmPassword
            ,formKota
            ,formNomorHandphone
            ,formNamaPerusahaan
            ,formKeahlianOther
            ,formInstagram
            ,formLinkedIn
            ,formFacebook;
    RadioGroup formRadioGroupGender
            ,formRadioGroupProfesi;
    Spinner formSpinnerKeahlian;
    LinearLayout layoutSosmed;
    TextView formTanggalLahir
            ,ketTanggalLahir
            ,ketGender
            ,ketNmrTlp
            ,ketProfesi
            ,ketKeahlian;
    View viewTanggalLahir;
    int indexSpinner;
    String gender = "Pria";
    String profesi = "Student";
    Date tanggalLahir;
    boolean sudahIsiTanggalLahir = false;

    //variabel button submit
    Button btnSubmitRegister;


    /*
    inisialisasi variabel yang berada di form tersebut
     */
    private void inisialisasiForm(){

        //yang pertama muncul adalah form nama,email,pass, dan confirm pass
        formNama = (EditText)findViewById(R.id.edt_nama);
        formEmail = (EditText)findViewById(R.id.edt_email);
        formPassword = (EditText)findViewById(R.id.edt_password);
        formConfirmPassword = (EditText)findViewById(R.id.edt_confirm_password);

        //dibawah ini munculnya ga langsung
        formTanggalLahir = (TextView)findViewById(R.id.edt_tanggal_lahir);
        formTanggalLahir.setVisibility(View.GONE);
        ketTanggalLahir = (TextView)findViewById(R.id.ket_tgl_lahir);
        ketTanggalLahir.setVisibility(View.GONE);
        viewTanggalLahir = (View)findViewById(R.id.view1);
        viewTanggalLahir.setVisibility(View.GONE);
        formKota = (EditText)findViewById(R.id.edt_kota);
        formKota.setVisibility(View.GONE);
        formNomorHandphone = (EditText)findViewById(R.id.edt_nomor_hp);
        formNomorHandphone.setVisibility(View.GONE);
        ketNmrTlp = (TextView)findViewById(R.id.ket_nmrtlp);
        ketNmrTlp.setVisibility(View.GONE);
        formNamaPerusahaan = (EditText)findViewById(R.id.edt_nama_perusahaan);
        formNamaPerusahaan.setVisibility(View.GONE);
        formKeahlianOther = (EditText)findViewById(R.id.ed_keahlian_other);
        formKeahlianOther.setVisibility(View.GONE);
        formInstagram = (EditText)findViewById(R.id.edt_sosmed_ig);
        formLinkedIn = (EditText)findViewById(R.id.edt_sosmed_linkedin);
        formFacebook = (EditText)findViewById(R.id.edt_sosmed_fb);
        layoutSosmed = (LinearLayout)findViewById(R.id.linear_sosmed);
        layoutSosmed.setVisibility(View.GONE);
        formRadioGroupGender = (RadioGroup)findViewById(R.id.radio_grup_gender);
        formRadioGroupGender.setVisibility(View.GONE);
        ketGender = (TextView)findViewById(R.id.ket_gender);
        ketGender.setVisibility(View.GONE);
        formRadioGroupProfesi = (RadioGroup)findViewById(R.id.radio_grup_profesi);
        formRadioGroupProfesi.setVisibility(View.GONE);
        ketProfesi = (TextView)findViewById(R.id.ket_profesi);
        ketProfesi.setVisibility(View.GONE);
        formSpinnerKeahlian = (Spinner)findViewById(R.id.spinner_keahlian);
        formSpinnerKeahlian.setVisibility(View.GONE);
        ketKeahlian = (TextView)findViewById(R.id.ket_keahlian);
        ketKeahlian.setVisibility(View.GONE);
        btnSubmitRegister = (Button)findViewById(R.id.btn_submit_register);
        btnSubmitRegister.setVisibility(View.GONE);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("email")) {
            formEmail.setText(getIntent().getExtras().getString("email"));
            Log.d("TEST", getIntent().getExtras().getString("email"));
        }

        //ketika confirm password terpencet, tanggal lahir muncul
        formConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                formTanggalLahir.setVisibility(View.VISIBLE);
                ketTanggalLahir.setVisibility(View.VISIBLE);
                viewTanggalLahir.setVisibility(View.VISIBLE);
                ketGender.setVisibility(View.VISIBLE);
                formRadioGroupGender.setVisibility(View.VISIBLE);
                formKota.setVisibility(View.VISIBLE);
            }
        });

        //ketika kota di klik, maka nomor HP keluar
        formKota.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                formNomorHandphone.setVisibility(View.VISIBLE);
                ketNmrTlp.setVisibility(View.VISIBLE);
            }
        });

        //ketika nomor HP di klik , profesi dan nama perusahaan muncul
        formNomorHandphone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                ketProfesi.setVisibility(View.VISIBLE);
                formRadioGroupProfesi.setVisibility(View.VISIBLE);
                formNamaPerusahaan.setVisibility(View.VISIBLE);
            }
        });

        //nama perusahaan di klik, maka keahlian ditampilkan dan sosmoed
        formNamaPerusahaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                formSpinnerKeahlian.setVisibility(View.VISIBLE);
                ketKeahlian.setVisibility(View.VISIBLE);
                layoutSosmed.setVisibility(View.VISIBLE);
                btnSubmitRegister.setVisibility(View.VISIBLE);
            }
        });

        //upload data Spinner
        UploadSpinner();
        formSpinnerKeahlian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("spinnerValue", "onItemSelected: "+i);
                indexSpinner = i;

                if (i != 7){
                    formKeahlianOther.setVisibility(View.GONE);
                }else{
                    formKeahlianOther.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //mengeluarkan calendar ketika di klik
        formTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
            }
        });

        //pemilihan gender;
        formRadioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.radio_button_pria){
                    gender = "Pria";
                }else{
                    gender = "Wanita";
                }
            }
        });

        //pemilihan profesi
        formRadioGroupProfesi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.radio_button_student){
                    profesi = "Student";
                }else if (i == R.id.radio_button_freelance){
                    profesi = "Freelance";
                }else{
                    profesi = "Startup";
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");

        //inisialisasi form
        inisialisasiForm();

        btnSubmitRegister.requestFocus();


        //ketika button register di klik
        btnSubmitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForm(view);
            }
        });



    }

    private void checkForm(View view) {

        //pengecekan form registrasi
        if (TextUtils.isEmpty(formNama.getText())){
            setSnackbarView("Nama belum terisi",view);
        }else if ( TextUtils.isEmpty(formEmail.getText())){
            setSnackbarView("Email belum terisi",view);
        }else if ( TextUtils.isEmpty(formPassword.getText())){
            setSnackbarView("Password belum terisi",view);
        }else if (TextUtils.isEmpty(formConfirmPassword.getText())){
            setSnackbarView("Confirmation password belum terisi",view);
        }else if (!sudahIsiTanggalLahir){
            setSnackbarView("Tanggal Lahir belum terisi",view);
        }else if (TextUtils.isEmpty(formKota.getText())){
            setSnackbarView("Kota belum terisi",view);
        }else if (TextUtils.isEmpty(formNomorHandphone.getText())){
            setSnackbarView("Nomor Handphone belum terisi",view);
        }else if (TextUtils.isEmpty(formNamaPerusahaan.getText())){
            setSnackbarView("Nama Perusahaan belum terisi",view);
        }else if (indexSpinner==7 && TextUtils.isEmpty(formKeahlianOther.getText())){
            setSnackbarView("Keahlian belum terisi",view);
        }else if (formPassword.getText().length()<6){
            setSnackbarView("Password minimal 6 karakter",view);
        }else if (!isEmailValid(formEmail.getText().toString())){
            setSnackbarView("Email doesn't valid",view);
        }else if (!formPassword.getText().toString().equals(formConfirmPassword.getText().toString())){
            setSnackbarView("Password doesn't match",view);
        }else{
            formBerhasil();
        }

    }

    /*
    metode dipanggil ketika form sudah memenuhi kriteria
     */
    private void formBerhasil() {
        String hasilNama = formNama.getText().toString();
        String hasilEmail = formEmail.getText().toString();
        String hasilPassword = formPassword.getText().toString();
        //tanggal lahir
        Date hasilTanggalLahir = tanggalLahir;
        String hasilGender = gender;
        String hasilKota = formKota.getText().toString();
        String hasilNomorHandphone = formNomorHandphone.getText().toString();
        String hasilProfesi = profesi;
        String hasilNamaPerusahaan = formNamaPerusahaan.getText().toString();
        String hasilKeahlian;
        if (indexSpinner==7){
            hasilKeahlian = formKeahlianOther.getText().toString();
        }else{
            hasilKeahlian = getResources().getStringArray(R.array.keahlian_array)[indexSpinner];
        }
        String hasilInstagram = "https://www.instagram.com/"+formInstagram.getText().toString();
        String hasilLinkedIn = formLinkedIn.getText().toString();
        String hasiFacebook = formFacebook.getText().toString();

        Member member = new Member(hasilNama,hasilEmail,hasilPassword,hasilGender,hasilTanggalLahir,hasilKota,hasilNomorHandphone,hasilProfesi,hasilNamaPerusahaan,hasilKeahlian,hasilInstagram,hasiFacebook,hasilLinkedIn);

        //input member ke API
        uploadToServer(member);

        //pegi ke terimakasih
        Intent intent = new Intent(getApplicationContext(),CompletedRegisterActivity.class);
        startActivity(intent);
        finish();

    }

    private void uploadToServer(Member member) {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(URL_ROOT) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);

        //Defining the method insertuser of our interface
        api.insertUser(

                //Passing the values by getting it from editTexts
                member.getNama(),
                member.getEmail(),
                member.getPassword(),
                member.getGender(),
                member.getTanggalLahir().toString(),
                member.getKota(),
                member.getNomorHandphone(),
                member.getProfesi(),
                member.getNama(),
                member.getKeahlian(),
                member.getInstagram(),
                member.getFacebook(),
                member.getLinkedin(),

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.upload_data_error),
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    /*
    menampilkan snackbar
     */
    private void setSnackbarView(String isi,View view){
        ScrollView scrollView = (ScrollView)findViewById(R.id.scroll_register);
        scrollView.smoothScrollTo(0,0);

        Snackbar.make(view,isi, BaseTransientBottomBar.LENGTH_SHORT)
                .setAction("Action",null).show();
    }

    /*
    pengecekan apakah string tersebut berisi email atau tidak
     */
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();}

    /*
    metode untuk mengupload data data spinner
     */
    private void UploadSpinner() {
        String[] items = getResources().getStringArray(R.array.keahlian_array);

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(formSpinnerKeahlian);

            // Set popupWindow height to 500px
            popupWindow.setHeight(500);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the spinners adapter to the previously created one.
        formSpinnerKeahlian.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    variabel dan metode ketika tanggal lahir di klik oleh pengguna dan langsung menampilkan calendar
     */
    private DatePickerDialog.OnDateSetListener DateTanggalLahirListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            tanggalLahir = new Date(i2,i1,i);
            sudahIsiTanggalLahir = true;

            formTanggalLahir.setText(tanggalLahir.getDay()+"/"+(tanggalLahir.getMonth()+1)+"/"+tanggalLahir.getYear());

        }
    };

    //untuk metode memanggil calendar
    @Override
    protected Dialog onCreateDialog(int id) {

        Calendar calendar = Calendar.getInstance();

        if (id == 999){
            return new DatePickerDialog(this,
                    DateTanggalLahirListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        }
        return super.onCreateDialog(id);
    }

}
