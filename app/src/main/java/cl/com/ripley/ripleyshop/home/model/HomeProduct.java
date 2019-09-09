package cl.com.ripley.ripleyshop.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cl.com.ripley.ripleyshop.general.model.ProductAttributes;

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
    @SerializedName("attributes")
    private List<ProductAttributes> mAtributes;
    @SerializedName("images")
    private String[] mImages;

    public HomeProduct(){

    }

    public String[] getImages(){
        return mImages;
    }
    public String getImage(int position){
        return mImages[position];
    }
    public List<ProductAttributes> getAtributes(){
        return mAtributes;
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
