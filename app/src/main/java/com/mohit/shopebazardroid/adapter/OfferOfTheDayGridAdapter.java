package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.listener.RecyclerItemclicklistner;
import com.mohit.shopebazardroid.model.response.ProductEntity;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by msp on 23/7/16.
 */
public class OfferOfTheDayGridAdapter extends RecyclerView.Adapter<OfferOfTheDayGridAdapter.RecyclerViewHolders> {
    Context mContext;
    ArrayList<ProductEntity> arrayList;
    RecyclerItemclicklistner itemclicklistner;
    String imagePrefix = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");

    public OfferOfTheDayGridAdapter(Context mContext, ArrayList<ProductEntity> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.itemclicklistner = itemclicklistner;
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        public RecyclerViewHolders(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.adp_oftd_grid_image);
        }
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.adapter_offeroftheday, parent, false);

        return new RecyclerViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        ProductEntity entity = arrayList.get(position);

        //        if(!TextUtils.isEmpty(entity.getImageurl()))
        if (entity.getMedia() != null
                && entity.getMedia().size() > 0
                && entity.getMedia().get(0).getUrl().length() > 0)
        {
//            Log.d(TAG, "Position"+position);
//            Log.d(TAG, "getView: "+entity.toString());
            try {

                Picasso.with(mContext)
                        .load(imagePrefix+entity.getMedia().get(0).getUrl())
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                        .into(holder.imageView);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            holder.imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_placeholder));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
