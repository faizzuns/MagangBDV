package com.example.user.magangbdv.data;

/**
 * Created by User on 18/08/2017.
 */

public class Member {

    String nama;
    String email;
    String password;
    String Gender;
    Date tanggalLahir;
    String kota;
    String nomorHandphone;
    String profesi;
    String namaPerusahaan;
    String keahlian;
    String instagram;
    String facebook;
    String linkedin;

    public Member(String nama, String email, String password, String gender, Date tanggalLahir, String kota, String nomorHandphone, String profesi, String namaPerusahaan, String keahlian, String instagram, String facebook, String linkedin) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        Gender = gender;
        this.tanggalLahir = tanggalLahir;
        this.kota = kota;
        this.nomorHandphone = nomorHandphone;
        this.profesi = profesi;
        this.namaPerusahaan = namaPerusahaan;
        this.keahlian = keahlian;
        this.instagram = instagram;
        this.facebook = facebook;
        this.linkedin = linkedin;
    }
}
