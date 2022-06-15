package com.mustafa.smallstore.view.main.category;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
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
    List<CategoryEntity> categoryEntityList;
    Bundle bundle;
    Vibrator vibrator;
    //endregion

    //region Component
    Dialog dialog;
    FragmentCategoryBinding binding;
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
            if (categoryEntities.size() == 0) {
                binding.fragmentCategoryAnimation.setAnimation(R.raw.error);
                binding.fragmentCategoryAnimation.setVisibility(View.VISIBLE);
            } else {
                binding.fragmentCategoryAnimation.setVisibility(View.GONE);

                categoryAdapter.refreshList(categoryEntities);
            }
        });

        //Swipe For Delete Account
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                showCenterDialogDelete("yes", "Are you sure want to delete \n             this Category?", viewHolder, viewHolder.getLayoutPosition());
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

    //region Methods
    private void showCenterDialogDelete(String yes, String t1, RecyclerView.ViewHolder viewHolder, int positionItem) {
        Button centerDialogForDeleteButtonDeleteOk;
        TextView centerDialogForDeleteTextviewText;
        LottieAnimationView centerDialogForDeleteAnimation;
        LottieAnimationView centerDialogForDeleteSuccessfulAnimation;
        Vibrator vibrator;

        //Setup The Dialog in Center
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.center_dialog_for_delete);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.DialogAnimation;

        //FindViewById
        centerDialogForDeleteButtonDeleteOk = dialog.findViewById(R.id.center_dialog_for_delete_button_delete_ok);
        centerDialogForDeleteTextviewText = dialog.findViewById(R.id.center_dialog_for_delete_textview_text);
        centerDialogForDeleteAnimation = dialog.findViewById(R.id.center_dialog_for_delete_animation);
        centerDialogForDeleteSuccessfulAnimation = dialog.findViewById(R.id.center_dialog_for_delete_successful_animation);
        centerDialogForDeleteTextviewText.setText(t1);
        vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);

        if (yes.equals("Yes")) {
            centerDialogForDeleteAnimation.setAnimation(R.raw.ok_happy);
            centerDialogForDeleteTextviewText.setTextColor(getResources().getColor(R.color.green));
            centerDialogForDeleteButtonDeleteOk.setBackgroundResource(R.drawable.yes_button_background);

        }

        //If Click Ok To Delete
        centerDialogForDeleteButtonDeleteOk.setOnClickListener(view -> {
            categoryViewModel.delete(categoryAdapter.getCategoryPosition(viewHolder.getAdapterPosition()));
            centerDialogForDeleteButtonDeleteOk.setVisibility(View.GONE);
            centerDialogForDeleteTextviewText.setVisibility(View.GONE);
            centerDialogForDeleteAnimation.setVisibility(View.GONE);
            centerDialogForDeleteSuccessfulAnimation.setVisibility(View.VISIBLE);
            //For Sound Player
            final MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sound_succeful_delete);
            Handler handler = new Handler();

            Runnable r = () ->

                    dialog.dismiss();

            if (centerDialogForDeleteButtonDeleteOk.isClickable()) {

                // this is the only type of the vibration which requires system version Oreo (API 26)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    final VibrationEffect vibrationEffect1;
                    // this effect creates the vibration of default amplitude for 1000ms(1 sec)
                    vibrationEffect1 = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);
                    mediaPlayer.start();
                    // it is safe to cancel other vibrations currently taking place
                    vibrator.cancel();
                    vibrator.vibrate(vibrationEffect1);
                }

            }

            handler.postDelayed(r, 1600);
        });


        dialog.setCancelable(true);

        //When i Click Out Of Dialog
        dialog.setOnCancelListener(dialogInterface -> {
            categoryAdapter.notifyItemChanged(positionItem);
        });
        //Setup Screen
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
    //endregion

}