package com.mohit.shopebazardroid.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mohit.shopebazardroid.R;

public class WebView_Detail extends Fragment {

    WebView webview;
    ProgressDialog pd;
    Context context;

    String url_new;

    public static String TAG = WebView_Detail.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.webview_detail, container, false);

        try {
            url_new = getArguments().getString("url");

        } catch (Exception e) {
            e.printStackTrace();
        }

        webview = (WebView) rootView.findViewById(R.id.webview1);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        context = getActivity();

        pd = new ProgressDialog(context);
        pd.setMessage("wait...");
        pd.show();
        pd.setCancelable(false);

        setdetail();


    }


    private void setdetail() {

        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url_new);
        webview.setWebViewClient(new MyWebViewClient());

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            if (pd.isShowing()) {

                pd.dismiss();

            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

//        menu = inflater.inflate(R.menu.main_menu);


        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_cart).setVisible(false);

        super.onCreateOptionsMenu(menu, inflater);


    }
}
