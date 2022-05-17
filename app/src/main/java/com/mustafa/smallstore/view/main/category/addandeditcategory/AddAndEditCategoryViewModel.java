package com.mustafa.smallstore.view.main.category.addandeditcategory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.mustafa.smallstore.model.entity.CategoryEntity;
import com.mustafa.smallstore.repository.CategoryRepository;

public class AddAndEditCategoryViewModel extends AndroidViewModel {

    //region Repository
    CategoryRepository categoryRepository;
    //endregion

    //region Constructor
    public AddAndEditCategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
    }
    //endregion

    //region Methods
    public void insert(CategoryEntity categoryEntity) {
        categoryRepository.insert(categoryEntity);
    }

    public void update(CategoryEntity categoryEntity) {
        categoryRepository.update(categoryEntity);
    }
    //endregion
}
