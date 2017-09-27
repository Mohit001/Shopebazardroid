package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.model.response.SubCategoryListItemResponse;

import java.util.List;

/**
 * Created by dhavalj on 10/29/2015.
 */
public class SubCategoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubCategoryListItemResponse> data;
    View itemView;

    public SubCategoryListAdapter(Context context, List<SubCategoryListItemResponse> mData) {

        this.data = mData;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_category_listitem, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final  SubCategoryListItemResponse subcategoryListItemResponse = getItem(position);

        ((Viewholder) holder).name.setText(subcategoryListItemResponse.getName());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public SubCategoryListItemResponse getItem(int pos) {
        return data.get(pos);
    }

    private class Viewholder extends RecyclerView.ViewHolder {

        protected TextView name;

        public Viewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name_tv);
        }
    }

}
