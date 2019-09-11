package cl.com.ripley.ripleyshop.cart.presenter;

import java.util.List;
import cl.com.ripley.ripleyshop.general.model.GeneralEnum;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public interface ManageCart {

    void showErrorMessage();

    public interface GetProduct extends ManageCart{
        void setCartProducts(String products);
    }

    public interface PayCartProduct extends ManageCart{
        void paySucess();
        void payError(String message);
    }

    public interface  RemoveCartProduct extends ManageCart{
        void removeProductSucess();
        void remoceProductError();
    }

    public interface  AddCartProduct extends ManageCart{
        void addProductSucess();
        void addProductError();
    }

    public interface view extends ManageCart{
        void setCartProducts(List<HomeProduct> homeProductList);
        void noProducts();
        void showPaySucess();
        void showRemoveSucess();
        void showErrorMessage(GeneralEnum.TYPE_ERROR error, String message);
    }
}
