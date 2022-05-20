package com.mustafa.smallstore.view.main.product;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mustafa.smallstore.model.entity.ProductEntity;
import com.mustafa.smallstore.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    //region Variables
    private ProductRepository productRepository;
    //endregion

    //region Constructor
    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
    }
    //endregion

    //region Methods
    public void delete(ProductEntity productEntity) {
        productRepository.delete(productEntity);
    }

    public LiveData<List<ProductEntity>> getAllProducts() {
        return productRepository.getAllProducts();
    }

    //endregion


}
