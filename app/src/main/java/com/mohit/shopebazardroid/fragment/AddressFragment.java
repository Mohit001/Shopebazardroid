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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Address.AddUpdateAddressActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.AddressAdapter;
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
 * Created by msp on 27/7/16.
 */
public class AddressFragment extends BaseFragment implements AddressListner, View.OnClickListener, ApiResponse {

    public static String TAG = AddressFragment.class.getSimpleName();
    Context mContext;
    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;
    Button submit_btn;

    List<Address> arrayList;
    int deleteIndex = -1;

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
        if(arrayList != null && arrayList.size() > 0)
            arrayList.clear();

        getAddressList();
        if(isUserLogin() && submit_btn != null)
            submit_btn.setVisibility(View.VISIBLE);
        else if(submit_btn != null){
            submit_btn.setVisibility(View.GONE);
        }
    }

    private void getAddressList() {
        if(isUserLogin()){
            String userid = getUserid();
            HTTPWebRequest.AddressList(mContext, userid, AppConstants.APICode.ADDRESS_LIST, this,
                    getFragmentManager());
        }
    }

    private void setupAddress() {
        arrayList = new ArrayList<>();


        Address address1 = new Address();
        address1.setFull_name("Home address");
        address1.setAddress1("ksjdf tkhsdnf");
        address1.setAddress2("jdjdjd");
        address1.setState("Asdf");
        address1.setCity("kasjdfh");
        address1.setPostcode("84848");
        address1.setEmail("test@gmail.com");
        address1.setDefault_value(0);
        arrayList.add(address1);

        Address address2 = new Address();
        address2.setFull_name("Home address");
        address2.setAddress1("ksjdf tkhsdnf");
        address2.setAddress2("jdjdjd");
        address2.setState("Asdf");
        address2.setCity("kasjdfh");
        address2.setPostcode("84848");
        address2.setEmail("test@gmail.com");
        address2.setDefault_value(0);
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
        String addressID = String.valueOf(address.getAddress_id());
        HTTPWebRequest.AddressDelete(mContext, addressID, AppConstants.APICode.ADDRESS_DELETE,
                this, getFragmentManager());

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

                Type addressListType =  new TypeToken<BaseResponse<List<Address>>>(){}.getType();
                BaseResponse<List<Address>> addressResponse = new Gson().fromJson(response, addressListType);

                if (addressResponse.getInfo().size() != 0) {

                    if(arrayList == null){
                        arrayList = new ArrayList<>();
                    }

                    arrayList.addAll(addressResponse.getInfo());
                    mAdapter = new AddressAdapter(mContext, arrayList, this, false, 0);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Utility.toastMessage(mContext, "No Address found");
                }
                break;
            case AppConstants.APICode.ADDRESS_DELETE:
                Type removeAddressType = new TypeToken<BaseResponse<List<Address>>>(){}.getType();
                BaseResponse<List<Address>> removeAddresResponse = new Gson().fromJson(response, removeAddressType);
                Toast.makeText(mContext, removeAddresResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if(removeAddresResponse.getStatus() == 1 && deleteIndex != -1){

                    arrayList.remove(deleteIndex);
                    deleteIndex = -1;
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(mAdapter);
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
