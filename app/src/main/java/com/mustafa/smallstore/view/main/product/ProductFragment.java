package com.mustafa.smallstore.view.main.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.databinding.FragmentProductBinding;
import com.mustafa.smallstore.model.entity.ProductEntity;
import com.mustafa.smallstore.view.main.product.addandeditproduct.AddAndEditProductFragment;

import java.util.ArrayList;


public class ProductFragment extends Fragment implements ProductAdapter.ProductOnClickListener, ProductOfferAdapter.ProductOfferOnClickListener {

    //region Variables
    FragmentProductBinding binding;
    ProductAdapter productAdapter;
    ProductOfferAdapter productOfferAdapter;
    ProductViewModel productViewModel;
    ProductEntity productEntity;
    Bundle bundle;
    //endregion

    //region Life cycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);
        binding = FragmentProductBinding.bind(view);
        setupRecyclerView();
        setupRecyclerViewProductOffer();


        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        productViewModel.getAllProducts().observe(requireActivity(), productEntities -> {
            productAdapter.refreshList(productEntities);
            productOfferAdapter.refreshList(productEntities);

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
        productAdapter = new ProductAdapter(new ArrayList<>(), this);
        binding.fragmentProductRecyclerViewProduct.setHasFixedSize(true);
        binding.fragmentProductRecyclerViewProduct.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.fragmentProductRecyclerViewProduct.setAdapter(productAdapter);
    }

    private void setupRecyclerViewProductOffer() {
        productOfferAdapter = new ProductOfferAdapter(new ArrayList<>(), this);
        binding.fragmentProductRecyclerViewProductOffer.setHasFixedSize(true);
        binding.fragmentProductRecyclerViewProductOffer.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.fragmentProductRecyclerViewProductOffer.setAdapter(productOfferAdapter);
    }
    //endregion

    //region Interface


    @Override
    public void onProductItemLinearParentClickListener(ProductEntity productEntity) {
        AddAndEditProductFragment addAndEditProductFragment = new AddAndEditProductFragment();
        bundle = new Bundle();

        bundle.putInt("id", productEntity.getId());
        bundle.putString("name", productEntity.getName());
        bundle.putDouble("price", productEntity.getPrice());
        bundle.putString("madeIn", productEntity.getMadeIn());
        bundle.putBoolean("isNew", productEntity.isNew());
        bundle.putString("categoryName", productEntity.getCategoryName());
        bundle.putInt("quantity", productEntity.getQuantity());
        bundle.putBoolean("isOffered", productEntity.isOffered());
        bundle.putDouble("offerPrice", productEntity.getOfferCost());
        // bundle.putString("endDate", productEntity.getExpireDateOffer());

        addAndEditProductFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction =
                requireActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.main_activity_frame_layout, addAndEditProductFragment, "ADD_AND_EDIT_ACCOUNT_FRAGMENT")
                .addToBackStack("fasdf")
                .commit();
    }

    @Override
    public void onProductOfferItemLinearParentClickListener(ProductEntity productEntity) {

        AddAndEditProductFragment addAndEditProductFragment = new AddAndEditProductFragment();
        bundle = new Bundle();

        bundle.putInt("id", productEntity.getId());
        bundle.putString("name", productEntity.getName());
        bundle.putDouble("price", productEntity.getPrice());
        bundle.putString("madeIn", productEntity.getMadeIn());
        bundle.putBoolean("isNew", productEntity.isNew());
        bundle.putString("categoryName", productEntity.getCategoryName());
        bundle.putInt("quantity", productEntity.getQuantity());
        bundle.putBoolean("isOffered", productEntity.isOffered());
        bundle.putDouble("offerPrice", productEntity.getOfferCost());

        addAndEditProductFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction =
                requireActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.main_activity_frame_layout, addAndEditProductFragment, "ADD_AND_EDIT_ACCOUNT_FRAGMENTS")
                .addToBackStack("fasdf")
                .commit();
    }
    //endregion
}