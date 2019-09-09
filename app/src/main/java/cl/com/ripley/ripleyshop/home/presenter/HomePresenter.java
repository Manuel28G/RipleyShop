package cl.com.ripley.ripleyshop.home.presenter;

import android.content.Context;
import java.util.List;
import cl.com.ripley.ripleyshop.home.interactor.HomeItems;
import cl.com.ripley.ripleyshop.home.interactor.SKU;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public class HomePresenter implements Home.Presenter {

    private HomeItems homeItemInteractor;
    private SKU skuInteractor;
    private Context mCtx;

    public HomePresenter(Context ctx){
        mCtx = ctx;
        homeItemInteractor = new HomeItems();
        skuInteractor = new SKU(mCtx,this);
    }

    public void getItems() {
        skuInteractor.run();
    }

    @Override
    public void addSKUS(String listSKU) {
        String sku = "";
        for(String tmp :listSKU.split("\n")){
                sku += "," + tmp;
        }
        if(!sku.isEmpty())
            sku = sku.substring(1);
        homeItemInteractor.getItemsInformation(sku);
    }

    @Override
    public void addPublications(List<HomeProduct> products) {

    }
}
