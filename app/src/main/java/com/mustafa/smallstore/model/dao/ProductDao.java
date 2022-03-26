package com.mustafa.smallstore.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mustafa.smallstore.model.entity.ProductEntity;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(ProductEntity productEntity);

    @Update
    void update(ProductEntity productEntity);

    @Delete
    void delete(ProductEntity productEntity);

    @Query("Select * From product_table order by name")
    LiveData<List<ProductEntity>> getProducts();
}
