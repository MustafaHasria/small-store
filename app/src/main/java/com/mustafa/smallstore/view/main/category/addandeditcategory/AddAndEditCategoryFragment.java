package com.mustafa.smallstore.view.main.category.addandeditcategory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.databinding.FragmentAddAndEditCategoryBinding;
import com.mustafa.smallstore.model.entity.CategoryEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddAndEditCategoryFragment extends Fragment {

    //region Variable
    FragmentAddAndEditCategoryBinding binding;
    AddAndEditCategoryViewModel viewModel;
    Bitmap bitmap;
    Bundle bundle;
    CategoryEntity categoryEntity;
    //endregion

    //region Life Cycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_and_edit_category, container, false);
        binding = FragmentAddAndEditCategoryBinding.bind(view);

        viewModel = new ViewModelProvider(this).get(AddAndEditCategoryViewModel.class);


        bundle = getArguments();
        if (bundle != null) {
            binding.fragmentAddAndEditCategoryEditButtonAddCategory.setText("Update");
            binding.fragmentAddAndEditCategoryEditEditTextCategoryName.setText(bundle.getString("name"));
        }

        binding.fragmentAddAndEditCategoryEditButtonAddCategory.setOnClickListener(view1 ->
        {
            if (bitmap != null) {
                categoryEntity = new CategoryEntity(binding.fragmentAddAndEditCategoryEditEditTextCategoryName.getText().toString(),
                        bitmapToByteArray(bitmap));
            } else {
                categoryEntity = new CategoryEntity(binding.fragmentAddAndEditCategoryEditEditTextCategoryName.getText().toString(),
                        null);
            }

            if (bundle != null && bundle.getInt("id") != 0) {
                categoryEntity.setId(bundle.getInt("id"));
                viewModel.update(categoryEntity);
                Toast.makeText(getContext(), "The Category Has Been Update", Toast.LENGTH_SHORT).show();
            } else {

                viewModel.insert(categoryEntity);
                Toast.makeText(getContext(), "The Category Has Been Saved", Toast.LENGTH_SHORT).show();
            }


            //Back to account activity after add
            requireActivity().onBackPressed();
        });


        binding.fragmentAddAndEditCategoryEditButtonChooseImage.setOnClickListener(view1 -> {
            chooseImage();
        });

        return view;
    }
    //endregion


    //region On fragment result (use for add image)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.fragmentAddAndEditCategoryEditRoundedImageCategoryImage.setImageBitmap(bitmap);
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
    //endregion
}