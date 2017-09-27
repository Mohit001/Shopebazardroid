package com.mohit.shopebazardroid.listener;

/**
 * Created by msp on 26/7/16.
 */
public interface CartListner {
    void onQuentityDecreaseClick(int index);
    void onQuentityIncreaseClick(int index);
    void onProductDeleteClick(int index);

}
