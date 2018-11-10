package com.venkateshsuvarna.userdirectory.presenter;

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

        //This method will get the user details from AddUserActivity and perform validation
        //If the username and password are empty then it will tell to show a toast message
        //Or else it will perform the POST request using the sendRequest() method

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

        //This function will just instruct the view to show a Toast message to the user
        Log.d("UserDirectoryLogging","Send Toast with message = "+message);
        this.addUserView.showToast(message);
    }

    @Override
    public void sendRequest(Context mContext, final String userName, final String userJob) {

        //This functions takes the userName and userJob from the getUserDetails()
        //and performs a POST request

        Log.d("UserDirectoryLogging","Send Request Called");
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://reqres.in/api/users";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        //Response received
                        Log.d("UserDirectoryLogging", "Add User Response = "+response);
                        sendToast("User Added Successfully with Response ="+response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Error
                        Log.d("UserDirectoryLogging", "Add User Error = "+error.toString());
                        sendToast(error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                //Putting the POST parameters here
                Map<String, String>  params = new HashMap<>();
                params.put("name", userName);
                params.put("job", userJob);

                return params;
            }
        };
        queue.add(postRequest);
    }
}
