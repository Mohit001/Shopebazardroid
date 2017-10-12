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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Address.AddUpdateAddressActivity;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.adapter.AddressListAdapter;
import com.mohit.shopebazardroid.enums.ApiResponseStatus;
import com.mohit.shopebazardroid.listener.AddressListner;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.models.Address;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AnimatedFloatingActionButtonBottom;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msp on 26/7/16.
 */
public class ActivityShippingAddress extends BaseActivity implements View.OnClickListener,
        AddressListner, ApiResponse {

    public static String TAG = ActivityShippingAddress.class.getSimpleName();
    private Context mContext;
    private RelativeLayout relativeLayout;
    private Button continueTextView;
    private List<Address> arrayList;
    private RecyclerView recyclerView;
    private ListView listView;
    private LinearLayoutManager linearLayoutManager;
    private Address selectedAddress;
     //  private AddressAdapter mAddressAdapter;
    private AddressListAdapter mAddressListAdapter;
    private AnimatedFloatingActionButtonBottom addAddressFab;

    int deleteIndex;
    private Menu menu;

    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle("Shipping Address");


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
        recyclerView.setHasFixedSize(true);

        continueTextView.setText("Continue");
        continueTextView.setOnClickListener(this);
        relativeLayout.setVisibility(View.GONE);
//        addAddressFab.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.billing_address, menu);

        MenuItem item = menu.findItem(R.id.action_plus);

        return true;
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
//                startActivity(new Intent(mContext, ActivityBillingAddress.class));
                onBackPressed();
                return true;
            case R.id.action_plus:
//                startActivity(new Intent(mContext, AddUpdateAddressActivity.class));
                Intent i = new Intent(mContext, AddUpdateAddressActivity.class);
                i.putExtra("key", "add");
                startActivity(i);
                return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, CartActivity.class));
        this.finish();
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

            case R.id.proceed_checkout_btn:

                if(selectedAddress == null){
                    Toast.makeText(mContext, R.string.address_empty_selection, Toast.LENGTH_SHORT).show();
                    return;
                }

                String cart_id = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0");

                HTTPWebRequest.setCartShippingAddress(mContext, cart_id, String.valueOf(selectedAddress.getAddress_id()), AppConstants.APICode.ADDRESS_UPDATE_DEFAULT, this, getSupportFragmentManager());

                break;
        }
    }

    @Override
    public void onEditAddressClick(int index) {
//        Utility.toastMessage(mContext, "Edit address clicked");
//        AddUpdateAddressActivity.address = arrayList.get(index);
//        startActivity(new Intent(mContext, AddUpdateAddressActivity.class));
//
        /*Intent i =new Intent(mContext,AddUpdateAddressActivity.class);
        i.putExtra("key","edit");
        startActivity(i);*/
        Address address = arrayList.get(index);
        Bundle bundle = new Bundle();
        bundle.putSerializable("address", address);

        Intent i = new Intent(mContext, AddUpdateAddressActivity.class);
        i.putExtra("key", "edit");
        i.putExtra("bundle", bundle);
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

        Address address;
        for (int i = 0; i < arrayList.size(); i++) {
            address = arrayList.get(i);
            if (i == index) {
                address.setSelected(true);
                selectedAddress = address;
            } else {
                address.setSelected(false);

            }

            arrayList.set(i, address);
        }


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

                Type addressListType = new TypeToken<BaseResponse<List<Address>>>() {}.getType();
                BaseResponse<List<Address>> addressResponse = new Gson().fromJson(response, addressListType);

                if (addressResponse.getInfo().size() != 0) {

                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    } else {
                        arrayList.clear();
                    }

                    if(addressResponse.getInfo().size() > 0){

                        arrayList.addAll(addressResponse.getInfo());

                        mAddressListAdapter = new AddressListAdapter(mContext, arrayList, this, true, 0);
                        listView.setAdapter(mAddressListAdapter);

                        selectedAddress = arrayList.get(0);
                        selectedAddress.setSelected(true);
                        arrayList.set(0, selectedAddress);

                        mAddressListAdapter.notifyDataSetChanged();
                    }

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
            case AppConstants.APICode.ADDRESS_UPDATE_DEFAULT:
                Type setShippingAddressType = new TypeToken<BaseResponse<List<Address>>>(){}.getType();
                Gson gson = new GsonBuilder().serializeNulls().create();
                BaseResponse<List<Address>> baseResponse = gson.fromJson(response, setShippingAddressType);

                Toast.makeText(mContext, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if(baseResponse.getStatus() == ApiResponseStatus.CART_SHIPPING_ADDRESS_UPDATE_SUCCESS.getStatus_code()){
                    startActivity(new Intent(this, ActivityBillingAddress.class));
                    this.finish();
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
