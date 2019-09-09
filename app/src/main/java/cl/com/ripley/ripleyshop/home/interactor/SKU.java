package cl.com.ripley.ripleyshop.home.interactor;

import android.content.Context;
import cl.com.ripley.ripleyshop.general.model.UtilHelper;
import cl.com.ripley.ripleyshop.home.presenter.Home;

import static cl.com.ripley.ripleyshop.general.model.Constants.FILE_SKU;

public class SKU {

    private Context mCtx;
    private Home.Presenter mPresenter;

    public SKU(Context ctx, Home.Presenter presenter){
        mCtx = ctx;
        mPresenter = presenter;
    }

    public void run(){
        String response = UtilHelper.readFile(mCtx, FILE_SKU);
        if(response != null){
            mPresenter.addSKUS(response);
        }
    }

}
