package com.mustafa.smallstore.view.main.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.databinding.FragmentProductBinding;
import com.mustafa.smallstore.view.main.product.addandeditproduct.AddAndEditProductFragment;

import java.util.ArrayList;


public class ProductFragment extends Fragment {

    //region Variables
    FragmentProductBinding binding;
    ProductAdapter productAdapter;
    ProductViewModel productViewModel;
    //endregion

    //region Life cycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);
        binding = FragmentProductBinding.bind(view);
        setupRecyclerView();

        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        productViewModel.getAllProducts().observe(requireActivity(), productEntities -> {
            productAdapter.refreshList(productEntities);
        });
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


            AddAndEditProductFragment addAndEditProductFragment = new AddAndEditProductFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity_frame_layout, addAndEditProductFragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
    //endregion

    //region Methods
    private void setupRecyclerView() {
        productAdapter = new ProductAdapter(new ArrayList<>());
        binding.fragmentProductRecyclerViewProduct.setHasFixedSize(true);
        binding.fragmentProductRecyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false));
        binding.fragmentProductRecyclerViewProduct.setAdapter(productAdapter);
    }
    //endregion
}