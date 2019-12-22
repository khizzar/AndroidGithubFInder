package com.example.githubfinder.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.example.githubfinder.Adapters.UsersAdapter;
import com.example.githubfinder.models.InitialUsersModel;
import com.example.githubfinder.R;
import com.example.githubfinder.databinding.ActivityMainPageBinding;
import com.example.githubfinder.viewModels.MainPageActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainPageActivity extends AppCompatActivity {

    private ActivityMainPageBinding mainPageBinding;
    private MainPageActivityViewModel mainPageActivityViewModel;
    private MainActivityClickHandlers mainActivityClickHandlers;
    private RecyclerView initialUsersListView;
    private ArrayList<InitialUsersModel> initialUsersModelArrayList;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

//        initialize the view models and all
        mainPageBinding = DataBindingUtil.setContentView(this,R.layout.activity_main_page);
        mainPageActivityViewModel = ViewModelProviders.of(this).get(MainPageActivityViewModel.class);
//        set the click handler
        mainActivityClickHandlers = new MainActivityClickHandlers();
        mainPageBinding.setMainActivityClickHandler(mainActivityClickHandlers);
        mainPageBinding.setLifecycleOwner(this);

//        mainPageActivityViewModel.getInitialUsers().observe(MainPageActivity.this, new Observer<List<InitialUsersModel>>() {
//            @Override
//            public void onChanged(List<InitialUsersModel> initialUsersModels) {
//                initialUsersModelArrayList = (ArrayList<InitialUsersModel>) initialUsersModels;
//            }
//        });

        mainPageActivityViewModel.getInitialUsersList().observe(MainPageActivity.this, new Observer<List<InitialUsersModel>>() {
            @Override
            public void onChanged(List<InitialUsersModel> initialUsersModels) {
                initialUsersModelArrayList = (ArrayList<InitialUsersModel>)initialUsersModels;
                initRecyclerView(initialUsersModelArrayList);
            }
        });

    }

    public class MainActivityClickHandlers{
        public void onFabButtonClick(View view){
            Intent main = new Intent(MainPageActivity.this,SearchUserActivity.class);
            main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
        }
    }

//    init the recycler view
    public void initRecyclerView(ArrayList<InitialUsersModel> initialUsersModels){
//        Log.i("initials", "onCreate: " + initialUsersModels.size());
        initialUsersListView = mainPageBinding.initialUsers;
        usersAdapter = new UsersAdapter(MainPageActivity.this,this,initialUsersModelArrayList);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            initialUsersListView.setLayoutManager(new GridLayoutManager(this, 1));
        } else {
            initialUsersListView.setLayoutManager(new GridLayoutManager(this, 1));
        }

        initialUsersListView.setItemAnimator(new DefaultItemAnimator());
        initialUsersListView.setAdapter(usersAdapter);
//        usersAdapter.notifyDataSetChanged();
    }
}
