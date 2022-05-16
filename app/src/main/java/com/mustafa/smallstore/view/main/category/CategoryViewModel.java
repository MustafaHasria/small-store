package com.mustafa.smallstore.view.main.category;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mustafa.smallstore.model.entity.CategoryEntity;
import com.mustafa.smallstore.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    //region Variables
    CategoryRepository categoryRepository;
    //endregion

    //region Constructor
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
    }
    //endregion

    //region Methods
    public void delete(CategoryEntity categoryEntity) {
        categoryRepository.delete(categoryEntity);
    }

    public LiveData<List<CategoryEntity>> getAllCategories() {
        return categoryRepository.getAllCategories();
    }
    //endregion
}
