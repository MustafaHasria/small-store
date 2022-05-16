package com.mustafa.smallstore.view.main.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.databinding.FragmentCategoryBinding;
import com.mustafa.smallstore.model.entity.CategoryEntity;
import com.mustafa.smallstore.view.main.account.addandeditaccount.AddAndEditAccountFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryAdapter.CategoryOnClickListener {
    //region Variables
    CategoryViewModel categoryViewModel;
    CategoryAdapter categoryAdapter;
    FragmentCategoryBinding binding;
    List<CategoryEntity> categoryEntityList;
    Bundle bundle;
    //endregion


    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        binding = FragmentCategoryBinding.bind(view);
        categoryEntityList = new ArrayList<>();
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        categoryViewModel.getAllCategories().observe(getViewLifecycleOwner(), categoryEntities -> {
            categoryAdapter.refreshList(categoryEntities);
        });

        //Swipe For Delete Account
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                categoryViewModel.delete(categoryAdapter.getCategoryPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.fragmentCategoryRecyclerView);

        setupRecyclerView();

        binding.fragmentCategoryFloatingActionButtonAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAndEditAccountFragment addAndEditAccountFragment = new AddAndEditAccountFragment();
                bundle = new Bundle();
                FragmentTransaction fragmentTransaction =
                        requireActivity().getSupportFragmentManager().beginTransaction();
            }
        });
        return view;
    }


    //region Setup
    private void setupRecyclerView() {
        categoryAdapter = new CategoryAdapter(categoryEntityList, this);
        binding.fragmentCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.fragmentCategoryRecyclerView.setHasFixedSize(true);
        binding.fragmentCategoryRecyclerView.setAdapter(categoryAdapter);
    }

    //endregion

    //region Adapter click listener
    @Override
    public void onItemRecyclerViewCategoryCardViewMainContainerClickListener(CategoryEntity categoryEntity) {

    }
    //endregion
}