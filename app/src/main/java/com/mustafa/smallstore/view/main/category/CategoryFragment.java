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
import com.mustafa.smallstore.view.main.category.addandeditcategory.AddAndEditCategoryFragment;

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


    //region Life Cycle
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

        //when click action button for move to another fragment
        binding.fragmentCategoryFloatingActionButtonAddCategory.setOnClickListener(view1 ->
        {
            AddAndEditCategoryFragment addAndEditCategoryFragment = new AddAndEditCategoryFragment();

            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();

            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .add(R.id.main_activity_frame_layout, addAndEditCategoryFragment, "ADD_AND_EDIT_CATEGORY_FRAGMENT")
                    .addToBackStack("dsf").commit();
        });
        return view;
    }
    //endregion


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

        AddAndEditCategoryFragment andEditCategoryFragment = new AddAndEditCategoryFragment();
        bundle = new Bundle();
        FragmentTransaction fragmentTransaction =
                requireActivity().getSupportFragmentManager().beginTransaction();

        bundle.putInt("id", categoryEntity.getId());
        bundle.putString("name", categoryEntity.getName());

        andEditCategoryFragment.setArguments(bundle);

        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.main_activity_frame_layout, andEditCategoryFragment, "ADD_AND_EDIT_CATEGORY_FRAGMENT")
                .addToBackStack("fasdf")
                .commit();
    }
    //endregion
}