package com.mustafa.smallstore.view.main.product;

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

    //region Component
    Dialog dialog;
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


        //Swipe For Delete Account(For Product NotProduct Offer)
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                showCenterDialogDelete("yes", "Are you sure want to delete \n             this Product?", viewHolder, viewHolder.getLayoutPosition());
            }
        }).attachToRecyclerView(binding.fragmentProductRecyclerViewProduct
        );


        //Swipe For Delete Account(For Product Offer)
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.UP | ItemTouchHelper.DOWN) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                showCenterDialogDeleteForProductOffer("yes", "Are you sure want to delete \n             this Product Offer?", viewHolder, viewHolder.getLayoutPosition());
            }
        }).attachToRecyclerView(binding.fragmentProductRecyclerViewProduct
        );


        //open addAndEditProduct Fragment
        binding.fragmentProductFloatingActionButtonAddProduct.setOnClickListener(view1 ->
        {

            AddAndEditProductFragment addAndEditProductFragment = new AddAndEditProductFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity_frame_layout, addAndEditProductFragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
    //endregion

    //region SetUp
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

    //region Methode
    private void showCenterDialogDelete(String yes, String t1, RecyclerView.ViewHolder viewHolder, int positionItem) {
        Button centerDialogForDeleteButtonDeleteOk;
        TextView centerDialogForDeleteTextviewText;
        LottieAnimationView centerDialogForDeleteAnimation;
        LottieAnimationView centerDialogForDeleteSuccessfulAnimation;

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

        if (yes.equals("Yes")) {
            centerDialogForDeleteAnimation.setAnimation(R.raw.ok_happy);
            centerDialogForDeleteTextviewText.setTextColor(getResources().getColor(R.color.green));
            centerDialogForDeleteButtonDeleteOk.setBackgroundResource(R.drawable.yes_button_background);

        }

        //If Click Ok To Delete
        centerDialogForDeleteButtonDeleteOk.setOnClickListener(view -> {
            productViewModel.delete(productAdapter.getProductPosition(viewHolder.getAdapterPosition()));
            centerDialogForDeleteButtonDeleteOk.setVisibility(View.GONE);
            centerDialogForDeleteTextviewText.setVisibility(View.GONE);
            centerDialogForDeleteAnimation.setVisibility(View.GONE);
            centerDialogForDeleteSuccessfulAnimation.setVisibility(View.VISIBLE);


            Handler handler = new Handler();
            Runnable r = () -> dialog.dismiss();
            handler.postDelayed(r, 1600);
        });


        dialog.setCancelable(true);

        //When i Click Out Of Dialog
        dialog.setOnCancelListener(dialogInterface -> {
            productAdapter.notifyItemChanged(positionItem);
        });
        //Setup Screen
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    //region Methode
    private void showCenterDialogDeleteForProductOffer(String yes, String t1, RecyclerView.ViewHolder viewHolder, int positionItem) {
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
        final MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sound_succeful_delete);
        vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);


        if (yes.equals("Yes")) {
            centerDialogForDeleteAnimation.setAnimation(R.raw.ok_happy);
            centerDialogForDeleteTextviewText.setTextColor(getResources().getColor(R.color.green));
            centerDialogForDeleteButtonDeleteOk.setBackgroundResource(R.drawable.yes_button_background);

        }

        //If Click Ok To Delete
        centerDialogForDeleteButtonDeleteOk.setOnClickListener(view -> {
            productViewModel.delete(productOfferAdapter.getProductPosition(viewHolder.getAdapterPosition()));
            centerDialogForDeleteButtonDeleteOk.setVisibility(View.GONE);
            centerDialogForDeleteTextviewText.setVisibility(View.GONE);
            centerDialogForDeleteAnimation.setVisibility(View.GONE);
            centerDialogForDeleteSuccessfulAnimation.setVisibility(View.VISIBLE);


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
            productOfferAdapter.notifyItemChanged(positionItem);
        });
        //Setup Screen
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
    //endregion
}