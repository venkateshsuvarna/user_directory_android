package com.venkateshsuvarna.userdirectory.model;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.venkateshsuvarna.userdirectory.presenter.IMainActivityPresenter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserListModel {
    List<String> userImageURLList;
    List<String> userFirstNameList;
    List<String> userLastNameList;

    public boolean isDataLoaded() {
        return dataLoaded;
    }

    boolean dataLoaded = false;

    public UserListModel(Context mContext) {

        //Initialize the arraylist
        userImageURLList = new ArrayList<>();
        userFirstNameList = new ArrayList<>();
        userLastNameList = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(mContext);
        final String url = "https://reqres.in/api/users";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Record Response
                        Log.d("UserDirectoryLogging", "Response from Server = "+response.toString());
                        dataLoaded = true;
                        parseJSONUserListData(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("UserDirectoryLogging", "Error from Server = "+error.toString());
                    }
                }
        );

        //Add it to the RequestQueue
        queue.add(getRequest);
    }

    public void parseJSONUserListData(JSONObject response){

        //This function will take the response JSON object from the GET url and convert the data
        //into the respective arraylists

        Log.d("UserDirectoryLogging","parseJSONUserListData called");

        try{
            JSONArray data = response.getJSONArray("data");
            for(int i=0;i<=data.length()-1;i++){
                JSONObject userObject = data.getJSONObject(i);
                userImageURLList.add(userObject.getString("avatar"));
                userFirstNameList.add(userObject.getString("first_name"));
                userLastNameList.add(userObject.getString("last_name"));

                Log.d("UserDirectoryLogging","First Name = "
                        +userObject.getString("first_name"));
                Log.d("UserDirectoryLogging","Last Name = "
                        +userObject.getString("last_name"));
                Log.d("UserDirectoryLogging","Avatar = "
                        +userObject.getString("avatar"));

            }
        }
        catch (Exception e){
            Log.d("ParseJSONError",e.toString());
        }

        Log.d("UserDirectoryLogging","parseJSONUserListData method end");
    }


    public String[] getUserImageURLStringArray(){
        return userImageURLList.toArray(new String[0]);
    }

    public String[] getUserFirstNameStringArray(){
        return userFirstNameList.toArray(new String[0]);
    }


    public String[] getUserLastNameStringArray(){
        return userLastNameList.toArray(new String[0]);
    }

}
