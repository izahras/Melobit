package com.example.melobit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HanldeRetrofit {

    private static HanldeRetrofit global;

    public static HanldeRetrofit getGlobal() {
        return global;
    }

    public HanldeRetrofit() {
        global = this;
    }
    private Retrofit retrofit;
    public Retrofit createRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl("https://api-beta.melobit.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public  Retrofit getRetrofit() {
        if (retrofit == null)
            return createRetrofit();
        else
            return retrofit;
    }


}
