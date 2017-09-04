package com.example.user.magangbdv;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 04/09/2017.
 */

public class MemberHelper {

    public static final String BASE_URL = "http://member.bandungdigitalvalley.com/";
    static Retrofit retrofit = null;

    public static Retrofit client() {
        Gson gson = new GsonBuilder().
                setLenient().create();

        retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create(gson)).
                build();
        return retrofit;
    }

}
