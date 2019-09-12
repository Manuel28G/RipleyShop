package cl.com.ripley.ripleyshop.product.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import cl.com.ripley.ripleyshop.R;
import cl.com.ripley.ripleyshop.general.model.ProductAttributes;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {

    private List<ProductAttributes> mInformationList;
    private Context mCtx;
    private View mView;
    private boolean isGray;

    public InformationAdapter(Context ctx, List<ProductAttributes> information){
        mInformationList = information;
        mCtx = ctx;
        isGray = true;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mCtx).inflate(R.layout.item_product_info, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductAttributes tmp = mInformationList.get(position);
        holder.title.setText(tmp.getName());
        holder.value.setText(tmp.getValue());
        if(isGray)
            holder.constraintLayout.setBackgroundColor(mCtx.getResources().getColor(R.color.gray));
        else
            holder.constraintLayout.setBackgroundColor(mCtx.getResources().getColor(R.color.white));

        isGray = !isGray;
    }

    @Override
    public int getItemCount() {
        return mInformationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtview_value)
        TextView value;
        @BindView(R.id.txtview_title)
        TextView title;
        @BindView(R.id.conslay_container)
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
