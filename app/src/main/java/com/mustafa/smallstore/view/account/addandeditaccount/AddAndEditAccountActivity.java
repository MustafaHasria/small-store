package com.mustafa.smallstore.view.account.addandeditaccount;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mustafa.smallstore.app.button.ProgressButton;
import com.mustafa.smallstore.databinding.ActivityAddAndEditAccountBinding;
import com.mustafa.smallstore.model.entity.AccountEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddAndEditAccountActivity extends AppCompatActivity {

    //region Variables
    View view;
    AddAndEditAccountViewModel addAndEditAccountViewModel;
    ActivityAddAndEditAccountBinding binding;
    Bundle bundle;
    Bitmap bitmap;
    //endregion

    TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            addAndEditAccountViewModel.getAccountsByName(binding.activityAddAndEditAccountEditTextName.getText().toString()).observe(this, accountEntityList ->
            {
                if (accountEntityList.equals(binding.activityAddAndEditAccountEditTextName.getText().toString())) {
                    binding.activityAddAndEditAccountEditTextName.setError("change name");
                }
            });
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    //endregion

    //region On activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.activityAddAndEditAccountImageUser.setImageBitmap(bitmap);
        }
    }

    //endregion

    //region Methods
    private void chooseImage() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "choose picture"), 1);
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    //region Life Cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setTitle("Add Note");

        binding = ActivityAddAndEditAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.activityAddAndEditAccountEditTextName.addTextChangedListener(textWatcher);


        addAndEditAccountViewModel = new ViewModelProvider(this).get(AddAndEditAccountViewModel.class);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            binding.activityAddAndEditAccountMyProgressButton.textView.setText("Update");
            binding.activityAddAndEditAccountEditTextName.setText(bundle.getString("name"));
            binding.activityAddAndEditAccountEditTextPassword.setText(bundle.getString("password"));
            if (bundle.getInt("role") == 0)
                binding.activityAddAndEditAccountRadioButtonAdmin.setChecked(true);
            else
                binding.activityAddAndEditAccountRadioButtonAdmin.isChecked();
        }

        binding.activityAddAndEditAccountMyProgressButton.cardView.setOnClickListener(view -> {
            ProgressButton progressButton = new ProgressButton(AddAndEditAccountActivity.this, view);
            progressButton.buttonActivated();
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                progressButton.buttonFinished();

                Handler handler1 = new Handler();

                handler1.postDelayed(() -> {
                    int role;
                    if (binding.activityAddAndEditAccountRadioButtonAdmin.isChecked()) {
                        role = 0;
                    } else {
                        role = 1;
                    }
                    AccountEntity accountEntity;
                    if (bitmap != null) {
                        accountEntity = new AccountEntity(binding.activityAddAndEditAccountEditTextName.getText().toString(),
                                bitmapToByteArray(bitmap), role,
                                binding.activityAddAndEditAccountEditTextPassword.getText().toString());
                    } else {
                        accountEntity = new AccountEntity(binding.activityAddAndEditAccountEditTextName.getText().toString(),
                                null, role,
                                binding.activityAddAndEditAccountEditTextPassword.getText().toString());

                    }
                    if (bundle != null && bundle.getInt("id") != 0) {
                        accountEntity.setId(bundle.getInt("id"));
                        addAndEditAccountViewModel.update(accountEntity);
                    } else {

                        addAndEditAccountViewModel.insert(accountEntity);
                    }
                    //open Account Activity After Add
                    onBackPressed();
                }, 2000);
            }, 2000);
        });
        binding.activityAddAndEditAccountButtonChoosePicture.setOnClickListener(view1 -> {
            chooseImage();
        });
    }
    //endregion
}