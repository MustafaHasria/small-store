package com.mustafa.smallstore.view.account.addandeditaccount;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.app.button.ProgressButton;
import com.mustafa.smallstore.databinding.ActivityAddAndEditAccountBinding;
import com.mustafa.smallstore.model.entity.AccountEntity;
import com.mustafa.smallstore.view.account.AccountActivity;

public class AddAndEditAccountActivity extends AppCompatActivity {

    //region Variables
    View view;
    AddAndEditAccountViewModel addAndEditAccountViewModel;
    ActivityAddAndEditAccountBinding binding;
    Bundle bundle;
    String activityAddAndEditAccountEditTextName, activityAddAndEditAccountEditTextPassword;
    //endregion

    //region Life Cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setTitle("Add Note");

        binding = ActivityAddAndEditAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addAndEditAccountViewModel = new ViewModelProvider(this).get(AddAndEditAccountViewModel.class);
        view = findViewById(R.id.activity_add_and_edit_account_myProgressButton);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressButton progressButton = new ProgressButton(AddAndEditAccountActivity.this, view);
                progressButton.buttonActivated();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressButton.buttonFinished();

                        Handler handler1 = new Handler();

                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int Role;
                                if (binding.activityAddAndEditAccountRadioButtonAdmin.isChecked()) {
                                    Role = 0;
                                } else {
                                    Role = 1;
                                }
                                addAndEditAccountViewModel.insert(new AccountEntity(binding.activityAddAndEditAccountEditTextName.getText().toString(),
                                        null,
                                        Role,
                                        binding.activityAddAndEditAccountEditTextPassword.getText().toString()));
                                //open Account Activity After Add
                                Intent intent = new Intent(AddAndEditAccountActivity.this, AccountActivity.class);
                                startActivity(intent);

                            }
                        }, 2000);
                    }
                }, 2000);
            }
        });

        bundle = getIntent().getExtras();

        binding.activityAddAndEditAccountEditTextName.setText(bundle.getString("name"));
        binding.activityAddAndEditAccountEditTextPassword.setText(bundle.getString("password"));

        if (bundle.getInt("role") == 0)
            binding.activityAddAndEditAccountRadioButtonAdmin.isChecked();

        else
            binding.activityAddAndEditAccountRadioButtonUser.isChecked();
    }
    //endregion
}