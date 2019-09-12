package cl.com.ripley.ripleyshop.cart.presenter;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import cl.com.ripley.ripleyshop.cart.Interactor.GetCartProducts;
import cl.com.ripley.ripleyshop.cart.Interactor.PayCartProduct;
import cl.com.ripley.ripleyshop.general.model.UtilHelper;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public class ManageCartPresenter implements
        ManageCart.GetProduct,
        ManageCart.PayCartProduct,
        ManageCart.RemoveCartProduct ,
ManageCart.AddCartProduct {

    private GetCartProducts getCartProducts;
    private cl.com.ripley.ripleyshop.cart.Interactor.AddCartProduct addCartProductsInteractor;
    private ViewCart mViewCart;
    private AddPublication mAddPublication;
    private cl.com.ripley.ripleyshop.cart.Interactor.PayCartProduct payCartProduct;

    public ManageCartPresenter(ViewCart viewCart, Context context){
        mViewCart = viewCart;
        getCartProducts = new GetCartProducts(this);
        payCartProduct = new cl.com.ripley.ripleyshop.cart.Interactor.PayCartProduct(this);
    }

    public void addProductToCart(HomeProduct homeProduct){
        addCartProductsInteractor.setProduct(homeProduct);
        addCartProductsInteractor.run();
    }

    public ManageCartPresenter(AddPublication addPublication, Context context){
        mAddPublication = addPublication;
        addCartProductsInteractor = new cl.com.ripley.ripleyshop.cart.Interactor.AddCartProduct(this);
    }

    /**
     * Metodo para obtener los productos del carrito por usuario
     * @param userId identificador unico del usuario {@link UUID}
     */
    public void getCartProducts(UUID userId){
        getCartProducts.setUUID(userId);
        getCartProducts.run();
    }

    public void buyAction(List<HomeProduct> products,UUID userId){
        payCartProduct.setListOfProducts(products,userId);
        payCartProduct.run();
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void setCartProducts(String products) {
        Type type = new TypeToken<ArrayList<HomeProduct>>(){}.getType();
        List<HomeProduct> homeProducts = UtilHelper.parseJsonToArrayFromArrayJson(products,type);
        if(homeProducts.isEmpty() || homeProducts.size() ==  0){
            mViewCart.noProducts();
        }
        else
        {
            mViewCart.setCartProducts(homeProducts);
        }
    }

    @Override
    public void paySucess() {
        mViewCart.showPaySucess();
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

    @Override
    public void addProductSucess() {
        mAddPublication.showSucessMessage();
    }

    @Override
    public void addProductError() {
        mAddPublication.showErrorMessage();
    }
}
