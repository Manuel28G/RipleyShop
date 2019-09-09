package cl.com.ripley.ripleyshop.home.interactor;

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

    private RetrofitHelper mHelper;
    private Home.Presenter mPresenter;
    public static final String TAG = HomeItems.class.toString();

    public HomeItems(Home.Presenter presenter){
        mHelper = RetrofitHelper.getInstance(EP_GET_PRODUCT_INFO);
        mPresenter = presenter;
    }

    @Override
    public void getItemsInformation(String SkU) {
       Products products = mHelper.callEP(Products.class);
       products.getProducts(SkU).enqueue(new Callback<List<HomeProduct>>() {
           @Override
           public void onResponse(Call<List<HomeProduct>> call, Response<List<HomeProduct>> response) {
               mPresenter.addPublications(response.body());
           }

           @Override
           public void onFailure(Call<List<HomeProduct>> call, Throwable t) {
                mPresenter.errorConnection(TAG);
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
