package com.venkateshsuvarna.userdirectory.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.venkateshsuvarna.userdirectory.R;
import com.venkateshsuvarna.userdirectory.presenter.CustomUserListAdapter;
import com.venkateshsuvarna.userdirectory.presenter.IMainActivityPresenter;
import com.venkateshsuvarna.userdirectory.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements IMainActivityView{

    IMainActivityPresenter mainActivityPresenter;
    Context mContext = this;
    ListView userListView;
    FloatingActionButton addUserFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityPresenter = new MainActivityPresenter(this,MainActivity.this);

        userListView = findViewById(R.id.userListView);
        mainActivityPresenter.getUserDetails(mContext);
        getSupportActionBar().setTitle("User Directory");

        addUserFloatingActionButton = findViewById(R.id.addUserFloatingActionButton);
        addUserFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddUserActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void displayList(CustomUserListAdapter userListAdapter) {
        userListView.setAdapter(userListAdapter);
    }
}
