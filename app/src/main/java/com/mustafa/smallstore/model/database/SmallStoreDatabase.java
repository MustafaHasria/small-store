package com.mustafa.smallstore.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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

    static SmallStoreDatabase instance;

//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//        }
//    };

    //Get database as object
    public static synchronized SmallStoreDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SmallStoreDatabase.class,
                    "small_store_database")
                    //If there are error in Migration destruct all database and rebuild it
                    .fallbackToDestructiveMigration()
                    //Want do something when database building
//                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    //region DAO
    public abstract AccountDao accountDao();

    public abstract CategoryDao categoryDao();

    public abstract ProductDao productDao();
    //endregion


}
