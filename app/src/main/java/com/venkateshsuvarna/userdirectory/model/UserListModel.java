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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserListModel {
    List<String> userImageURLList;
    List<String> userFirstNameList;
    List<String> userLastNameList;

    public UserListModel(Context mContext) {

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
                        // display response
                        Log.d("UserListResponse", response.toString());

                        parseJSONUserListData(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        // add it to the RequestQueue
        queue.add(getRequest);
    }

    public void parseJSONUserListData(JSONObject response){
        try{
            JSONArray data = response.getJSONArray("data");
            for(int i=0;i<=data.length()-1;i++){
                JSONObject userObject = data.getJSONObject(i);
                userImageURLList.add(userObject.getString("avatar"));
                userFirstNameList.add(userObject.getString("first_name"));
                userLastNameList.add(userObject.getString("last_name"));
            }
        }
        catch (Exception e){
            Log.d("ParseJSONError",e.toString());
        }
    }

    public List<String> getUserImageURLList() {
        return userImageURLList;
    }

    public void setUserImageURLList(List<String> userImageURLList) {
        this.userImageURLList = userImageURLList;
    }

    public List<String> getUserFirstNameList() {
        return userFirstNameList;
    }

    public void setUserFirstNameList(List<String> userFirstNameList) {
        this.userFirstNameList = userFirstNameList;
    }

    public List<String> getUserLastNameList() {
        return userLastNameList;
    }

    public void setUserLastNameList(List<String> userLastNameList) {
        this.userLastNameList = userLastNameList;
    }
}
