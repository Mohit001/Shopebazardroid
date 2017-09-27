package com.mohit.shopebazardroid.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by msp on 19/9/16.
 */




public class CustomeTextviewforSpinner extends TextView {

    public CustomeTextviewforSpinner(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public CustomeTextviewforSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public CustomeTextviewforSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "OPENSANS-REGULAR.TTF");
        setTypeface(typeface);
    }
}
