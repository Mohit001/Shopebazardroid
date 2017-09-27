package com.mohit.shopebazardroid.activity.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.listener.RecyclerItemclicklistner;
import com.mohit.shopebazardroid.model.response.OrderHistory;

import java.util.ArrayList;


/**
 * Created by msp on 27/7/16.
 */
public class OrderHistoryActivity extends BaseActivity {

    public static String TAG = OrderHistoryActivity.class.getSimpleName();
    Context mContext;

    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;

    ArrayList<OrderHistory> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);
        mContext = this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle("Order History");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new RecyclerItemclicklistner.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Utility.toastMessage(mContext, "Item click at : " + position);
                startActivity(new Intent(mContext, OrderHistoryDetailActivity.class));
            }
        }));

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupOrderHistory();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                this.finish();
                return true;
        }
    }

    private void setupOrderHistory() {

        arrayList = new ArrayList<>();

        OrderHistory history1 = new OrderHistory();
        history1.setOrderno("234234");
        history1.setOrderdate("12 Dec, 2015");
        history1.setAmount("$25000");
        history1.setStatus("delivered");
        arrayList.add(history1);

        OrderHistory history2 = new OrderHistory();
        history2.setOrderno("234534234");
        history2.setOrderdate("23 Feb, 2016");
        history2.setAmount("$450");
        history2.setStatus("cancel");
        arrayList.add(history2);

        OrderHistory history3 = new OrderHistory();
        history3.setOrderno("7928374");
        history3.setOrderdate("1 Jun, 2016");
        history3.setAmount("$1025");
        history3.setStatus("delivered");
        arrayList.add(history3);

        OrderHistory history4 = new OrderHistory();
        history4.setOrderno("949494");
        history4.setOrderdate("30 Jun, 2015");
        history4.setAmount("$6584");
        history4.setStatus("Pending");
        arrayList.add(history4);

        /*mAdapter = new OrderHistoryAdapter(mContext, arrayList);
        mRecyclerView.setAdapter(mAdapter);*/
    }

}
