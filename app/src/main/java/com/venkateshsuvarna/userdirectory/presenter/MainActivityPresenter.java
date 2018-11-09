package com.venkateshsuvarna.userdirectory.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.venkateshsuvarna.userdirectory.model.UserListModel;
import com.venkateshsuvarna.userdirectory.view.IMainActivityView;
import com.venkateshsuvarna.userdirectory.view.MainActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class MainActivityPresenter implements IMainActivityPresenter {

    public IMainActivityView mainActivityView;
    Activity currentActivity;
    UserListModel userListModel;

    public MainActivityPresenter(IMainActivityView mainActivityView, Activity currentActivity){
        this.mainActivityView = mainActivityView;
        this.currentActivity = currentActivity;
    }

    @Override
    public void getUserDetails(Context mContext) {
        userListModel = new UserListModel(mContext);

        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(userListModel.isDataLoaded() == true){
                    loadListView();
                    timer.cancel();
                }
            }
        },500,1000);


    }

    @Override
    public void loadListView() {
        String[] userFirstNameStringArray = userListModel.getUserFirstNameStringArray();
        String[] userLastNameStringArray = userListModel.getUserLastNameStringArray();
        String[] userImageURLStringArray = userListModel.getUserImageURLStringArray();

        Log.d("UserDirectoryLogging","Get User Details Array Print Start");
        Log.d("UserDirectoryLogging","User First Name String Array");
        Log.d("UserDirectoryLogging",Arrays.toString(userFirstNameStringArray));
        Log.d("UserDirectoryLogging","User Last Name String Array");
        Log.d("UserDirectoryLogging",Arrays.toString(userLastNameStringArray));
        Log.d("UserDirectoryLogging","User Image URL String Array");
        Log.d("UserDirectoryLogging",Arrays.toString(userImageURLStringArray));
        Log.d("UserDirectoryLogging","Get User Details Array Print End");

        final CustomUserListAdapter customUserListAdapter =
                new CustomUserListAdapter(currentActivity,userFirstNameStringArray,
                        userLastNameStringArray,userImageURLStringArray);

        currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityView.displayList(customUserListAdapter);
            }
        });

        //mainActivityView.displayList(customUserListAdapter);
    }
}
