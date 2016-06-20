package com.collin.fastloanapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
public class AppWebView extends WebView {

    public AppWebView(Context context) {
        super(context);
        init();
    }

    public AppWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType((Build.VERSION.SDK_INT >= 19) ? LAYER_TYPE_HARDWARE : LAYER_TYPE_SOFTWARE, null);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setUseWideViewPort(true);

        setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                AppWebView.this.loadUrl(url);
                if (url.startsWith("http") && pageInfoListener != null)
                    pageInfoListener.onStartLoadUrl(url);
                if (progressView != null)
                    progressView.start();
                return true;
            }

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                if (pageInfoListener != null)
                    pageInfoListener.onPageError(view.getUrl());
            }

            public void onPageFinished(WebView view, String url) {
                if (pageInfoListener != null)
                    pageInfoListener.onPageLoadComplete(url);
                if (progressView != null)
                    progressView.stop(true);
            }
        });
    }

    @Override
    public void destroy() {
        removeAllViews();
        setPageInfoListener(null);
        setWebViewClient(null);
        setWebChromeClient(null);
        bindProgressView(null);
    }

    private SimplePageInfoListener pageInfoListener;
    private WvProgressView progressView;

    /**
     * 页面信息监听回调
     */
    public void setPageInfoListener(SimplePageInfoListener pageInfoListener) {
        this.pageInfoListener = pageInfoListener;
    }

    /**
     * 绑定进度条
     */
    public void bindProgressView(WvProgressView pv) {
        this.progressView = pv;
    }

    public interface PageInfoListener {
        /*开始加载*/
        void onStartLoadUrl(String url);
        /*加载结束*/
        void onPageLoadComplete(String url);
        /*页面错误*/
        void onPageError(String url);
    }

    public static class SimplePageInfoListener implements PageInfoListener {
        public void onStartLoadUrl(String url) {

        }

        public void onPageLoadComplete(String url) {

        }

        public void onPageError(String url) {

        }
    }

}
