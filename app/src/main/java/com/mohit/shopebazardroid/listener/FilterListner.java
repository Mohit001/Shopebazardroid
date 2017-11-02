package com.mohit.shopebazardroid.listener;


import com.mohit.shopebazardroid.model.response.ProductFilterAttributes;

import java.util.ArrayList;

/**
 * Created by msp on 13/8/16.
 */
public interface FilterListner {
    void onFilterClearClick();
    void onFilterCancelClick();
    void onFilterSubmitClick(ArrayList<ProductFilterAttributes> filterArrayList);
    void onOptionCheckchanged();
}
