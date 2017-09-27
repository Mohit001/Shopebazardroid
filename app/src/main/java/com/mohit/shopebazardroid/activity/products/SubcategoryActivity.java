package com.mohit.shopebazardroid.activity.products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.adapter.SubcategoryAdapter;
import com.mohit.shopebazardroid.dialog.ConfirmDialog;
import com.mohit.shopebazardroid.listener.RecyclerItemclicklistner;
import com.mohit.shopebazardroid.model.response.CategoryChildrens;

import java.util.ArrayList;


/**
 * Created by msp on 23/7/16.
 */
public class SubcategoryActivity extends BaseActivity {

    public static String TAG = SubcategoryActivity.class.getSimpleName();
    Context mContext;
    ArrayList<CategoryChildrens> arrayList;
    RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    RecyclerView.Adapter mAdapter;
    CategoryChildrens childrens;
    ConfirmDialog confirmDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);
        mContext = this;
        childrens = (CategoryChildrens) getIntent().getSerializableExtra(CategoryChildrens
                .KEY_OBJECT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle(childrens.getName());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        setupGrid();
    }

    private void setupGrid() {

        arrayList = childrens.getChildren();
        if (arrayList != null && arrayList.size() > 0) {
            mAdapter = new SubcategoryAdapter(this, arrayList);
            recyclerView.setAdapter(mAdapter);
            recyclerView.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                    RecyclerItemclicklistner.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
//                    Utility.toastMessage(mContext, "position : "+position);
                    CategoryChildrens child = arrayList.get(position);
                    if (child.getChildren() != null && child.getChildren().size() > 0) {
                        Intent intent = new Intent(mContext, SubcategoryActivity.class);
                        intent.putExtra(CategoryChildrens.KEY_OBJECT, child);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContext, ProductGridActivity.class);
                        intent.putExtra(CategoryChildrens.KEY_ID, child.getId());
                        intent.putExtra(CategoryChildrens.KEY_NAME, child.getName());
                        intent.putExtra(CategoryChildrens.KEY_TYPE, 0);
                        startActivity(intent);
                    }
                }
            }));
        } else {
//            Utility.toastMessage(mContext, "Sorry no product found");
           /* confirmDialog = new ConfirmDialog();
            confirmDialog.setListner(this, "Product Listing", "No product found", "OK", "RETRY");
            confirmDialog.show(getSupportFragmentManager(), ConfirmDialog.TAG);*/
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                this.finish();
                return true;
        }
    }


    /*@Override
    public void onPositiveButtonClick() {
        confirmDialog.dismiss();
    }

    @Override
    public void onNegativeButtonClick() {
        confirmDialog.dismiss();
        ProductRequest request = new ProductRequest();
        request.setCatid(subcategotyid);
        request.setPage(String.valueOf(1));
        request.setPagesize(String.valueOf(10000));

        if(type == 0)
            HTTPWebRequest.products(mContext, request, AppConstants.APICode.PRODUCTSLIST,this,
            getSupportFragmentManager());
        else
            HTTPWebRequest.BannerProducts(mContext, request,AppConstants.APICode.BANNER_PRODUCTS,
            this, getSupportFragmentManager());

    }*/
}
