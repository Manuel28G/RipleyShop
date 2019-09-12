package cl.com.ripley.ripleyshop.cart.Interactor;

import java.util.UUID;
import cl.com.ripley.ripleyshop.cart.presenter.ManageCart;
import cl.com.ripley.ripleyshop.general.model.GeneralInteractor;

public class GetCartProducts  implements GeneralInteractor {

    private ManageCart.GetProduct mGetProduct;

    public GetCartProducts(ManageCart.GetProduct getProduct){
        mGetProduct = getProduct;
    }
    private UUID mUUID;

    public void setUUID(UUID userId){
        mUUID = userId;
    }

    @Override
    public void run() {
        mGetProduct.setCartProducts(ProductsInMemory.getInstance().getProductList());
    }

}
