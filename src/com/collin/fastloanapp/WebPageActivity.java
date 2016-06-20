package com.collin.fastloanapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
*  类名：WebPageActivity.java
 * 注释：网页界面
 * 日期：2016年6月15日
 * 作者：王超
 */
public class WebPageActivity extends Activity {
	private TextView mTvTitle = null;
	
	private AppWebView mWvContent = null;
	private String mUrl = null;
	private String mTitle = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_page);
		initView();
		initData();
	}
	
	private void initView() {
		mTvTitle = (TextView) findViewById(R.id.tv_title);
		
		mWvContent = (AppWebView) findViewById(R.id.webView);
		mWvContent.bindProgressView((WvProgressView) findViewById(R.id.pb));
	}
	
	private void initData() {
		mUrl = getIntent().getStringExtra("url");
		mTitle = getIntent().getStringExtra("title");
		mWvContent.loadUrl(mUrl);
		mTvTitle.setText(mTitle);
	}
	
}
