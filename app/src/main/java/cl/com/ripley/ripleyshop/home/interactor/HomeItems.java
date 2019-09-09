package cl.com.ripley.ripleyshop.home.interactor;

import android.util.Log;

import java.util.List;

import cl.com.ripley.ripleyshop.general.model.RetrofitHelper;
import cl.com.ripley.ripleyshop.home.model.Cart;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;
import cl.com.ripley.ripleyshop.home.presenter.Home;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static cl.com.ripley.ripleyshop.general.model.Constants.EP_GET_PRODUCT_INFO;

public class HomeItems implements Home.Interactor {

    RetrofitHelper helper;

    public HomeItems(){
        helper = RetrofitHelper.getInstance(EP_GET_PRODUCT_INFO);

    }

    @Override
    public void getItemsInformation(String SkU) {
       Products products = helper.callEP(Products.class);
       products.getProducts(SkU).enqueue(new Callback<List<HomeProduct>>() {
           @Override
           public void onResponse(Call<List<HomeProduct>> call, Response<List<HomeProduct>> response) {

           }

           @Override
           public void onFailure(Call<List<HomeProduct>> call, Throwable t) {

           }
       });
    }

    interface Products {
            @Headers("Content-Type: application/json")
            @GET("products")
            Call<List<HomeProduct>> getProducts(@Query("partNumbers") String partNumbers);

            @GET("cart")
            Call<Cart> getProduct(@Query("cart_id") String cartId);
    }
}
