package com.example.agnciadeturismo.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTask {

    public static String IP = "192.168.0.106";
    private static Retrofit retrofit;
    private static String BASE_URL = "http://"+IP+"/APIs/";

    private static Retrofit instanceRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static HorizonFlyApi getRetrofit(){
        return instanceRetrofit().create(HorizonFlyApi.class);
    }
}
