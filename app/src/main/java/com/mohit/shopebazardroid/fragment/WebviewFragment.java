package com.mohit.shopebazardroid.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.thefinestartist.finestwebview.FinestWebView;

/**
 * Created by msp on 21/7/16.
 */
public class WebviewFragment extends BaseFragment {

    public static String TAG = WebviewFragment.class.getSimpleName();
    Context mContext;
    String weburlString="";
    WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        webView = (WebView) view.findViewById(R.id.webview);

        weburlString = getArguments().getString(AppConstants.WEB_URL);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        /*WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);*/
//        webView.setWebViewClient(new MyAppWebViewClient());
//        webView.setWebChromeClient(new MyChromiumClient());
//        Utility.toastMessage(mContext, weburlString);
//        webView.loadUrl(weburlString);

//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("http://www.google.com");


        new FinestWebView.Builder(mContext)
//                .titleDefault("The Finest Artist")
                .show("http://www.google.co.in");
    }


    public class MyAppWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }
    }

    private class MyChromiumClient extends WebChromeClient{
        public MyChromiumClient() {
            super();
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }


    }
}
