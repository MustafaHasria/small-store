package com.mustafa.smallstore.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mustafa.smallstore.model.dao.CategoryDao;
import com.mustafa.smallstore.model.database.SmallStoreDatabase;
import com.mustafa.smallstore.model.entity.CategoryEntity;

import java.util.List;

public class CategoryRepository {

    //region Variable
    CategoryDao categoryDao;
    //endregion

    //region Constructor
    public CategoryRepository(Application application) {
        SmallStoreDatabase instance = SmallStoreDatabase.getInstance(application);
        categoryDao = instance.categoryDao();
    }
    //endregion

    //region Methods
    public void insert(CategoryEntity categoryEntity) {
        new InsertCategoryTask(categoryDao).execute(categoryEntity);
    }

    public void update(CategoryEntity categoryEntity) {
        new UpdateCategoryTask(categoryDao).execute(categoryEntity);
    }

    public void delete(CategoryEntity categoryEntity) {
        new DeleteAccountTask(categoryDao).execute(categoryEntity);
    }

    public LiveData<List<CategoryEntity>> getAllCategories() {
        return categoryDao.getCategories();
    }

    //endregion

    //region Async tasks
    private static class InsertCategoryTask extends AsyncTask<CategoryEntity, Void, Void> {
        //region Variables
        CategoryDao categoryDao;
        //endregion

        //region Constructor
        public InsertCategoryTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }
        //endregion

        @Override
        protected Void doInBackground(CategoryEntity... categoryEntities) {
            categoryDao.insert(categoryEntities[0]);
            return null;
        }
    }

    private static class UpdateCategoryTask extends AsyncTask<CategoryEntity, Void, Void> {

        //region Variables
        CategoryDao categoryDao;
        //endregion

        //region Constructor
        public UpdateCategoryTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }
        //endregion

        @Override
        protected Void doInBackground(CategoryEntity... categoryEntities) {
            categoryDao.update(categoryEntities[0]);
            return null;
        }
    }

    private static class DeleteAccountTask extends AsyncTask<CategoryEntity, Void, Void> {

        //region Variables
        CategoryDao categoryDao;
        //endregion

        //region Constructor
        public DeleteAccountTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }
        //endregion

        @Override
        protected Void doInBackground(CategoryEntity... categoryEntities) {
            categoryDao.delete(categoryEntities[0]);
            return null;
        }
    }

    //endregion

}
