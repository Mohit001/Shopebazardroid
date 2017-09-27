package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.model.response.CategoryChildrens;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by msp on 23/7/16.
 */
public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.RecyclerViewHolders> {
    Context mContext;
    ArrayList<CategoryChildrens> arrayList;
    String imagePrefix = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");

    public SubcategoryAdapter(Context mContext, ArrayList<CategoryChildrens> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView nameTextView;
        TextView imagebgTextView;


        public RecyclerViewHolders(View itemView) {
            super(itemView);
            int viewHeightWidth = (int) NavigationDrawerActivity.width/2;
            imageView = (ImageView) itemView.findViewById(R.id.subcategory_image);
            imagebgTextView = (TextView) itemView.findViewById(R.id.subcategory_image_bg);
            nameTextView = (TextView) itemView.findViewById(R.id.name_lbl);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(viewHeightWidth, viewHeightWidth);
            imageView.setLayoutParams(layoutParams);
            imagebgTextView.setLayoutParams(layoutParams);
        }
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.adapter_subcategory, parent, false);

        return new RecyclerViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        CategoryChildrens entity = arrayList.get(position);
        holder.nameTextView.setText(entity.getName());
        if(!TextUtils.isEmpty(entity.getThumb()))
        {
            Picasso.with(mContext)
                    .load(imagePrefix+entity.getImage())
//                    .resize(300,300)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(holder.imageView);
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
