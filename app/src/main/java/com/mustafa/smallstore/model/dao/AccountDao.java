package com.mustafa.smallstore.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mustafa.smallstore.model.entity.AccountEntity;

import java.util.List;

@Dao
public interface AccountDao {

    @Insert
    void insert(AccountEntity accountEntity);

    @Update
    void update(AccountEntity accountEntity);

    @Delete
    void delete(AccountEntity accountEntity);

    @Query("Select * from account_table order by name")
    LiveData<List<AccountEntity>> getAccounts();

    @Query("Select * from account_table where name =:name  order by name")
    LiveData<List<AccountEntity>> getAccountsByName(String name);

    @Query("SELECT * FROM ACCOUNT_TABLE WHERE name =:name and password =:password")
    LiveData<List<AccountEntity>> login(String name, String password);

}
