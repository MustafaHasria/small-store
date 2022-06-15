package com.mustafa.smallstore.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class CategoryEntity {

    @ColumnInfo(name = "name")
    public String name;
    //region Properties
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    //endregion

    //region Constructor
    public CategoryEntity(String name, byte[] image) {
        this.name = name;
        this.image = image;

    }

    //endregion

    //region Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    //endregion
}
