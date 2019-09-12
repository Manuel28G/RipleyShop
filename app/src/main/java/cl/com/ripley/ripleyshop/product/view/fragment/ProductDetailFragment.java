package cl.com.ripley.ripleyshop.product.view.fragment;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.synnapps.carouselview.CarouselView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.com.ripley.ripleyshop.R;
import cl.com.ripley.ripleyshop.cart.presenter.ManageCart;
import cl.com.ripley.ripleyshop.cart.presenter.ManageCartPresenter;
import cl.com.ripley.ripleyshop.cart.view.fragment.CartFragment;
import cl.com.ripley.ripleyshop.general.model.UtilHelper;
import cl.com.ripley.ripleyshop.general.view.fragment.ManagementFragment;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;
import cl.com.ripley.ripleyshop.home.view.activity.MainActivity;
import cl.com.ripley.ripleyshop.product.view.adapter.InformationAdapter;
import static cl.com.ripley.ripleyshop.general.model.Constants.HTTPS;
import static cl.com.ripley.ripleyshop.general.model.Constants.PUBLICATION_ID;

public class ProductDetailFragment extends Fragment implements ManageCart.AddPublication {


    @BindView(R.id.carouselView)
    CarouselView carouselView;
    @BindView(R.id.txtview_detail_title)
    TextView title;
    @BindView(R.id.txtview_ripley_price)
    TextView ripleyPrice;
    @BindView(R.id.txtview_total_price)
    TextView totalPrice;
    @BindView(R.id.recview_detail)
    RecyclerView recyclerView;
    private HomeProduct mProduct;
    private InformationAdapter mInformationAdapter;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private ManageCartPresenter presenter;
    private final String TAG = ProductDetailFragment.class.toString();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail,container,false);
        String tmp = getArguments().getString(PUBLICATION_ID);
        mProduct = UtilHelper.parseJsonToObject(tmp, HomeProduct.class);
        ButterKnife.bind(this, view);
        presenter = new ManageCartPresenter(this,getContext());
        setRecyclerView();
        setInformation();
        return view;
    }

    private void setRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        mInformationAdapter = new InformationAdapter(getContext(),mProduct.getAtributes());
        recyclerView.setAdapter(mInformationAdapter);
    }

    private void setInformation(){
        title.setText(mProduct.getName());
        ripleyPrice.setText(mProduct.getPrices().getFormattedOfferPrice());
        UtilHelper.strikeText(totalPrice);
        totalPrice.setText(mProduct.getPrices().getFormattedListPrice());
        carouselView.setPageCount(mProduct.getImages().length);
        carouselView.setImageListener((position, imageView) ->
                Glide.with(getContext())
                        .load(HTTPS+mProduct.getImage(position))
                        .addListener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                                progressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .fitCenter()
                        .into(imageView));
    }

    @OnClick(R.id.bt_add_to_cart)
    public void onClickAddToCar(){
        presenter.addProductToCart(mProduct);
        ((MainActivity)getActivity()).enableViews(true,true,true);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getContext(),getResources().getString(R.string.add_error),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSucessMessage() {
        Toast.makeText(getContext(),getResources().getString(R.string.add_sucess),Toast.LENGTH_SHORT).show();
        ManagementFragment.getInstance().replaceFragment(new CartFragment(getContext()),TAG,getFragmentManager());
    }
}
