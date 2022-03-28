package com.mustafa.smallstore.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.mustafa.smallstore.model.dao.ProductDao;
import com.mustafa.smallstore.model.database.SmallStoreDatabase;
import com.mustafa.smallstore.model.entity.ProductEntity;

public class ProductRepository {

    //region Variables
    ProductDao productDao;
    //endregion

    //region Constructor
    public ProductRepository(Application application) {
        SmallStoreDatabase instance = SmallStoreDatabase.getInstance(application);
        productDao = instance.productDao();
    }
    //endregion

    //region Methods
    public void insert(ProductEntity productEntity) {
        new InsertProductTask(productDao).execute(productEntity);
    }

    public void update(ProductEntity productEntity) {
        new UpdateProductTask(productDao).execute(productEntity);
    }

    public void delete(ProductEntity productEntity) {
        new DeleteProductTask(productDao).execute(productEntity);
    }
    //endregion

    //region Async tasks
    private static class InsertProductTask extends AsyncTask<ProductEntity, Void, Void> {

        //region Variables
        ProductDao productDao;
        //endregion

        //region Constructor
        public InsertProductTask(ProductDao productDao) {
            this.productDao = productDao;
        }
        //endregion

        @Override
        protected Void doInBackground(ProductEntity... productEntities) {
            productDao.insert(productEntities[0]);
            return null;
        }
    }

    private static class UpdateProductTask extends AsyncTask<ProductEntity, Void, Void> {
        //region Variables
        ProductDao productDao;
        //endregion

        //region Constructor
        public UpdateProductTask(ProductDao productDao) {
            this.productDao = productDao;
        }
        //endregion

        @Override
        protected Void doInBackground(ProductEntity... productEntities) {
            productDao.update(productEntities[0]);
            return null;
        }
    }

    private static class DeleteProductTask extends AsyncTask<ProductEntity, Void, Void> {

        //region Variables
        ProductDao productDao;
        //endregion

        //region Constructor
        public DeleteProductTask(ProductDao productDao) {
            this.productDao = productDao;
        }
        //endregion

        @Override
        protected Void doInBackground(ProductEntity... productEntities) {
            productDao.delete(productEntities[0]);
            return null;
        }
    }

    //endregion

}





