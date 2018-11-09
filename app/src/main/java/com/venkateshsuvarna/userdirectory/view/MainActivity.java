package com.venkateshsuvarna.userdirectory.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.venkateshsuvarna.userdirectory.R;

public class MainActivity extends AppCompatActivity implements IMainActivityView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
