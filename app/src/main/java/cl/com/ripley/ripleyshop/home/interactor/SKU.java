package cl.com.ripley.ripleyshop.home.interactor;

import android.content.Context;
import cl.com.ripley.ripleyshop.general.model.GeneralInteractor;
import cl.com.ripley.ripleyshop.general.model.UtilHelper;
import cl.com.ripley.ripleyshop.home.presenter.Home;

import static cl.com.ripley.ripleyshop.general.model.Constants.FILE_SKU;

public class SKU  implements GeneralInteractor {

    private Context mCtx;
    private Home.Presenter mPresenter;
    public static final String TAG = SKU.class.toString();

    public SKU(Context ctx, Home.Presenter presenter){
        mCtx = ctx;
        mPresenter = presenter;
    }

    @Override
    public void run(){
        String response = UtilHelper.readFile(mCtx, FILE_SKU);
        if(response != null){
            mPresenter.addSKUS(response);
        }
    }

}
