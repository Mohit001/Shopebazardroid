package com.mohit.shopebazardroid.activity.Checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Address.AddUpdateAddressActivity;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.adapter.AddressListAdapter;
import com.mohit.shopebazardroid.listener.AddressListner;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.models.Address;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msp on 26/7/16.
 */
public class ActivityBillingAddress extends BaseActivity implements View.OnClickListener,
        AddressListner, ApiResponse {

    public static String TAG = ActivityBillingAddress.class.getSimpleName();
    Context mContext;
    RelativeLayout relativeLayout;
    Button continueTextView;
    List<Address> arrayList;
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    Address selectedAddress;
    //    AddressAdapter mAddressAdapter;
    AddressListAdapter mAddressListAdapter;
    int deleteIndex;
    //    AnimatedFloatingActionButtonBottom addAddressFab;
    ListView listView;
    private Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle(R.string.title_billnig_address);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        listView = (ListView) findViewById(R.id.listview);
        listView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        relativeLayout = (RelativeLayout) findViewById(R.id.cart_gross_total_rl);
        continueTextView = (Button) findViewById(R.id.proceed_checkout_btn);
//        addAddressFab = (AnimatedFloatingActionButtonBottom) findViewById(R.id.add_address_fab);
//        addAddressFab.setVisibility(View.VISIBLE);


        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);

        continueTextView.setText("Continue");
        continueTextView.setOnClickListener(this);
        relativeLayout.setVisibility(View.GONE);
//        addAddressFab.setOnClickListener(this);

    }


    private void setupAddressLive() {
        String userid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .USER_ID, "0");
        HTTPWebRequest.AddressList(mContext, userid, AppConstants.APICode.ADDRESS_LIST, this,
                getSupportFragmentManager());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
//                startActivity(new Intent(this, CartActivity.class));
                this.finish();
                return true;
            case R.id.action_plus:
                Intent i =new Intent(mContext,AddUpdateAddressActivity.class);
                i.putExtra("key","add");
                startActivity(i);
//                startActivity(new Intent(mContext, AddUpdateAddressActivity.class));
                return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.billing_address, menu);

        MenuItem item = menu.findItem(R.id.action_plus);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        setupAddressDummy();
        setupAddressLive();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.add_address_fab:
//                startActivity(new Intent(mContext, AddUpdateAddressActivity.class));
//                break;
            case R.id.proceed_checkout_btn:
                Address selecteedAddress = null;

                if(arrayList == null){
                    Toast.makeText(mContext, R.string.empty_billing_address, Toast.LENGTH_SHORT).show();
                    return;
                }

                if( arrayList.size() == 0){
                    Toast.makeText(mContext, R.string.empty_billing_address, Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < arrayList.size(); i++) {
                    Address address = arrayList.get(i);
                 /*   if (address.isSelected())
                        selecteedAddress = address;*/
                }

                if (selecteedAddress == null) {
                    Utility.toastMessage(mContext, R.string.address_empty_selection);
                    return;
                }

                String cart_id = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0");
                HTTPWebRequest.setCartBillingAddress(mContext, cart_id, String.valueOf(selecteedAddress.getAddress_id()), AppConstants.APICode.ADDRESS_UPDATE_DEFAULT, this, getSupportFragmentManager());


                break;
        }
    }

    @Override
    public void onEditAddressClick(int index) {
//        Utility.toastMessage(mContext, "Edit address clicked");
        //AddUpdateAddressActivity.address = arrayList.get(index);
        Address address = arrayList.get(index);
        Bundle bundle =new Bundle();
        bundle.putSerializable("address",address);

        Intent i =new Intent(mContext,AddUpdateAddressActivity.class);
        i.putExtra("key","edit");
        i.putExtra("bundle",bundle);
        startActivity(i);
    }

    @Override
    public void onDeleteAddressClick(int index) {
//        Utility.toastMessage(mContext, "Delete address clicked");

        deleteIndex = index;
        Address address = arrayList.get(index);
        String addressID = String.valueOf(address.getAddress_id());
        HTTPWebRequest.AddressDelete(mContext, addressID, AppConstants.APICode.ADDRESS_DELETE,
                this, getSupportFragmentManager());

    }

    @Override
    public void onSelectionChange(int index) {
//        ArrayList<Address> list = new ArrayList<>();
//        list.addAll(arrayList);

//        arrayList.clear();

        Address address;
        for (int i = 0; i < arrayList.size(); i++) {
            address = arrayList.get(i);
            if (i == index) {
               /* address.setIs_default_billing(true);
                address.setSelected(true);*/
                selectedAddress = arrayList.get(index);
            } else {
                /*address.setIs_default_billing(false);
                address.setSelected(false);*/
            }

//            list.remove(index);
//            list.add(index, address);
            arrayList.set(i, address);
        }

//        mAddressAdapter.notifyDataSetChanged();
//        arrayList.addAll(list);
//        mAddressListAdapter = new AddressListAdapter(mContext, arrayList, this, true, 1);
//        listView.setAdapter(mAddressListAdapter);
        mAddressListAdapter.notifyDataSetChanged();

    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {

        if (response == null) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.ADDRESS_LIST:

                Type addressListType = new TypeToken<BaseResponse<List<Address>>>() {
                }.getType();
                BaseResponse<List<Address>> addressResponse = new Gson().fromJson(response, addressListType);

                if (addressResponse.getInfo().size() != 0) {

                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }

                    arrayList.addAll(addressResponse.getInfo());
                    mAddressListAdapter = new AddressListAdapter(mContext, arrayList, this, false, 0);
                    listView.setAdapter(mAddressListAdapter);
                } else {
                    Utility.toastMessage(mContext, "No Address found");
                }
                break;
            case AppConstants.APICode.ADDRESS_DELETE:
                Type removeAddressType = new TypeToken<BaseResponse<List<Address>>>() {
                }.getType();
                BaseResponse<List<Address>> removeAddresResponse = new Gson().fromJson(response, removeAddressType);
                Toast.makeText(mContext, removeAddresResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if (removeAddresResponse.getStatus() == 1 && deleteIndex != -1) {

                    arrayList.remove(deleteIndex);
                    deleteIndex = -1;
                    mAddressListAdapter.notifyDataSetChanged();
                    listView.setAdapter(mAddressListAdapter);
                }
                break;
        }
    }

    @Override
    public void networkError(int apiCode) {

    }

    @Override
    public void responseError(int apiCode) {

    }
}
