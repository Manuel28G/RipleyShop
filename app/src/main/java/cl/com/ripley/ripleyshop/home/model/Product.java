package cl.com.ripley.ripleyshop.home.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("product_id")
    private String mProductId;
    @SerializedName("cart_id")
    private String mCartId;
    @SerializedName("price")
    private double mPrice;
    @SerializedName("price_discount")
    private double mPriceDiscount;
    @SerializedName("price_discount_ripley")
    private double mPriceDiscountRipley;
    @SerializedName("sku")
    private String mSKU;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("quantity")
    private int mQuantity;
    @SerializedName("date")
    private String mDate;
    @SerializedName("is_active")
    private String mIsActive;

    public Product(){

    }

    public String getProductId() {
        return mProductId;
    }

    public void setProductId(String mProductId) {
        this.mProductId = mProductId;
    }

    public String getCartId() {
        return mCartId;
    }

    public void setCartId(String mCartId) {
        this.mCartId = mCartId;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public double getPriceDiscount() {
        return mPriceDiscount;
    }

    public void setPriceDiscount(double mPriceDiscount) {
        this.mPriceDiscount = mPriceDiscount;
    }

    public double getPriceDiscountRipley() {
        return mPriceDiscountRipley;
    }

    public void setPriceDiscountRipley(double mPriceDiscountRipley) {
        this.mPriceDiscountRipley = mPriceDiscountRipley;
    }

    public String getSKU() {
        return mSKU;
    }

    public void setSKU(String mSKU) {
        this.mSKU = mSKU;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getIsActive() {
        return mIsActive;
    }

    public void setIsActive(String mIsActive) {
        this.mIsActive = mIsActive;
    }
}
