package cl.com.ripley.ripleyshop.home.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.com.ripley.ripleyshop.R;
import cl.com.ripley.ripleyshop.general.model.UtilHelper;
import cl.com.ripley.ripleyshop.general.view.fragment.ManagementFragment;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;
import cl.com.ripley.ripleyshop.home.view.activity.MainActivity;
import cl.com.ripley.ripleyshop.product.view.fragment.ProductDetailFragment;
import static cl.com.ripley.ripleyshop.general.model.Constants.HTTPS;
import static cl.com.ripley.ripleyshop.general.model.Constants.PUBLICATION_ID;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder> {

    private Context mContext;
    private FragmentManager mManager;
    private List<HomeProduct> mProductList;
    private View mView;
    private static final String TAG = PublicationAdapter.class.toString();

    public PublicationAdapter(Context context, FragmentManager manager){
        mContext = context;
        mManager = manager;
        mProductList = new ArrayList<>();
    }

    public void addProduct(HomeProduct product){
        mProductList.add(product);
    }

    public  void addProducts(List<HomeProduct> products){
        mProductList.addAll(products);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(mView,mManager);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HomeProduct product = mProductList.get(position);
        Glide.with(mContext)
                .load(HTTPS+product.getThumbnailImage())
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.image);

        holder.price.setText(product.getPrices().getFormattedOfferPrice());
        holder.oldPrice.setText(product.getPrices().getFormattedListPrice());
        holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.title.setText(product.getName());
        holder.product = product;
        holder.moreAction();
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_card)
        ImageView image;
        @BindView(R.id.txtview_title_card)
        TextView title;
        @BindView(R.id.txtview_last_price)
        TextView oldPrice;
        @BindView(R.id.imgview_replay_icon_shop)
        ImageView iconReplayCard;
        @BindView(R.id.txtview_price)
        TextView price;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public HomeProduct product;
        private String description;
        private FragmentManager mManager;

        public ViewHolder(@NonNull View itemView, FragmentManager manager) {
            super(itemView);
            mManager = manager;
            ButterKnife.bind(this,itemView);
        }

        public void moreAction(){
            description = title.getText().toString();
            if(title.getText().toString().length() > 19) {
                String tmp = title.getText().toString().substring(0,19);
                title.setText(tmp + "...more");
            }
        }

        @OnClick(R.id.txtview_title_card)
        public void onClickTitle(){
            title.setText(description);
        }

        @OnClick(R.id.img_card)
        public void onClickImage(){
            Fragment fragment = new ProductDetailFragment();
            Bundle args = new Bundle();
            args.putString(PUBLICATION_ID, UtilHelper.parseObjectToJsonString(product));
            fragment.setArguments(args);
            ManagementFragment.getInstance().replaceFragment(fragment,TAG,mManager);
            ((MainActivity)mContext).enableViews(true,true,false);
        }

    }
}
