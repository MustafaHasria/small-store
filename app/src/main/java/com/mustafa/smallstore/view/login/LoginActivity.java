package com.mustafa.smallstore.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;

import com.mustafa.smallstore.databinding.ActivityLoginBinding;
import com.mustafa.smallstore.model.entity.AccountEntity;
import com.mustafa.smallstore.view.dashboard.MainActivity;
import com.mustafa.smallstore.view.stepper.Tools;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    //region Component
    ActivityLoginBinding binding;
    List<AccountEntity> accountEntityList;
    LoginViewModel loginViewModel;
    AccountEntity accountEntity;
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

        binding.activityLoginProgressButtonLogin.cardView.setOnClickListener(view -> {
            String Name, Password;

            Name = binding.activityLoginTextInputEditTextUsername.getText().toString().trim();
            Password = binding.activityLoginTextInputEditTextPassword.getText().toString().trim();

            binding.activityLoginProgressButtonLogin.cardView.setOnClickListener(view1 ->
            {

                if (Name != null) {
                    binding.activityLoginTextInputEditTextUsername.setError("Please Enter Your Name Address");
                } else if (Password != null) {
                    binding.activityLoginTextInputEditTextPassword.setError("Please Enter Your Password");
                } else {
                    if (accountEntityList.contains(accountEntity.getName() == Name) && accountEntityList.contains(accountEntity.getPassword() == Password)) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }


            });

        });

    }
    //endregion
}