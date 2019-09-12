package cl.com.ripley.ripleyshop.cart.Interactor;

import java.util.List;
import java.util.UUID;

import cl.com.ripley.ripleyshop.cart.presenter.ManageCart;
import cl.com.ripley.ripleyshop.general.model.GeneralInteractor;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public class PayCartProduct implements GeneralInteractor {

    private ManageCart.PayCartProduct mPayCartProduct;
    private List<HomeProduct> mProducts;

    /**
     * Metodo para asignar los elementos que seran enviados al endpoint
     * @param products lista de productos que seran enviados como comprados por el cliente
     * @param userId ID del usuario que esta comprando los articulos
     */
    public void setListOfProducts(List<HomeProduct> products, UUID userId){
        mProducts = products;
    }

    public PayCartProduct(ManageCart.PayCartProduct payCartProduct){
        mPayCartProduct = payCartProduct;
    }

    @Override
    public void run() {
        ProductsInMemory.getInstance().payAction();
        mPayCartProduct.paySucess();
    }
}
