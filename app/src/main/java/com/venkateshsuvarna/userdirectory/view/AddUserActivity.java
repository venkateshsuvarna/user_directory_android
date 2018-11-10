package com.venkateshsuvarna.userdirectory.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.venkateshsuvarna.userdirectory.R;
import com.venkateshsuvarna.userdirectory.presenter.AddUserPresenter;
import com.venkateshsuvarna.userdirectory.presenter.IAddUserPresenter;

public class AddUserActivity extends AppCompatActivity implements IAddUserView{

    IAddUserPresenter addUserPresenter;
    Context mContext = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        addUserPresenter = new AddUserPresenter(mContext,this);

        getSupportActionBar().setTitle("Add User");

        final EditText newUserNameEditText = findViewById(R.id.newUserNameEditText);
        final EditText newUserJobEditText = findViewById(R.id.newUserJobEditText);
        Button addNewUserButton = findViewById(R.id.addNewUserButton);

        addNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserPresenter.getUserDetails(newUserNameEditText.getText().toString(),
                        newUserJobEditText.getText().toString());

            }
        });
    }

    @Override
    public void showToast(String message) {
        Log.d("UserDirectoryLogging","Show Toast with message = "+message);
        Toast.makeText(mContext,message,Toast.LENGTH_LONG).show();
    }
}
