package cl.com.ripley.ripleyshop.cart.Interactor;

import cl.com.ripley.ripleyshop.cart.presenter.ManageCart;
import cl.com.ripley.ripleyshop.general.model.GeneralInteractor;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public class AddCartProduct implements GeneralInteractor {

    private HomeProduct mHomeProduct;
    private ManageCart.AddCartProduct mAddProduct;

    public AddCartProduct(ManageCart.AddCartProduct addCartProduct){
        mAddProduct = addCartProduct;
    }

    public void setProduct(HomeProduct homeProduct){
        mHomeProduct = homeProduct;
    }

    @Override
    public void run() {
        if(mHomeProduct != null) {
            ProductsInMemory.getInstance().addProduct(mHomeProduct);
            mAddProduct.addProductSucess();
        }
        else
        {
            mAddProduct.addProductError();
        }
    }
}
