package com.mohit.shopebazardroid.utility;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by msp on 12/8/16.
 */
abstract class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
    private int mScrollThreshold;

    abstract void onScrollUp();

    abstract void onScrollDown();

    abstract void setScrollThreshold();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;
        if (isSignificantDelta) {
            if (dy > 0) {
                onScrollUp();
                Log.i("Abscroll", "Rview up");
            } else {
                onScrollDown();
                Log.i("Abscroll", "RView down");
            }
        }
    }

    public void setScrollThreshold(int scrollThreshold) {
        mScrollThreshold = scrollThreshold;
        Log.i("Abscroll", "RView thresh " + scrollThreshold);
    }
}