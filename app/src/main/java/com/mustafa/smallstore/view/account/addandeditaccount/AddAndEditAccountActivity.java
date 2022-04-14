package com.mustafa.smallstore.view.account.addandeditaccount;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mustafa.smallstore.databinding.ActivityAddAndEditAccountBinding;
import com.mustafa.smallstore.model.entity.AccountEntity;

public class AddAndEditAccountActivity extends AppCompatActivity {

    //region Variables
    AddAndEditAccountViewModel addAndEditAccountViewModel;
    ActivityAddAndEditAccountBinding binding;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAndEditAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addAndEditAccountViewModel = new ViewModelProvider(this).get(AddAndEditAccountViewModel.class);

        binding.buttonAdd.setOnClickListener(view -> {
            addAndEditAccountViewModel.insert(new AccountEntity(binding.editTextName.getText().toString(),
                    null,
                    0,
                    binding.editTextPassword.getText().toString()));
        });


    }
}