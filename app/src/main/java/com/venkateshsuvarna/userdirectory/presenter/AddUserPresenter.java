package com.venkateshsuvarna.userdirectory.presenter;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.venkateshsuvarna.userdirectory.view.IAddUserView;

import java.util.HashMap;
import java.util.Map;

public class AddUserPresenter implements IAddUserPresenter{

    public IAddUserView addUserView;
    Context mContext;

    public AddUserPresenter(Context mContext, IAddUserView addUserView){
        this.addUserView = addUserView;
        this.mContext = mContext;
    }

    @Override
    public void getUserDetails(String userName, String userJob) {
        Log.d("UserDirectoryLogging","Get User Details Called");
        if(userName.equals("") || userJob.equals("")){
            Log.d("UserDirectoryLogging","One field is empty");
            sendToast("User Name and Job should not be empty");
        }
        else{
            Log.d("UserDirectoryLogging","None of the field is empty");
            sendRequest(mContext,userName,userJob);
        }
    }

    @Override
    public void sendToast(String message) {
        Log.d("UserDirectoryLogging","Send Toast with message = "+message);
        this.addUserView.showToast(message);
    }

    @Override
    public void sendRequest(Context mContext, final String userName, final String userJob) {
        Log.d("UserDirectoryLogging","Send Request Called");
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://reqres.in/api/users";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("UserDirectoryLogging", "Add User Response = "+response);
                        sendToast(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("UserDirectoryLogging", "Add User Error = "+error.toString());
                        sendToast(error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("name", userName);
                params.put("job", userJob);

                return params;
            }
        };
        queue.add(postRequest);
    }
}
