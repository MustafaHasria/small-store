package com.mustafa.smallstore.view.account;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.databinding.ActivityAccountBinding;
import com.mustafa.smallstore.model.entity.AccountEntity;
import com.mustafa.smallstore.view.account.addandeditaccount.AddAndEditAccountActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountActivity extends AppCompatActivity implements AccountAdapter.AccountOnClickListener {

    //region Variables
    AccountAdapter accountAdapter;
    List<AccountEntity> accountEntityList;
    AccountViewModel accountViewModel;
    ActivityAccountBinding binding;
    Bundle bundle;
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

        //Swipe For Delete Account
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                accountViewModel.delete(accountAdapter.getAccountPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(AccountActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.activityAccountRecyclerViewMain);
    }

    //endregion

    //region Setups
    private void setupRecyclerView() {
        accountAdapter = new AccountAdapter(accountEntityList, this);
        binding.activityAccountRecyclerViewMain.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.activityAccountRecyclerViewMain.setHasFixedSize(true);
        binding.activityAccountRecyclerViewMain.setAdapter(accountAdapter);
    }
    //endregion

    //region Interface
    @Override
    public void onAccountItemCardMainContainerClickListener(AccountEntity accountEntity) {
        bundle = new Bundle();
        bundle.putString("name", accountEntity.getName());
        bundle.putString("password", accountEntity.getPassword());
        bundle.putInt("role", accountEntity.getRole());
        Intent intent = new Intent(AccountActivity.this, AddAndEditAccountActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //endregion

    //region Methode
    //endregion
}