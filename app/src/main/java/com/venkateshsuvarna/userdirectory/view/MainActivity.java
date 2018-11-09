package com.venkateshsuvarna.userdirectory.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.venkateshsuvarna.userdirectory.R;
import com.venkateshsuvarna.userdirectory.presenter.CustomUserListAdapter;
import com.venkateshsuvarna.userdirectory.presenter.IMainActivityPresenter;
import com.venkateshsuvarna.userdirectory.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements IMainActivityView{

    IMainActivityPresenter mainActivityPresenter;
    Context mContext = this;
    ListView userListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityPresenter = new MainActivityPresenter(this,MainActivity.this);

        userListView = findViewById(R.id.userListView);
        mainActivityPresenter.getUserDetails(mContext);
        getSupportActionBar().setTitle("User Directory");

    }

    @Override
    public void displayList(CustomUserListAdapter userListAdapter) {
        userListView.setAdapter(userListAdapter);
    }
}
