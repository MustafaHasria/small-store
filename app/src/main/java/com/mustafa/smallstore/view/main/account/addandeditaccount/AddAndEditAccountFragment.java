package com.mustafa.smallstore.view.main.account.addandeditaccount;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.app.button.ProgressButton;
import com.mustafa.smallstore.databinding.FragmentAddAndEditAccountBinding;
import com.mustafa.smallstore.model.entity.AccountEntity;

import java.io.ByteArrayOutputStream;

public class AddAndEditAccountFragment extends Fragment {

    //region variable
    View view;
    AddAndEditAccountViewModel addAndEditAccountViewModel;
    FragmentAddAndEditAccountBinding binding;
    Bundle bundle;
    Bitmap bitmap;
    //endregion

    //region Life Cycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_and_edit_account, container, false);
        binding = FragmentAddAndEditAccountBinding.bind(view);

        //Validate Name
        binding.fragmentAddAndEditAccountEditTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                addAndEditAccountViewModel.getAccountsByName(binding.fragmentAddAndEditAccountEditTextName.getText().toString()).observe(getViewLifecycleOwner(), accountEntityList ->
                {
                    if (bundle == null) {
                        if (accountEntityList.size() != 0 &&
                                accountEntityList.get(0).getName().equals(binding.fragmentAddAndEditAccountEditTextName.getText().toString())) {
                            binding.fragmentAddAndEditAccountEditTextName.setError("This name is used");
                        }
                    }

                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        addAndEditAccountViewModel = new ViewModelProvider(this).get(AddAndEditAccountViewModel.class);

        bundle = getArguments();
        if (bundle != null) {
            binding.fragmentAddAndEditAccountMyProgressButton.textView.setText("Update");
            binding.fragmentAddAndEditAccountEditTextName.setText(bundle.getString("name"));
            binding.fragmentAddAndEditAccountEditTextPassword.setText(bundle.getString("password"));
            if (bundle.getInt("role") == 0)
                binding.fragmentAddAndEditAccountRadioButtonAdmin.setChecked(true);
            else
                binding.fragmentAddAndEditAccountRadioButtonAdmin.isChecked();
        }

        binding.fragmentAddAndEditAccountMyProgressButton.cardView.setOnClickListener(view1 -> {
            ProgressButton progressButton = new ProgressButton(getContext(), view1);
            progressButton.buttonActivated();
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                progressButton.buttonFinished();

                Handler handler1 = new Handler();

                handler1.postDelayed(() -> {
                    int role;
                    if (binding.fragmentAddAndEditAccountRadioButtonAdmin.isChecked()) {
                        role = 0;
                    } else {
                        role = 1;
                    }
                    AccountEntity accountEntity;
                    if (bitmap != null) {
                        accountEntity = new AccountEntity(binding.fragmentAddAndEditAccountEditTextName.getText().toString(),
                                bitmapToByteArray(bitmap), role,
                                binding.fragmentAddAndEditAccountEditTextPassword.getText().toString());
                    } else {
                        accountEntity = new AccountEntity(binding.fragmentAddAndEditAccountEditTextName.getText().toString(),
                                null, role,
                                binding.fragmentAddAndEditAccountEditTextPassword.getText().toString());

                    }
                    if (bundle != null && bundle.getInt("id") != 0) {
                        accountEntity.setId(bundle.getInt("id"));
                        addAndEditAccountViewModel.update(accountEntity);
                    } else {

                        addAndEditAccountViewModel.insert(accountEntity);
                    }
                    //Back to account activity after add
                    requireActivity().onBackPressed();
                }, 2000);
            }, 2000);
        });
//        binding.fragmentAddAndEditAccountButtonChoosePicture.setOnClickListener(view1 -> {
//            chooseImage();
//        });

        return view;
    }
    //endregion

    //region On fragment result
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            Uri uri = data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            binding.fragmentAddAndEditAccountImageUser.setImageBitmap(bitmap);
//        }
//    }

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
    //endregion

}