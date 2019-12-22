package com.example.githubfinder.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_Api_Client {

    public static String BASE_URL = "https://api.github.com/";

    public static <S> S createService(Class<S> serviceClass) throws Exception {
        try {
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(160, TimeUnit.SECONDS)
                    .connectTimeout(160, TimeUnit.SECONDS)
                    .build();


            Gson gson = new GsonBuilder()
                    .setLenient()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            Retrofit retrofit = builder.client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit.create(serviceClass);
        }catch (Exception e){
            throw  new Exception("Invalid URL");
        }
    }
}
