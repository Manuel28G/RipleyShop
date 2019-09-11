package cl.com.ripley.ripleyshop.cart.presenter;

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

}
