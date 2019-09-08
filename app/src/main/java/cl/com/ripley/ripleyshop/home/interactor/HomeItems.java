package cl.com.ripley.ripleyshop.home.interactor;

import android.util.Log;

import cl.com.ripley.ripleyshop.general.model.RetrofitHelper;
import cl.com.ripley.ripleyshop.home.presenter.Home;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class HomeItems implements Home.Interactor {

    RetrofitHelper helper;

    public HomeItems(){
        helper = RetrofitHelper.getInstance();

    }

    @Override
    public void getItems() {
       Products products = helper.callEP(Products.class);
       products.getProducts("2000372411631P").enqueue(new Callback<String>() {
           @Override
           public void onResponse(Call<String> call, Response<String> response) {
               Log.d("",response.body());
           }

           @Override
           public void onFailure(Call<String> call, Throwable t) {
               Log.d("",t.getMessage());

           }
       });
    }

    interface Products {
            @GET("v2/products/{sku}")
            Call<String> getProducts(@Path("sku") String sku);
    }
}
