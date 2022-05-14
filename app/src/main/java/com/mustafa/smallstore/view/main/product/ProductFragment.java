package com.mustafa.smallstore.view.main.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.databinding.FragmentProductBinding;
import com.mustafa.smallstore.view.main.product.addandeditproduct.AddAndEditProduct;


public class ProductFragment extends Fragment {

    //region Variables
    FragmentProductBinding binding;
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);
        binding = FragmentProductBinding.bind(view);


        //open addAndEditProduct Fragment
        binding.fragmentProductFloatingActionButtonAddProduct.setOnClickListener(view1 ->
        {
//            AddAndEditProduct addAndEditProduct = new AddAndEditProduct();
//            FragmentTransaction fragmentTransaction =
//                    requireActivity().getSupportFragmentManager().beginTransaction();
//
//            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                    .add(R.id.fragment_product_frame_layout, addAndEditProduct, "ADD_AND_EDIT_PRODUCT_FRAGMENT")
//                    .addToBackStack("fasdf")
//                    .commit();


            AddAndEditProduct addAndEditProduct = new AddAndEditProduct();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity_frame_layout, addAndEditProduct, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}