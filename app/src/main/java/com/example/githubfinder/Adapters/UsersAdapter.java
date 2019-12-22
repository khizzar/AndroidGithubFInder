package com.example.githubfinder.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubfinder.models.InitialUsersModel;
import com.example.githubfinder.R;
import com.example.githubfinder.databinding.UsersInfoCardBinding;
import com.example.githubfinder.utils.UserDataSetter;
import com.github.florent37.viewtooltip.ViewTooltip;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersListViewHolder>{

    private Context context;
    private ArrayList<InitialUsersModel> initialUsersModelArrayList;
    private UserDataSetter userDataSetter = new UserDataSetter();
    private Activity callingActivity;

    public UsersAdapter(Activity activity, Context context, ArrayList<InitialUsersModel> initialUsersModelArrayList) {
        activity = callingActivity;
        this.context = context;
        this.initialUsersModelArrayList = initialUsersModelArrayList;
    }

    @NonNull
    @Override
    public UsersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UsersInfoCardBinding usersInfoCardBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.users_info_card,parent,false);
        return new UsersListViewHolder(usersInfoCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListViewHolder holder, int position) {
        InitialUsersModel initialUsersModel = initialUsersModelArrayList.get(position);
        userDataSetter.setUserData(holder.usersInfoCardBinding,initialUsersModel,holder,context);
//        if (position == 0){
//                    final ViewTooltip.TooltipView tooltipView = ViewTooltip
//                    .on(callingActivity, holder.usersInfoCardBinding.viewProfile)
//                    .autoHide(true, 5000)
//                    .corner(96)
//                    .padding(paddingLeft, paddingTop, paddingRight, paddingBot)
//                    .position(ViewTooltip.Position.TOP)
//                    .textColor(Color.WHITE)
//                    .arrowWidth(14)
//                    .color(getResources().getColor(R.color.black))
//                    .distanceWithView(2)
//                    .arrowHeight(14)
//                    .align(ViewTooltip.ALIGN.CENTER)
//                    .text(R.string.swipe_left_switch_voice)
//                    .clickToHide(true)
//
//                    .onHide(new ViewTooltip.ListenerHide() {
//                        @Override
//                        public void onHide(View view) {
//                        }
//                    })
//                    .show();
////                    .border(context.getResources().getColor(R.color.black), borderMeasure)
//
//            tooltipView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    tooltipView.close();
//                }
//            });
//            holder.usersInfoCardBinding.setUserModel(initialUsersModel);
//        }
    }

    @Override
    public int getItemCount() {
        return initialUsersModelArrayList.size();
    }

    public class UsersListViewHolder extends RecyclerView.ViewHolder{
        private UsersInfoCardBinding usersInfoCardBinding;

        UsersListViewHolder(@NonNull UsersInfoCardBinding usersInfoCardBinding) {
            super(usersInfoCardBinding.getRoot());
            this.usersInfoCardBinding = usersInfoCardBinding;
        }
    }

}
