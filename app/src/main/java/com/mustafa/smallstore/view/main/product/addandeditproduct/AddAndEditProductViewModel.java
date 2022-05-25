package com.mustafa.smallstore.view.main.product.addandeditproduct;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mustafa.smallstore.model.entity.CategoryEntity;
import com.mustafa.smallstore.model.entity.ProductEntity;
import com.mustafa.smallstore.repository.CategoryRepository;
import com.mustafa.smallstore.repository.ProductRepository;

import java.util.List;

public class AddAndEditProductViewModel extends AndroidViewModel {

    //region Variables
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    //endregion

    //region Constructor
    public AddAndEditProductViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
        productRepository = new ProductRepository(application);
    }
    //endregion

    //region Methods
    public LiveData<List<CategoryEntity>> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void insertProduct(ProductEntity productEntity) {
        productRepository.insert(productEntity);
    }

    public void updateProduct(ProductEntity productEntity) {
        productRepository.update(productEntity);
    }
    //endregion
}
