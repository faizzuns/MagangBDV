package com.example.user.magangbdv;

import com.example.user.magangbdv.data.MemberModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 04/09/2017.
 */

public interface EmailAPI {

    @GET("/API_datauser.php?")
    Call<MemberModel> getMahasiswaList(@Query("group") String group,@Query("email") String email);

}
