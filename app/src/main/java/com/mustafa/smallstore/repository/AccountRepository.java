package com.mustafa.smallstore.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mustafa.smallstore.model.dao.AccountDao;
import com.mustafa.smallstore.model.database.SmallStoreDatabase;
import com.mustafa.smallstore.model.entity.AccountEntity;

import java.util.List;

public class AccountRepository {

    //region Variables
    AccountDao accountDao;
    //endregion

    //region Constructor
    public AccountRepository(Application application) {
        SmallStoreDatabase instance = SmallStoreDatabase.getInstance(application);
        accountDao = instance.accountDao();
    }
    //endregion

    //region Methods
    public void insert(AccountEntity accountEntity) {
        new InsertAccountTask(accountDao).execute(accountEntity);
    }

    public void update(AccountEntity accountEntity) {
        new UpdateAccountTask(accountDao).execute(accountEntity);
    }

    public void delete(AccountEntity accountEntity) {
        new DeleteAccountTask(accountDao).execute(accountEntity);
    }

    public LiveData<List<AccountEntity>> getAllAccounts() {
        return accountDao.getAccounts();
    }

    public LiveData<List<AccountEntity>> getAccountsByName(String name) {
        return accountDao.getAccountsByName(name);
    }

    public LiveData<List<AccountEntity>> login(String name, String password) {
        return accountDao.login(name, password);
    }
    //endregion


    //region Async tasks
    private static class InsertAccountTask extends AsyncTask<AccountEntity, Void, Void> {
        //region Variables
        AccountDao accountDao;
        //endregion

        //region Constructor
        public InsertAccountTask(AccountDao accountDao) {
            this.accountDao = accountDao;
        }
        //endregion

        @Override
        protected Void doInBackground(AccountEntity... accountEntities) {
            accountDao.insert(accountEntities[0]);
            return null;
        }
    }

    private static class UpdateAccountTask extends AsyncTask<AccountEntity, Void, Void> {

        //region Variables
        AccountDao accountDao;
        //endregion

        //region Constructor
        public UpdateAccountTask(AccountDao accountDao) {
            this.accountDao = accountDao;
        }
        //endregion

        @Override
        protected Void doInBackground(AccountEntity... accountEntities) {
            accountDao.update(accountEntities[0]);
            return null;
        }


    }

    private static class DeleteAccountTask extends AsyncTask<AccountEntity, Void, Void> {
        //region Variables
        AccountDao accountDao;
        //endregion

        //region Constructor
        public DeleteAccountTask(AccountDao accountDao) {
            this.accountDao = accountDao;
        }

        @Override
        protected Void doInBackground(AccountEntity... accountEntities) {
            accountDao.delete(accountEntities[0]);
            return null;
        }
        //endregion
    }

    //endregion


}
