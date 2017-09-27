package com.mohit.shopebazardroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Address.AddUpdateAddressActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.AddressAdapter;
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
 * Created by msp on 27/7/16.
 */
public class AddressFragment extends BaseFragment implements AddressListner, View.OnClickListener, ApiResponse {

    public static String TAG = AddressFragment.class.getSimpleName();
    Context mContext;
    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;
    Button submit_btn;

    ArrayList<Address> arrayList;
    int deleteIndex;

    int themeCode;

    boolean show_cart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);
//        addAddressFab = (AnimatedFloatingActionButtonBottom) view.findViewById(R.id
//                .add_address_fab);

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        submit_btn = (Button) view.findViewById(R.id.submit_btn);
        submit_btn.setTypeface(SplashActivity.opensans_regular);
        submit_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getContext();

//        addAddressFab.attachToRecyclerView(mRecyclerView);
        layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

//        addAddressFab.setOnClickListener(this);

        if(isUserLogin())
            submit_btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();

//        setupAddress();
        getAddressList();
        if(isUserLogin() && submit_btn != null)
            submit_btn.setVisibility(View.VISIBLE);
        else if(submit_btn != null){
            submit_btn.setVisibility(View.GONE);
        }
    }

    private void getAddressList() {
        if(isUserLogin()){
            AddressRequest request = new AddressRequest();
            request.setUser_id(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                    .USER_ID, "0"));
            HTTPWebRequest.AddressList(mContext, request, AppConstants.APICode.ADDRESS_LIST, this,
                    getFragmentManager());
        }
    }

    private void setupAddress() {
        arrayList = new ArrayList<>();


        Address address1 = new Address();
        address1.setFirstname("Home address");
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
        address2.setFirstname("Office address");
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

        mAdapter = new AddressAdapter(mContext, arrayList, this, false, 0);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onEditAddressClick(int index) {
//        Utility.toastMessage(mContext, "Edit address clicked");
        /*AddUpdateAddressActivity.address = arrayList.get(index);
        Intent i = new Intent(getActivity(), AddUpdateAddressActivity.class);
        i.putExtra("key", "edit");
        startActivity(i);*/
        Address address = arrayList.get(index);
        Bundle bundle = new Bundle();
        bundle.putSerializable("address", address);

        Intent i = new Intent(mContext, AddUpdateAddressActivity.class);
        i.putExtra("key", "edit");
        i.putExtra("bundle", bundle);
        i.putExtra(AppConstants.INTENTDATA.Show_cart, true);
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
                this, getFragmentManager());

        /*arrayList.remove(index);
        if(arrayList.size() > 0)
        {
            Address address = arrayList.get(0);
            address.setSelected(true);
            arrayList.set(0, address);
        }
        mAdapter.notifyDataSetChanged();*/
    }

    @Override
    public void onSelectionChange(int index) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.add_address_fab:
//                startActivity(new Intent(mContext, AddUpdateAddressActivity.class));
////                Utility.toastMessage(mContext, "Add address clicked");
//                break;
            case R.id.submit_btn:

                Intent i = new Intent(getActivity(), AddUpdateAddressActivity.class);
                i.putExtra("key", "add");
                i.putExtra(AppConstants.INTENTDATA.Show_cart, true);
                startActivity(i);
                break;
        }
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

                if (addressResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                if (addressResponse.getResult().getAddress() != null
                        && addressResponse.getResult().getAddress().size() > 0) {
                    arrayList = addressResponse.getResult().getAddress();
                    Address address;
                    for (int i = 0; i < arrayList.size(); i++) {
                        address = arrayList.get(i);
                        if (address.is_default_billing())
                            address.setSelected(true);
                        else
                            address.setSelected(false);

                        arrayList.set(i, address);
                    }

                    mAdapter = new AddressAdapter(mContext, arrayList, this, false, 0);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Utility.toastMessage(mContext, "No Address found");
                }
                break;
            case AppConstants.APICode.ADDRESS_DELETE:
                AddressResponse addressResponse1 = new Gson().fromJson(response, AddressResponse
                        .class);
                if (addressResponse1.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                if (addressResponse1.getStatus().equalsIgnoreCase("success")) {

                    Utility.toastMessage(mContext, "Address remove successfully");
                    arrayList.remove(deleteIndex);
                    mAdapter.notifyDataSetChanged();
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
