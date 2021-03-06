package com.mustafa.smallstore.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "product_table")
public class ProductEntity {

    //region Properties
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "made_in")
    private String madeIn;

    @ColumnInfo(name = "is_offered")
    private boolean isOffered;

    @ColumnInfo(name = "expire_date_offer")
    private String expireDateOffer;

    @ColumnInfo(name = "start_date_offer")
    private String startDateOffer;

    @ColumnInfo(name = "is_new")
    private boolean isNew;

    @ColumnInfo(name = "offer_cost")
    private double offerCost;

    @ColumnInfo(name = "qr_code")
    private String qrCode;

    //  @ColumnInfo(name = "image1")
    //  private byte[] image1;

    //  @ColumnInfo(name = "image2")
    //  private byte[] image2;

    //  @ColumnInfo(name = "image3")
    //  private byte[] image3;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    @ColumnInfo(name = "category_name")
    private String categoryName;

    @ColumnInfo(name = "quantity")
    private int quantity;

    //endregion

    //region Constructor

    public ProductEntity(String name, double price, String madeIn, boolean isOffered, String expireDateOffer, String startDateOffer, boolean isNew, double offerCost, String qrCode, int categoryId, String categoryName, int quantity) {
        this.name = name;
        this.price = price;
        this.madeIn = madeIn;
        this.isOffered = isOffered;
        this.expireDateOffer = expireDateOffer;
        this.startDateOffer = startDateOffer;
        this.isNew = isNew;
        this.offerCost = offerCost;
        this.qrCode = qrCode;
//        this.image1 = image1;
//        this.image2 = image2;
//        this.image3 = image3;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.quantity = quantity;
    }


    //endregion

    //region Getter & Setter

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public boolean isOffered() {
        return isOffered;
    }

    public void setOffered(boolean offered) {
        isOffered = offered;
    }

    public String getExpireDateOffer() {
        return expireDateOffer;
    }

    public void setExpireDateOffer(String expireDateOffer) {
        this.expireDateOffer = expireDateOffer;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public double getOfferCost() {
        return offerCost;
    }

    public void setOfferCost(double offerCost) {
        this.offerCost = offerCost;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

//    public byte[] getImage1() {
//        return image1;
//    }
//
//    public void setImage1(byte[] image1) {
//        this.image1 = image1;
//    }
//
//    public byte[] getImage2() {
//        return image2;
//    }
//
//    public void setImage2(byte[] image2) {
//        this.image2 = image2;
//    }
//
//    public byte[] getImage3() {
//        return image3;
//    }
//
//    public void setImage3(byte[] image3) {
//        this.image3 = image3;
//    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStartDateOffer() {
        return startDateOffer;
    }

    public void setStartDateOffer(String startDateOffer) {
        this.startDateOffer = startDateOffer;
    }


    //endregion
}
