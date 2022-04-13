package com.mustafa.smallstore.activity.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.activity.stepper.Tools;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Tools.setSystemBarColor(this);
    }
}