
package com.example.user.magangbdv.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserList {

    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("profesi")
    @Expose
    private String profesi;
    @SerializedName("perusahaan")
    @Expose
    private String perusahaan;
    @SerializedName("keahlian")
    @Expose
    private String keahlian;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("linkedln")
    @Expose
    private String linkedln;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("foto")
    @Expose
    private String foto;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
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

    public String getLinkedln() {
        return linkedln;
    }

    public void setLinkedln(String linkedln) {
        this.linkedln = linkedln;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
