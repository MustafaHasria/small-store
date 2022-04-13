package com.mustafa.smallstore.activity.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.activity.login.Login;

public class WelcomeAfterStepperActivity extends AppCompatActivity {

    private static int splash_timeout = 3500;
    TextView wel, learning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_after_stepper);
        wel = findViewById(R.id.textview1);
        learning = findViewById(R.id.textview2);
        getSupportActionBar().hide();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeAfterStepperActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, splash_timeout);


        Animation animation = AnimationUtils.loadAnimation(WelcomeAfterStepperActivity.this, R.anim.animation2);
        wel.startAnimation(animation);

        Animation animation2 = AnimationUtils.loadAnimation(WelcomeAfterStepperActivity.this, R.anim.animation1);
        learning.startAnimation(animation);
    }
}