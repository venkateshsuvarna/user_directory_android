package com.venkateshsuvarna.userdirectory.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.venkateshsuvarna.userdirectory.R;

public class CustomUserListAdapter extends ArrayAdapter<String> {

    private final Activity mContext;
    private final String[] userFirstNameStringArray;
    private final String[] userLastNameStringArray;
    private final String[] userImageURLStringArray;

    public CustomUserListAdapter(Activity mContext, String[] userFirstNameStringArray,
                                 String[] userLastNameStringArray,
                                 String[] userImageURLStringArray){
        super(mContext,R.layout.listview_item,userFirstNameStringArray);

        this.mContext = mContext;
        this.userFirstNameStringArray = userFirstNameStringArray;
        this.userLastNameStringArray = userLastNameStringArray;
        this.userImageURLStringArray = userImageURLStringArray;

    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_item,null,true);

        ImageView userListItemImageView = rowView.findViewById(R.id.userListItemImageView);
        TextView userListItemFirstNameTextView = rowView.findViewById(R.id.userListItemFirstNameTextView);
        TextView userListItemLastNameTextView = rowView.findViewById(R.id.userListItemLastNameTextView);

        Log.d("UserDirectoryLogging","Row View First Name = "+userFirstNameStringArray[position]);
        Log.d("UserDirectoryLogging","Row View Last Name = "+userLastNameStringArray[position]);
        Log.d("UserDirectoryLogging","Row View Image URL = "+userImageURLStringArray[position]);

        Picasso.get().load(userImageURLStringArray[position]).into(userListItemImageView);
        userListItemFirstNameTextView.setText(userFirstNameStringArray[position]);
        userListItemLastNameTextView.setText(userLastNameStringArray[position]);

        return rowView;
    }
}
