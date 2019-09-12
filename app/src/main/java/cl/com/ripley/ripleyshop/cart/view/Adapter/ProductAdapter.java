package cl.com.ripley.ripleyshop.cart.view.Adapter;

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
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import cl.com.ripley.ripleyshop.R;
import cl.com.ripley.ripleyshop.general.model.Constants;
import cl.com.ripley.ripleyshop.general.model.UtilHelper;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private List<HomeProduct> mHomeProduct;
    private Context mContext;
    private View mView;

    public ProductAdapter(List<HomeProduct> homeProductList, Context context){
        mHomeProduct = homeProductList;
        mContext = context;
    }

    public void addPublication(HomeProduct product){
        mHomeProduct.add(product);
    }

    public void addCollection(List<HomeProduct> homeProductList){
        mHomeProduct.addAll(homeProductList);
    }

    /**
     * Metodo para crear el texto (singularo o plural) para mostrar en pantalla
     * @param productCount cantidad de productos {int}
     * @return cantidad de productos en String {String}
     */
    private String countProduct(int productCount){
        String detailTitle = Integer.toString(productCount);
        if(productCount == 1){
            detailTitle += " "+ mContext.getResources().getString(R.string.product);
        }
        else
        {
            detailTitle += " "+ mContext.getResources().getString(R.string.products);
        }
        return detailTitle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_shop_cart, parent, false);
        return new ProductAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeProduct product= mHomeProduct.get(position);
        holder.ripleyPrice.setText("$" + UtilHelper.getFormmated(product.getPrices().getOfferPrice() * product.getProductCount()));
        holder.totalPrice.setPaintFlags(holder.totalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.totalPrice.setText("$" + UtilHelper.getFormmated(product.getPrices().getListPrice() * product.getProductCount()) );
        holder.description.setText(product.getPartNumber());
        holder.title.setText(product.getName());
        holder.detailTitle.setText(countProduct(product.getProductCount()));
        Glide.with(mContext).load(Constants.HTTPS +product.getThumbnailImage()).fitCenter().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mHomeProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgview_image)
        ImageView image;
        @BindView(R.id.txtview_detail_title)
        TextView detailTitle;
        @BindView(R.id.txtview_ripley_price)
        TextView ripleyPrice;
        @BindView(R.id.txtview_total_price)
        TextView totalPrice;
        @BindView(R.id.txtview_description)
        TextView description;
        @BindView(R.id.txtview_title)
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
