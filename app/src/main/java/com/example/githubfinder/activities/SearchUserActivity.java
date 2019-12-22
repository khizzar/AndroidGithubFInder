package com.example.githubfinder.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Html;

import com.example.githubfinder.R;
import com.example.githubfinder.databinding.ActivitySearchUserBinding;

public class SearchUserActivity extends AppCompatActivity {

    ActivitySearchUserBinding activitySearchUserBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        activitySearchUserBinding = DataBindingUtil.setContentView(SearchUserActivity.this,R.layout.activity_search_user);

        activitySearchUserBinding.titleMsg.setText(Html.fromHtml(getString(R.string.search_user_title_msg)));


    }
}
