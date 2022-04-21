package com.mustafa.smallstore.view.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.databinding.ActivityAccountBinding;
import com.mustafa.smallstore.model.entity.AccountEntity;
import com.mustafa.smallstore.view.account.addandeditaccount.AddAndEditAccountActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountActivity extends AppCompatActivity {

    //region Variables
    AccountAdapter accountAdapter;
    List<AccountEntity> accountEntityList;
    AccountViewModel accountViewModel;
    ActivityAccountBinding binding;
    //endregion

    //region Life cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        accountEntityList = new ArrayList<>();
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //مشان نعمل initialize لل ViewModel
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        accountViewModel.getAllAccounts().observe(this, accountEntities -> {
            accountAdapter.refreshList(accountEntities);
        });

        binding.activityAccountFloatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(AccountActivity.this, AddAndEditAccountActivity.class);
            startActivity(intent);
        });
        setupRecyclerView();

    }

    //endregion

    //region Setups
    private void setupRecyclerView() {
        accountAdapter = new AccountAdapter(accountEntityList);
        binding.activityAccountRecyclerViewMain.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.activityAccountRecyclerViewMain.setHasFixedSize(true);
        binding.activityAccountRecyclerViewMain.setAdapter(accountAdapter);
    }

    //endregion
}