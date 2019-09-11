package cl.com.ripley.ripleyshop.cart.presenter;

import android.content.Context;

import cl.com.ripley.ripleyshop.cart.Interactor.GetCartProducts;

public class ManageCartPresenter {

    private GetCartProducts getCartProducts;

    public ManageCartPresenter(Context context){

        getCartProducts = new GetCartProducts();
    }


    public void getCartProducts(){

    }


}
