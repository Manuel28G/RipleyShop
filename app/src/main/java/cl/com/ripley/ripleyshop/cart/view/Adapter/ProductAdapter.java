package cl.com.ripley.ripleyshop.cart.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import cl.com.ripley.ripleyshop.R;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_shop_cart, parent, false);
        return new ProductAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mHomeProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
