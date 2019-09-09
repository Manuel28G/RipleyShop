package cl.com.ripley.ripleyshop.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {

    @SerializedName("customer_id")
    private String mCustomerId;
    @SerializedName("cart_id")
    private String mCartId;
    @SerializedName("total_price")
    private double mTotalPrice;
    @SerializedName("date")
    private String mDate;
    @SerializedName("customer_rut")
    private String mCustomerRut;
    @SerializedName("payment_method")
    private String mPaymentMethod;
    @SerializedName("state")
    private String mState;
    @SerializedName("is_active")
    private String mIsActive;
    @SerializedName("products")
    private List<Product> mProducts;

    public Cart(){

    }

    public String getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(String mCustomerId) {
        this.mCustomerId = mCustomerId;
    }

    public String getCartId() {
        return mCartId;
    }

    public void setCartId(String mCartId) {
        this.mCartId = mCartId;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(double mTotalPrice) {
        this.mTotalPrice = mTotalPrice;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getCustomerRut() {
        return mCustomerRut;
    }

    public void setCustomerRut(String mCustomerRut) {
        this.mCustomerRut = mCustomerRut;
    }

    public String getPaymentMethod() {
        return mPaymentMethod;
    }

    public void setPaymentMethod(String mPaymentMethod) {
        this.mPaymentMethod = mPaymentMethod;
    }

    public String getState() {
        return mState;
    }

    public void setState(String mState) {
        this.mState = mState;
    }

    public String getIsActive() {
        return mIsActive;
    }

    public void setIsActive(String mIsActive) {
        this.mIsActive = mIsActive;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> mProducts) {
        this.mProducts = mProducts;
    }
}
