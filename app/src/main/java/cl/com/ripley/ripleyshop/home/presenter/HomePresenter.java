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
    private Home.View mView;

    public HomePresenter(Context ctx, Home.View view){
        mCtx = ctx;
        mView = view;
        homeItemInteractor = new HomeItems(this);
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
        homeItemInteractor.setSKU(sku);
        homeItemInteractor.run();
    }

    @Override
    public void addPublications(List<HomeProduct> products) {
            mView.addProducts(products);
    }

    @Override
    public void errorConnection(String tag) {
        if(tag.equals(skuInteractor.TAG))
            mView.showErrorConsultingSKU();
        else if(tag.equals(homeItemInteractor.TAG))
            mView.showErrorConsultingPublications();
    }
}
