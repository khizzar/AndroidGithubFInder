package com.example.githubfinder.viewModels;

import android.app.Application;

import com.example.githubfinder.models.InitialUsersModel;
import com.example.githubfinder.Repos.UsersRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainPageActivityViewModel extends AndroidViewModel {
    private UsersRepository usersRepository;

    public MainPageActivityViewModel(@NonNull Application application) {
        super(application);

//        get the instance of the repository
        usersRepository = new UsersRepository(application);
    }

    public LiveData<List<InitialUsersModel>> getInitialUsers(){
        return usersRepository.getInitialUsers();
    }

    public LiveData<List<InitialUsersModel>> getInitialUsersList(){
        return usersRepository.getInitialUsersList();
    }


}
