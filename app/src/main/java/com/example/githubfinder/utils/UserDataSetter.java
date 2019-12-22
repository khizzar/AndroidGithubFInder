package com.example.githubfinder.utils;

import android.content.Context;
import android.util.Log;

import com.example.githubfinder.Adapters.UsersAdapter;
import com.example.githubfinder.Network.Retrofit_Api_Client;
import com.example.githubfinder.Network.Retrofit_Api_Interface;
import com.example.githubfinder.databinding.UsersInfoCardBinding;
import com.example.githubfinder.models.InitialUsersModel;
import com.example.githubfinder.models.UsersModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSetter {
    private UsersInfoCardBinding usersInfoCardBinding;
    private InitialUsersModel initialUsersModel;
    private UsersAdapter.UsersListViewHolder holder;
    private Context context;

    public UserDataSetter() {}

    public void setUserData(UsersInfoCardBinding usersInfoCardBinding, InitialUsersModel initialUsersModel, UsersAdapter.UsersListViewHolder holder, Context context) {
        this.usersInfoCardBinding = usersInfoCardBinding;
        this.initialUsersModel = initialUsersModel;
        this.holder = holder;
        this.context = context;

//        make the api call and set the data here
        getUserDataRetrofit(initialUsersModel.getLogin());

    }

    private void getUserDataRetrofit(String userLogin){
        try {
            Call<UsersModel> call = Retrofit_Api_Client.createService(Retrofit_Api_Interface.class).getSingleUserData(userLogin);
            call.enqueue(new Callback<UsersModel>() {
                @Override
                public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                    UsersModel usersModel = response.body();
                    usersInfoCardBinding.setUserModel(usersModel);
                }

                @Override
                public void onFailure(Call<UsersModel> call, Throwable t) {
                    Log.i("userData", "onFailure: " + t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
