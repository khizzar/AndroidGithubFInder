package com.example.githubfinder.Repos;

import android.app.Application;
import android.util.Log;

import com.example.githubfinder.models.InitialUsersModel;
import com.example.githubfinder.Network.Retrofit_Api_Client;
import com.example.githubfinder.Network.Retrofit_Api_Interface;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    Application application;
    private ArrayList<InitialUsersModel> initialUsersModelList = new ArrayList<>();
    private MutableLiveData<List<InitialUsersModel>> listMutableLiveData = new MutableLiveData<>();

    public UsersRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<InitialUsersModel>> getInitialUsers(){
        try {
            Call<InitialUsersModel> call = Retrofit_Api_Client.createService(Retrofit_Api_Interface.class).getInitialUsers();
            call.enqueue(new Callback<InitialUsersModel>() {
                @Override
                public void onResponse(Call<InitialUsersModel> call, Response<InitialUsersModel> response) {
                    InitialUsersModel initialUsersModel = response.body();
                    if (initialUsersModel != null) {
                        initialUsersModelList.add(initialUsersModel);
                        listMutableLiveData.setValue(initialUsersModelList);
                    }
                }

                @Override
                public void onFailure(Call<InitialUsersModel> call, Throwable t) {
                    Log.i("Failed", t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMutableLiveData;
    }

    public MutableLiveData<List<InitialUsersModel>> getInitialUsersList(){
        try {
            Call<List<InitialUsersModel>> call = Retrofit_Api_Client.createService(Retrofit_Api_Interface.class).getInitialUsersList("Authorization token d4db82781a373162db4636fa963a1b41a1bd3888");
            call.enqueue(new Callback<List<InitialUsersModel>>() {
                @Override
                public void onResponse(Call<List<InitialUsersModel>> call, Response<List<InitialUsersModel>> response) {
                    for (int i = 0; i < response.body().size(); i++){
                        initialUsersModelList.add(response.body().get(i));
                    }
//                    listMutableLiveData.setValue(initialUsersModelList);
                    listMutableLiveData.postValue(initialUsersModelList);
                }

                @Override
                public void onFailure(Call<List<InitialUsersModel>> call, Throwable t) {
                    Log.i("Failed", t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return listMutableLiveData;
    }
}
