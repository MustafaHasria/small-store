package com.mustafa.smallstore.model.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mustafa.smallstore.model.dao.AccountDao;
import com.mustafa.smallstore.model.dao.CategoryDao;
import com.mustafa.smallstore.model.dao.ProductDao;
import com.mustafa.smallstore.model.entity.AccountEntity;
import com.mustafa.smallstore.model.entity.CategoryEntity;
import com.mustafa.smallstore.model.entity.ProductEntity;

@Database(entities = {
        AccountEntity.class,
        CategoryEntity.class,
        ProductEntity.class,
}, version = 1)
public abstract class SmallStoreDatabase extends RoomDatabase {

    //region Variables
    static SmallStoreDatabase instance;
    //endregion

    //region Get database as object
    //region Callback
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    //endregion

    //region DAO
    public abstract AccountDao accountDao();

    public abstract CategoryDao categoryDao();

    public abstract ProductDao productDao();
    //endregion

    //we implement here singleton objective
    public static synchronized SmallStoreDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            SmallStoreDatabase.class,
                            "small_store_database")
                    //If there are error in Migration destruct all database and rebuild it
                    .fallbackToDestructiveMigration()
                    //Want do something when database building
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    //endregion

    //region AsyncTask
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        //region Variables
        AccountDao accountDao;
        CategoryDao categoryDao;
        ProductDao productDao;
        //endregion

        //region Constructor

        public PopulateDbAsyncTask(SmallStoreDatabase smallStoreDatabase) {
            accountDao = smallStoreDatabase.accountDao();
            categoryDao = smallStoreDatabase.categoryDao();
            productDao = smallStoreDatabase.productDao();
        }

        //endregion

        @Override
        protected Void doInBackground(Void... voids) {
            accountDao.insert(new AccountEntity("admin", null, 0, "0123"));
            categoryDao.insert(new CategoryEntity("Juice", null, 0));
            productDao.insert(new ProductEntity("First Item for test", 200, "UAE", false, "", false,
                    0, "", null, null, null, 1, "Juice"));
            return null;
        }
    }
    //endregion


}
