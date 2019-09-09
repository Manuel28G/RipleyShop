package cl.com.ripley.ripleyshop.home.view.adapter;

import android.content.Context;
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
import cl.com.ripley.ripleyshop.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mImageList;
    private View mView;

    public PublicationAdapter(Context context){
        mContext = context;
        mImageList = new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mImageList.get(position))
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_card)
        CircleImageView image;
        @BindView(R.id.txtview_title_card)
        TextView title;
        @BindView(R.id.txtview_last_price)
        TextView oldPrice;
        @BindView(R.id.imgview_replay_icon_shop)
        ImageView iconReplayCard;
        @BindView(R.id.txtview_price)
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
