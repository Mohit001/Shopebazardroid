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
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Address.AddUpdateAddressActivity;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.adapter.AddressListAdapter;
import com.mohit.shopebazardroid.listener.AddressListner;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.request.AddressRequest;
import com.mohit.shopebazardroid.model.response.Address;
import com.mohit.shopebazardroid.model.response.AddressResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;

/**
 * Created by msp on 26/7/16.
 */
public class ActivityBillingAddress extends BaseActivity implements View.OnClickListener,
        AddressListner, ApiResponse {

    public static String TAG = ActivityBillingAddress.class.getSimpleName();
    Context mContext;
    RelativeLayout relativeLayout;
    Button continueTextView;
    ArrayList<Address> arrayList;
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

    private void setupAddressDummy() {
        arrayList = new ArrayList<>();


        Address address1 = new Address();
        address1.setFirstname("Home1 address");
        address1.setHouseOfficeNo("123");
        address1.setStreet("ksjdf tkhsdnf");
        address1.setLandmark("jdjdjd");
        address1.setCity("kasjdfh");
        address1.setRegion("kjsdhf");
        address1.setCountry("klsjdflksdjf");
        address1.setPostcode("84848");
        address1.setEmail("test@gmail.com");
        address1.setTelephone("92837482347");
        address1.setSelected(true);
        arrayList.add(address1);


        Address address2 = new Address();
        address2.setFirstname("office address");
        address2.setHouseOfficeNo("123");
        address2.setStreet("ksjdf tkhsdnf");
        address2.setLandmark("jdjdjd");
        address2.setCity("kasjdfh");
        address2.setRegion("kjsdhf");
        address2.setCountry("klsjdflksdjf");
        address2.setPostcode("84848");
        address2.setEmail("test@gmail.com");
        address2.setTelephone("92837482347");
        address2.setSelected(false);
        arrayList.add(address2);

        selectedAddress = arrayList.get(0);
        mAddressListAdapter = new AddressListAdapter(mContext, arrayList, this, true, 1);
        listView.setAdapter(mAddressListAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    private void setupAddressLive() {
        AddressRequest request = new AddressRequest();
        request.setUser_id(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .USER_ID, "0"));
        HTTPWebRequest.AddressList(mContext, request, AppConstants.APICode.ADDRESS_LIST, this,
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
                    if (address.isSelected())
                        selecteedAddress = address;
                }

                if (selecteedAddress == null) {
                    Utility.toastMessage(mContext, R.string.address_empty_selection);
                    return;
                }

                AddressRequest request = new AddressRequest();
                request.setAddressId(selecteedAddress.getCustomer_address_id());
                request.setMode(1);
                request.setShoppingCartID(MyApplication.preferenceGetString(AppConstants
                        .SharedPreferenceKeys.CART_ID, "0"));
                HTTPWebRequest.AddressUpdateDefault(mContext, request, AppConstants.APICode
                        .ADDRESS_UPDATE_DEFAULT, this, getSupportFragmentManager());

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
        AddressRequest request = new AddressRequest();
        request.setUser_id(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .USER_ID, ""));
        request.setAddressId(address.getCustomer_address_id());
        HTTPWebRequest.AddressDelete(mContext, request, AppConstants.APICode.ADDRESS_DELETE,
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
                address.setIs_default_billing(true);
                address.setSelected(true);
                selectedAddress = arrayList.get(index);
            } else {
                address.setIs_default_billing(false);
                address.setSelected(false);
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
                AddressResponse addressResponse = new Gson().fromJson(response, AddressResponse
                        .class);

                if(addressResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0"))
                {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }

                if (addressResponse.getResult().getAddress() != null
                        && addressResponse.getResult().getAddress().size() > 0) {
                    arrayList = addressResponse.getResult().getAddress();
//                    mAddressAdapter = new AddressAdapter(mContext, arrayList, this, true, 1);

                    Address address;
                    for (int i = 0; i < arrayList.size(); i++) {
                        address = arrayList.get(i);
                        if (address.is_default_billing())
                            address.setSelected(true);
                        else
                            address.setSelected(false);

                        arrayList.set(i, address);
                    }
                    mAddressListAdapter = new AddressListAdapter(mContext, arrayList, this, true,
                            1);
                    listView.setAdapter(mAddressListAdapter);
                } else {
                    arrayList = new ArrayList<>();
                    Utility.toastMessage(mContext, "No Address found");
                }
                break;
            case AppConstants.APICode.ADDRESS_DELETE:
                AddressResponse addressResponse1 = new Gson().fromJson(response, AddressResponse
                        .class);

                if(addressResponse1.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0"))
                {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }

                if (addressResponse1.getStatus().equalsIgnoreCase("success")) {
                    Utility.toastMessage(mContext, "Address remove successfully");
                    arrayList.remove(deleteIndex);
//                    mAddressAdapter.notifyItemRemoved(deleteIndex);
                    mAddressListAdapter.notifyDataSetChanged();
                }
                break;

            case AppConstants.APICode.ADDRESS_UPDATE_DEFAULT:
                AddressResponse addressResponse2 = new Gson().fromJson(response, AddressResponse
                        .class);

                if(addressResponse2.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0"))
                {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }

                if (addressResponse2.getStatus().equalsIgnoreCase("success")) {
                    Utility.toastMessage(mContext, "Billing address saved successfully");

//                    ActivityCheckoutOrderReview.address_billing = selectedAddress;
                    startActivity(new Intent(mContext, ActivityShippingAddress.class));
                    finish();
                } else {
                    Utility.toastMessage(mContext, "Billing address not saved!!.\n Try again...");
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
