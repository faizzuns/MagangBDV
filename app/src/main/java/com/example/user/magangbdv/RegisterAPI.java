package com.example.user.magangbdv;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by denail on 17/08/28.
 */

public interface RegisterAPI {
    String URL_FILE = "/API_reguser.php";

    @FormUrlEncoded
    @POST(URL_FILE)
    void insertUser(
            @Field("nama") String name,
            @Field("email") String email,
            @Field("pass") String password,
            @Field("gender") String gender,
            @Field("tgl_lahir") String tanggalLahir,
            @Field("kota") String kota,
            @Field("no_hp") String nomorHandphone,
            @Field("profesi") String profesi,
            @Field("perusahaan") String namaPerusahaan,
            @Field("keahlian") String keahlian,
            @Field("instagram") String instagram,
            @Field("facebook") String facebook,
            @Field("linkedln") String linkedln,
            Callback<Response> callback);
}
