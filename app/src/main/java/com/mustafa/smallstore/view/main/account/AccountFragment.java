package com.mustafa.smallstore.view.main.account;

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
                accountViewModel.delete(accountAdapter.getAccountPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
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


}