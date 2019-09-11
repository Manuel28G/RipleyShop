package cl.com.ripley.ripleyshop.cart.presenter;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import cl.com.ripley.ripleyshop.cart.Interactor.GetCartProducts;
import cl.com.ripley.ripleyshop.general.model.UtilHelper;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public class ManageCartPresenter implements
        ManageCart.GetProduct,
        ManageCart.PayCartProduct,
        ManageCart.RemoveCartProduct {

    private GetCartProducts getCartProducts;
    private ManageCart.view mView;

    public ManageCartPresenter(ManageCart.view view,Context context){
        mView = view;
        getCartProducts = new GetCartProducts(this);
    }

    public void getCartProducts(){
        getCartProducts.run();
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void setCartProducts(String products) {
        Type type = new TypeToken<ArrayList<HomeProduct>>(){}.getType();
        List<HomeProduct> homeProducts = UtilHelper.parseJsonToArrayFromArrayJson(products,type);
        if(homeProducts.isEmpty() || homeProducts.size() ==  0){
            mView.noProducts();
        }
        else
        {
            mView.setCartProducts(homeProducts);
        }
    }

    @Override
    public void paySucess() {

    }

    @Override
    public void payError(String message) {

    }

    @Override
    public void removeProductSucess() {

    }

    @Override
    public void remoceProductError() {

    }
}
