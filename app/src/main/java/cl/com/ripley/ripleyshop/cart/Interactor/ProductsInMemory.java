package cl.com.ripley.ripleyshop.cart.Interactor;

import android.content.res.Resources;
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
        int index = indexOfObject(productId);
        if(index != -1){
            if(mHomeProductList.get(index).getProductCount() == 1){
                mHomeProductList.remove(productId);
            }
            else
            {
                mHomeProductList.get(index).removeProduct();
            }
        }
        else {
            throw new Resources.NotFoundException();
        }
    }

    private int indexOfObject(HomeProduct homeProduct){
        if(android.os.Build.VERSION.SDK_INT>24){
            HomeProduct tmp =  mHomeProductList
                  .stream()
                  .filter(homeProduct1 -> homeProduct1.getUniqueID().equals(homeProduct.getUniqueID()))
                  .findAny()
                  .orElse(null);
            return  tmp == null? -1:mHomeProductList.indexOf(tmp);
        }
        else
        {
            for(HomeProduct tmp : mHomeProductList){
                if(tmp.getUniqueID().equals(homeProduct.getUniqueID()))
                    return mHomeProductList.indexOf(tmp);
            }
            return -1;
        }
    }

    public void addProduct(HomeProduct productId){
        int index = indexOfObject(productId);
        if(index != -1){
            mHomeProductList.get(index).addProduct();
        }
        else {
            productId.addProduct();
            mHomeProductList.add(productId);
        }
    }

    public void payAction(){
        mHomeProductList = new ArrayList<>();
    }

}
