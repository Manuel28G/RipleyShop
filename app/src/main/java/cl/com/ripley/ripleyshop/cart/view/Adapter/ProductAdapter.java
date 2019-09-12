package cl.com.ripley.ripleyshop.cart.view.Adapter;

import android.content.Context;
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

    /**
     * Retorna la lista de elementos que fueron seleccionados por el cliente
     * @return lista de productos que el cliente quiere comprar {List<HomeProduct>}
     */
    public List<HomeProduct> getElemntList(){
        return mHomeProduct;
    }

    public void addCollection(List<HomeProduct> homeProductList){
        mHomeProduct.addAll(homeProductList);
    }

    /**
     * Metodo que retorna el total del costo de todos los productos agregados al carrito
     * @return entero que identifica el total de los productos agregados
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for(HomeProduct tmp : mHomeProduct){
            totalPrice +=(tmp.getPrices().getListPrice() * tmp.getProductCount());
        }
        return totalPrice;
    }

    /**
     * Metodo que retorna el valor total al pagar con una tarjeta ripley
     * @return valor con descuento del total de todos los productos con tarjeta ripley
     */
    public int getTotalRipleyPrice(){
        int totalRipleyPrice = 0;
        for(HomeProduct tmp : mHomeProduct){
            totalRipleyPrice +=tmp.getPrices().getOfferPrice() >0?
                    tmp.getPrices().getOfferPrice()* tmp.getProductCount():
                    tmp.getPrices().getListPrice() * tmp.getProductCount();
        }
        return totalRipleyPrice;
    }

    /**
     * Metodo que retorna el total de productos guardados en el carrito
     * @return cantidad de productos guardados en el carrito {int}
     */
    public int getTotalOfProducts(){

        int totalOfProducts = 0;
        for(HomeProduct tmp : mHomeProduct){
            totalOfProducts +=tmp.getProductCount();
        }
        return totalOfProducts;
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
        UtilHelper.strikeText(holder.totalPrice);
        holder.totalPrice.setText("$" + UtilHelper.getFormmated(product.getPrices().getListPrice() * product.getProductCount()) );
        holder.description.setText(product.getPartNumber());
        holder.title.setText(product.getName());
        holder.detailTitle.setText(UtilHelper.countProduct(product.getProductCount(),
                mContext.getResources().getString(R.string.product),
                mContext.getResources().getString(R.string.products)));
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
