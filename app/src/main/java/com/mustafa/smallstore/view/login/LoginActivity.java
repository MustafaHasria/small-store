package com.mustafa.smallstore.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;

import com.mustafa.smallstore.databinding.ActivityLoginBinding;
import com.mustafa.smallstore.view.dashboard.MainActivity;
import com.mustafa.smallstore.view.stepper.Tools;

public class LoginActivity extends AppCompatActivity {

    //region Component
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    //endregion

    //region Life Cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        Tools.setSystemBarColor(this);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.activityLoginProgressButtonLogin.cardView.setOnClickListener(view ->
        {
            if (binding.activityLoginTextInputEditTextUsername.getText() == null || binding.activityLoginTextInputEditTextPassword.getText() == null) {
                binding.activityLoginTextInputEditTextUsername.setError("Please Fill The User Name");
                binding.activityLoginTextInputEditTextPassword.setError("Please Fill The Password");
            } else {

                loginViewModel.login(binding.activityLoginTextInputEditTextUsername.getText().toString().trim(),
                        binding.activityLoginTextInputEditTextPassword.getText().toString().trim())
                        .observe(this, accountEntityList ->
                        {
                            if (accountEntityList.size() != 0) {
                                Intent intent = new Intent(this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(this, "The Name : " + binding.activityLoginTextInputEditTextUsername.getText().toString() + " And " + "Password" + "  " + binding.activityLoginTextInputEditTextPassword.getText().toString() + "Note Found", Toast.LENGTH_SHORT).show();
                            }

                        });
            }
        });
    }
    //endregion
}