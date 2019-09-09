package cl.com.ripley.ripleyshop.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeProduct {

    @SerializedName("uniqueID")
    private String mUniqueID;
    @SerializedName("name")
    private String mName;
    @SerializedName("fullImage")
    private String mFullImage;
    @SerializedName("thumbnailImage")
    private String mThumbnailImage;
    @SerializedName("prices")
    private Price mPrices;

    public HomeProduct(){

    }

    public String getUniqueID() {
        return mUniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.mUniqueID = uniqueID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getFullImage() {
        return mFullImage;
    }

    public void setFullImage(String fullImage) {
        this.mFullImage = fullImage;
    }

    public String getThumbnailImage() {
        return mThumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.mThumbnailImage = thumbnailImage;
    }

    public Price getPrices() {
        return mPrices;
    }

    public void setPrices(Price prices) {
        this.mPrices = prices;
    }
}
