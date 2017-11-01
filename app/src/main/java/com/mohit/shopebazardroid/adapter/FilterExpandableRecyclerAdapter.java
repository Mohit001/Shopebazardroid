package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.model.response.FilterAttributesOption;
import com.mohit.shopebazardroid.model.response.ProductFilterAttributes;

import java.util.ArrayList;

/**
 * Created by msp on 13/8/16.
 */
public class FilterExpandableRecyclerAdapter extends ExpandableRecyclerAdapter<FilterExpandableRecyclerAdapter.FilterParentViewHolder, FilterExpandableRecyclerAdapter.FilterChildViewHolder> {

    /**
     * Primary constructor. Sets up {@link #mParentItemList} and {@link #mItemList}.
     * <p/>
     * Changes to {@link #mParentItemList} should be made through add/remove methods in
     * {@link ExpandableRecyclerAdapter}
     *
     * @param parentItemList List of all {@link ParentListItem} objects to be
     *                       displayed in the RecyclerView that this
     *                       adapter is linked to
     */


    private Context mContext;
    private ArrayList<ProductFilterAttributes> arrayList;


    public FilterExpandableRecyclerAdapter(Context mContext, ArrayList<ProductFilterAttributes> filterArrayList) {
        super(filterArrayList);
        this.mContext = mContext;
        this.arrayList = filterArrayList;
    }

    @Override
    public FilterParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_adapter_filter_exp_header, parentViewGroup, false);
        return new FilterParentViewHolder(view);
    }

    @Override
    public FilterChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_adapter_filter_exp_child, childViewGroup, false);
        return new FilterChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(FilterParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        ProductFilterAttributes filterAttributes = (ProductFilterAttributes) parentListItem;
        parentViewHolder.filterCategorytitle.setText(filterAttributes.getTitle());
    }

    @Override
    public void onBindChildViewHolder(FilterChildViewHolder childViewHolder, int position, final Object childListItem) {
        final FilterAttributesOption option = (FilterAttributesOption) childListItem;
        childViewHolder.filterOptionCheckbox.setText(((FilterAttributesOption) childListItem).getLabel());
        childViewHolder.filterOptionCheckbox.setChecked(option.isChecked());
        childViewHolder.filterOptionCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ((FilterAttributesOption) childListItem).setChecked(b);
//                notifyChildItemChanged(option.getParentIndex(), option.getChildIndex());
            }
        });
    }

    public class FilterParentViewHolder extends ParentViewHolder
    {
        AppCompatTextView filterCategorytitle;
        /**
         * Default constructor.
         *
         * @param itemView The {@link View} being hosted in this ViewHolder
         */
        public FilterParentViewHolder(View itemView) {
            super(itemView);
            filterCategorytitle = (AppCompatTextView) itemView.findViewById(R.id.adapter_filter_exp_header_lbl);
            filterCategorytitle.setTypeface(SplashActivity.opensans_regular);
        }


    }

    public class FilterChildViewHolder extends ChildViewHolder {

        AppCompatCheckBox filterOptionCheckbox;
        /**
         * Default constructor.
         *
         * @param itemView The {@link View} being hosted in this ViewHolder
         */
        public FilterChildViewHolder(View itemView) {
            super(itemView);
            filterOptionCheckbox = (AppCompatCheckBox) itemView.findViewById(R.id.adapter_filter_exp_child_checkbox);
            filterOptionCheckbox.setTypeface(SplashActivity.opensans_regular);
        }
    }
}
