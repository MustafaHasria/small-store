package com.mustafa.smallstore.view.main.product.addandeditproduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.databinding.FragmentAddAndEditProductBinding;

public class AddAndEditProduct extends Fragment {

    //region Variables
    FragmentAddAndEditProductBinding binding;
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        binding = FragmentAddAndEditProductBinding.bind(view);


        return view;
    }
}