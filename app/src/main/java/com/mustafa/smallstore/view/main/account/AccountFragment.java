package com.mustafa.smallstore.view.main.account;

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
import com.mustafa.smallstore.databinding.FragmentAccountBinding;
import com.mustafa.smallstore.model.entity.AccountEntity;
import com.mustafa.smallstore.view.main.account.addandeditaccount.AddAndEditAccountFragment;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment implements AccountAdapter.AccountOnClickListener {

    //region Variables
    AccountAdapter accountAdapter;
    List<AccountEntity> accountEntityList;
    AccountViewModel accountViewModel;
    FragmentAccountBinding binding;
    Bundle bundle;
    //endregion

    //region Component
    Dialog dialog;
    //endregion

    //region Life cycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        binding = FragmentAccountBinding.bind(view);
        //initialize for accountEntityList
        accountEntityList = new ArrayList<>();
        //مشان نعمل initialize لل ViewModel
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        accountViewModel.getAllAccounts().observe(getViewLifecycleOwner(), accountEntities -> {
            accountAdapter.refreshList(accountEntities);
        });


        setupRecyclerView();

        //Swipe For Delete Account
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                showCenterDialogDelete("yes", "Are you sure want to delete \n             this Account?", viewHolder, viewHolder.getLayoutPosition());
            }
        }).attachToRecyclerView(binding.activityAccountRecyclerViewMain);


        binding.activityAccountFloatingActionButton.setOnClickListener(view1 -> {

            AddAndEditAccountFragment addAndEditAccountFragment = new AddAndEditAccountFragment();
            FragmentTransaction fragmentTransaction =
                    requireActivity().getSupportFragmentManager().beginTransaction();

            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .add(R.id.main_activity_frame_layout, addAndEditAccountFragment, "ADD_AND_EDIT_ACCOUNT_FRAGMENT")
                    .addToBackStack("fasdff")
                    .commit();
        });
        return view;
    }
    //endregion

    //region Setups
    private void setupRecyclerView() {
        accountAdapter = new AccountAdapter(accountEntityList, this);
        binding.activityAccountRecyclerViewMain.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.activityAccountRecyclerViewMain.setHasFixedSize(true);
        binding.activityAccountRecyclerViewMain.setAdapter(accountAdapter);
    }
    //endregion

    //region Interface
    @Override
    public void onAccountItemCardMainContainerClickListener(AccountEntity accountEntity) {

        AddAndEditAccountFragment addAndEditAccountFragment = new AddAndEditAccountFragment();
        bundle = new Bundle();
        FragmentTransaction fragmentTransaction =
                requireActivity().getSupportFragmentManager().beginTransaction();

        bundle.putInt("id", accountEntity.getId());
        bundle.putString("name", accountEntity.getName());
        bundle.putString("password", accountEntity.getPassword());
        bundle.putInt("role", accountEntity.getRole());

        addAndEditAccountFragment.setArguments(bundle);

        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.main_activity_frame_layout, addAndEditAccountFragment, "ADD_AND_EDIT_ACCOUNT_FRAGMENT")
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
        final MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sound_succeful_delete);

        if (yes.equals("Yes")) {
            centerDialogForDeleteAnimation.setAnimation(R.raw.ok_happy);
            centerDialogForDeleteTextviewText.setTextColor(getResources().getColor(R.color.green));
            centerDialogForDeleteButtonDeleteOk.setBackgroundResource(R.drawable.yes_button_background);

        }

        //If Click Ok To Delete
        centerDialogForDeleteButtonDeleteOk.setOnClickListener(view -> {
            accountViewModel.delete(accountAdapter.getAccountPosition(viewHolder.getAdapterPosition()));
            centerDialogForDeleteButtonDeleteOk.setVisibility(View.GONE);
            centerDialogForDeleteTextviewText.setVisibility(View.GONE);
            centerDialogForDeleteAnimation.setVisibility(View.GONE);
            centerDialogForDeleteSuccessfulAnimation.setVisibility(View.VISIBLE);


            Handler handler = new Handler();
            Runnable r = ()
                    -> dialog.dismiss();

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
            accountAdapter.notifyItemChanged(positionItem);
        });
        //Setup Screen
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
    //endregion


}