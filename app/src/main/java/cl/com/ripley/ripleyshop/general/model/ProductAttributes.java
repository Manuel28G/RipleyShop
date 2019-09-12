package cl.com.ripley.ripleyshop.general.model;

import com.google.gson.annotations.SerializedName;

public class ProductAttributes {

    @SerializedName("displayable")
    private boolean mDisplayable;
    @SerializedName("name")
    private String mName;
    @SerializedName("usage")
    private String mUsage;
    @SerializedName("value")
    private String mValue;

    public ProductAttributes(){

    }

    public boolean isDisplayable() {
        return mDisplayable;
    }

    public String getName() {
        return mName;
    }

    public String getUsage() {
        return mUsage;
    }

    public String getValue() {
        return mValue;
    }
}
