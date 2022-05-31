package com.mustafa.smallstore.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mustafa.smallstore.model.entity.CategoryEntity;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(CategoryEntity categoryEntity);

    @Update
    void update(CategoryEntity categoryEntity);

    @Delete
    void delete(CategoryEntity categoryEntity);

    @Query("Select * from category_table order by name")
    LiveData<List<CategoryEntity>> getCategories();

    @Query("SELECT * FROM category_table")
    LiveData<List<CategoryEntity>> getAllNotes();

}
