package com.venkateshsuvarna.userdirectory.presenter;

import android.content.Context;

public interface IAddUserPresenter {
    void getUserDetails(String userName, String userJob);
    void sendToast(String message);
    void sendRequest(Context mContext, String userName, String userJob);
}
