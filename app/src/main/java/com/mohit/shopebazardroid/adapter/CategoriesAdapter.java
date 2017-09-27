package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.model.response.CategoryChildrens;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by msp on 22/7/16.
 */
public class CategoriesAdapter extends BaseAdapter {

    public static String TAG = CategoriesAdapter.class.getSimpleName();
    Context mContext;
    ArrayList<CategoryChildrens> arrayList;
    String imagePrefix = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");

    public CategoriesAdapter(Context mContext, ArrayList<CategoryChildrens> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public CategoryChildrens getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {

        CategoryChildrens entity = getItem(position);
        ViewHolder holder;

        if (convertview == null) {
            convertview = LayoutInflater.from(mContext).inflate(R.layout
                    .adapter_home_category, viewGroup, false);
            holder = new ViewHolder(convertview);
            convertview.setTag(holder);

        } else {
            holder = (ViewHolder) convertview.getTag();
        }

        holder.lableTextView.setVisibility(View.VISIBLE);
        holder.lableTextView.setText(entity.getName());

        try {

            if (!TextUtils.isEmpty(entity.getImage())) {

                Picasso.with(mContext)
                        .load(imagePrefix+entity.getImage())
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                        .into(holder.mImageView);
            } else {
                try {
                    holder.mImageView.setImageBitmap(BitmapFactory.decodeResource(mContext
                            .getResources(), R.drawable.ic_placeholder));

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertview;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView mImageView;
        TextView lableTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.adp_oftd_image);
            lableTextView = (TextView) itemView.findViewById(R.id.adp_oftd_lable);
        }
    }
}
