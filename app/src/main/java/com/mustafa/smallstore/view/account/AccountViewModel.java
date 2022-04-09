package com.mustafa.smallstore.view.account;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mustafa.smallstore.model.entity.AccountEntity;
import com.mustafa.smallstore.repository.AccountRepository;

import java.util.List;

public class AccountViewModel extends AndroidViewModel {

    //region Variables
    private AccountRepository accountRepository;
    //endregion

    //region Constructor
    public AccountViewModel(@NonNull Application application) {
        super(application);
        accountRepository = new AccountRepository(application);
    }
    //endregion

    //region Methods
    public void insert(AccountEntity accountEntity) {
        accountRepository.insert(accountEntity);
    }

    public void update(AccountEntity accountEntity) {
        accountRepository.update(accountEntity);
    }

    public void delete(AccountEntity accountEntity) {
        accountRepository.delete(accountEntity);
    }

    public LiveData<List<AccountEntity>> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }
    //endregion


}
