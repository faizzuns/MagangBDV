package com.example.user.magangbdv.data;

/**
 * Created by User on 18/08/2017.
 */

public class Member {

    private String nama;
    private String email;
    private String password;
    private String gender;
    private Date tanggalLahir;
    private String kota;
    private String nomorHandphone;
    private String profesi;
    private String namaPerusahaan;
    private String keahlian;
    private String instagram;
    private String facebook;
    private String linkedin;

    public Member(String nama, String email, String password, String gender, Date tanggalLahir, String kota, String nomorHandphone, String profesi, String namaPerusahaan, String keahlian, String instagram, String facebook, String linkedin) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.gender = gender;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getNomorHandphone() {
        return nomorHandphone;
    }

    public void setNomorHandphone(String nomorHandphone) {
        this.nomorHandphone = nomorHandphone;
    }

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}
