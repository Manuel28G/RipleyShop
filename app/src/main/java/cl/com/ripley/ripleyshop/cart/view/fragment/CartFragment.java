package cl.com.ripley.ripleyshop.cart.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.UUID;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.com.ripley.ripleyshop.R;
import cl.com.ripley.ripleyshop.cart.presenter.ManageCart;
import cl.com.ripley.ripleyshop.cart.presenter.ManageCartPresenter;
import cl.com.ripley.ripleyshop.cart.view.Adapter.ProductAdapter;
import cl.com.ripley.ripleyshop.general.model.GeneralEnum;
import cl.com.ripley.ripleyshop.general.model.UtilHelper;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public class CartFragment extends Fragment implements ManageCart.ViewCart {


    private ManageCartPresenter mManageCart;
    @BindView(R.id.imgview_empty)
    ImageView image;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtview_empty_text)
    TextView text;
    @BindView(R.id.conslay_with_elements)
    ConstraintLayout constraintLayoutElements;
    @BindView(R.id.recview_products)
    RecyclerView products;
    @BindView(R.id.txtview_detail_title)
    TextView totalCountOfProduct;
    private ProductAdapter productAdapter;
    @BindView(R.id.txtview_ripley_price)
    TextView totalPriceRipley;
    @BindView(R.id.txtview_total_price)
    TextView totalPrice;
    private Context mContext;

    @BindView(R.id.bt_action)
    Button payButton;

    public CartFragment(Context context){
        mManageCart = new ManageCartPresenter(this, getContext());
        mContext = context;
    }

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_cart,container,false);
        ButterKnife.bind(this,view);
        mManageCart.getCartProducts(UUID.randomUUID()) ;
        payButton.setText(getContext().getResources().getString(R.string.buy));
        return view;
    }


    @Override
    public void showErrorMessage() {

    }

    /**
     * Método que crea el Recyclerview en forma de dos columnas
     */
    private void creatingRecyclerView(List<HomeProduct> homeProductList){
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        products.setLayoutManager(mLayoutManager);
        productAdapter = new ProductAdapter(homeProductList,getContext());
        products.setItemAnimator(new DefaultItemAnimator());
        products.setAdapter(productAdapter);
        setTotalPrice();
    }

    private void setTotalPrice(){
        totalCountOfProduct.setText(UtilHelper.countProduct(productAdapter.getTotalOfProducts(),
                mContext.getResources().getString(R.string.product),
                mContext.getResources().getString(R.string.products)));
        UtilHelper.strikeText(totalPrice);
        totalPrice.setText("$" + UtilHelper.getFormmated(productAdapter.getTotalPrice()));
        totalPriceRipley.setText("$" + UtilHelper.getFormmated(productAdapter.getTotalRipleyPrice()));
    }

    @Override
    public void setCartProducts(List<HomeProduct> homeProductList) {
        progressBar.setVisibility(android.view.View.GONE);
        text.setVisibility(android.view.View.GONE);
        image.setVisibility(android.view.View.GONE);
        constraintLayoutElements.setVisibility(android.view.View.VISIBLE);
        creatingRecyclerView(homeProductList);
    }

    @Override
    public void noProducts() {
        progressBar.setVisibility(android.view.View.GONE);
        text.setVisibility(android.view.View.VISIBLE);
        image.setVisibility(android.view.View.VISIBLE);
    }

    @OnClick(R.id.bt_action)
    public void onClickBuy(){

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