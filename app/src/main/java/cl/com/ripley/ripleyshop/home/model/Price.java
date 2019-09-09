package cl.com.ripley.ripleyshop.home.model;

import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("offerPrice")
    private int mOfferPrice;
    @SerializedName("listPrice")
    private int mListPrice;
    @SerializedName("discount")
    private int mDiscount;
    @SerializedName("discountPercentage")
    private int mDiscountPercentage;
    @SerializedName("formattedOfferPrice")
    private String mFormattedOfferPrice;
    @SerializedName("formattedListPrice")
    private String mFormattedListPrice;
    @SerializedName("formattedDiscount")
    private String mFormattedDiscount;

    public Price (){

    }

    public int getOfferPrice() {
        return mOfferPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.mOfferPrice = offerPrice;
    }

    public int getListPrice() {
        return mListPrice;
    }

    public void setListPrice(int listPrice) {
        this.mListPrice = listPrice;
    }

    public int getDiscount() {
        return mDiscount;
    }

    public void setDiscount(int discount) {
        this.mDiscount = discount;
    }

    public int getDiscountPercentage() {
        return mDiscountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.mDiscountPercentage = discountPercentage;
    }

    public String getFormattedOfferPrice() {
        return mFormattedOfferPrice;
    }

    public void setFormattedOfferPrice(String formattedOfferPrice) {
        this.mFormattedOfferPrice = formattedOfferPrice;
    }

    public String getFormattedListPrice() {
        return mFormattedListPrice;
    }

    public void setFormattedListPrice(String formattedListPrice) {
        this.mFormattedListPrice = formattedListPrice;
    }

    public String getFormattedDiscount() {
        return mFormattedDiscount;
    }

    public void setFormattedDiscount(String formattedDiscount) {
        this.mFormattedDiscount = formattedDiscount;
    }
}
