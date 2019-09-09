package cl.com.ripley.ripleyshop.home.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.com.ripley.ripleyshop.R;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

import static cl.com.ripley.ripleyshop.general.model.Constants.HTTPS;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder> {

    private Context mContext;
    private List<HomeProduct> mProductList;
    private View mView;

    public PublicationAdapter(Context context){
        mContext = context;
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
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HomeProduct product = mProductList.get(position);
        Glide.with(mContext)
                .load(HTTPS+product.getThumbnailImage())
                .into(holder.image);

        holder.price.setText(product.getPrices().getFormattedOfferPrice());
        holder.oldPrice.setText(product.getPrices().getFormattedListPrice());
        holder.oldPrice.setPaintFlags( holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.title.setText(product.getName());
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

        private String description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
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

    }
}
