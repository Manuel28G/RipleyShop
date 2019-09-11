package cl.com.ripley.ripleyshop.cart.Interactor;

import java.util.ArrayList;
import java.util.List;

import cl.com.ripley.ripleyshop.general.model.UtilHelper;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public class ProductsInMemory {

    private List<HomeProduct> mHomeProductList;
    private static ProductsInMemory sProductInMemory;

    private ProductsInMemory(){
        mHomeProductList = new ArrayList<>();
    }

    public static ProductsInMemory getInstance(){
        if(sProductInMemory == null)
            sProductInMemory = new ProductsInMemory();
        return sProductInMemory;
    }

    public String getProductList(){
        return UtilHelper.parseObjectToJsonString(mHomeProductList);
    }

    public void removeProduct(HomeProduct productId){
        mHomeProductList.remove(productId);
    }

    public void addProduct(HomeProduct productId){
        mHomeProductList.add(productId);
    }

    public void payAction(){
        mHomeProductList = new ArrayList<>();
    }

}
