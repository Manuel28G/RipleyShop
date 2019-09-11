package cl.com.ripley.ripleyshop.cart.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import cl.com.ripley.ripleyshop.R;
import cl.com.ripley.ripleyshop.cart.presenter.ManageCart;
import cl.com.ripley.ripleyshop.cart.presenter.ManageCartPresenter;
import cl.com.ripley.ripleyshop.general.model.GeneralEnum;
import cl.com.ripley.ripleyshop.general.view.fragment.ManagementFragment;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public class CartFragment extends Fragment implements ManageCart.view {


    private ManageCartPresenter mManageCart;
    @BindView(R.id.imgview_empty)
    ImageView image;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtview_empty_text)
    TextView text;
    @BindView(R.id.conslay_with_elements)
    ConstraintLayout constraintLayoutElements;

    public CartFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart,container,false);
        ButterKnife.bind(this,view);
        mManageCart.getCartProducts();
        return view;
    }


    @Override
    public void showErrorMessage() {

    }

    @Override
    public void setCartProducts(List<HomeProduct> homeProductList) {
        progressBar.setVisibility(View.GONE);
        constraintLayoutElements.setVisibility(View.VISIBLE);
    }

    @Override
    public void noProducts() {
        progressBar.setVisibility(View.GONE);
        text.setVisibility(View.VISIBLE);
        image.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPaySucess() {

    }

    @Override
    public void showRemoveSucess() {

    }

    @Override
    public void showErrorMessage(GeneralEnum.TYPE_ERROR error, String message) {

    }
}