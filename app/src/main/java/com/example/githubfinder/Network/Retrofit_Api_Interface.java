package com.example.githubfinder.Network;

import com.example.githubfinder.models.InitialUsersModel;
import com.example.githubfinder.models.UsersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit_Api_Interface {

    //    get random first 30 users
    @GET("users")
    Call<InitialUsersModel> getInitialUsers();

//    Things that come after the ? are usually queries.
    @GET("users")
    Call<List<InitialUsersModel>> getInitialUsersList(@Query("client_id") String client_id,
                                                      @Query("client_secret") String client_secret);

    @GET("users/{username}")
    Call<UsersModel> getSingleUserData(@Path("username") String username,
                                       @Query("client_id") String client_id,
                                       @Query("client_secret") String client_secret);

}
