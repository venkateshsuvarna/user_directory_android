package com.venkateshsuvarna.userdirectory.presenter;

import android.content.Context;

import com.venkateshsuvarna.userdirectory.model.UserListModel;
import com.venkateshsuvarna.userdirectory.view.IMainActivityView;

import java.util.List;

public class MainActivityPresenter implements IMainActivityPresenter {

    public IMainActivityView mainActivityView;

    public MainActivityPresenter(IMainActivityView mainActivityView){
        this.mainActivityView = mainActivityView;
    }

    @Override
    public void getUserDetails(Context mContext) {
        UserListModel userListModel = new UserListModel(mContext);
        List<String> userImageURLList = userListModel.getUserImageURLList();
        List<String> userFirstNameList = userListModel.getUserFirstNameList();
        List<String> userLastNameList = userListModel.getUserLastNameList();
    }
}
