package cl.com.ripley.ripleyshop.cart.presenter;

import java.util.List;
import cl.com.ripley.ripleyshop.general.model.GeneralEnum;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public interface ManageCart {

    void showErrorMessage();

    interface GetProduct extends ManageCart{
        void setCartProducts(String products);
    }

    interface PayCartProduct extends ManageCart{
        void paySucess();
        void payError(String message);
    }

    interface  RemoveCartProduct extends ManageCart{
        void removeProductSucess();
        void remoceProductError();
    }

    interface  AddCartProduct extends ManageCart{
        void addProductSucess();
        void addProductError();
    }

    interface ViewCart extends ManageCart{
        void setCartProducts(List<HomeProduct> homeProductList);
        void noProducts();
        void showPaySucess();
        void showRemoveSucess();
        void showErrorMessage(GeneralEnum.TYPE_ERROR error, String message);
    }
    interface AddPublication extends ManageCart{
        void showSucessMessage();
    }
}
