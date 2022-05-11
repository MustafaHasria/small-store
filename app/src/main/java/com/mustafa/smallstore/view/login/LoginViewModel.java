package com.mustafa.smallstore.view.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mustafa.smallstore.model.entity.AccountEntity;
import com.mustafa.smallstore.repository.AccountRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    //region Variable
    private AccountRepository accountRepository;
    //endregion

    //region Constructor
    public LoginViewModel(@NonNull Application application) {
        super(application);
        accountRepository = new AccountRepository(application);
    }
    //endregion

    //region Methode
    public LiveData<List<AccountEntity>> login(String name, String password) {
        return accountRepository.login(name, password);
    }
    //endregion
}
