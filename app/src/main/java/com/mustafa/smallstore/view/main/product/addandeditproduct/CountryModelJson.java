package com.mustafa.smallstore.view.main.product.addandeditproduct;

import androidx.annotation.NonNull;

public class CountryModelJson {

    private String name;
    private String code;

    public CountryModelJson(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
