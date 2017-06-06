package com.bb.offerapp2.fragment.viewpaper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bb.offerapp2.R;

/**
 * Created by bb on 2016/11/18.
 */
public class BFragment extends Fragment {


    private String url = "http://music.baidu.com/songs/europe/";
    private WebView webView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View  view = inflater.inflate(R.layout.recycle_b_fragment, container, false);
        webView = (WebView) view.findViewById(R.id.webView);

        init();
        return view;
    }

    private void init() {
        // 覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebVIew中打开
        webView.setWebViewClient(new WebViewClient());
        //启用支持JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }


}
