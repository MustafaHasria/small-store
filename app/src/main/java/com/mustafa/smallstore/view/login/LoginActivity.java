package com.mustafa.smallstore.view.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.view.stepper.Tools;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Tools.setSystemBarColor(this);
    }
}