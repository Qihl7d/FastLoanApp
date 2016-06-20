package com.collin.fastloanapp;

import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
*  类名：MainActivity.java
 * 注释：首页
 * 日期：2016年6月4日
 * 作者：王超
 */
public class MainActivity extends Activity implements OnClickListener {
	private final String mPageName = "MainActivity";
	private long mExitTime = 0;
	
	private TextView mTvAskFor01 = null;
	private TextView mTvAskFor02 = null;
	private TextView mTvAskFor03 = null;
	private TextView mTvAskFor04 = null;
	private LinearLayout mLlAskForNow = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ToolContext.initContext(this);
		MobclickAgent.setCatchUncaughtExceptions(true); 
		MobclickAgent.setScenarioType(this, EScenarioType.E_UM_NORMAL);
		initView();
		setListener();
	}
	
	private void initView() {
		mTvAskFor01 = (TextView) findViewById(R.id.tv_ask_for_first);
		mTvAskFor02 = (TextView) findViewById(R.id.tv_ask_for_second);
		mTvAskFor03 = (TextView) findViewById(R.id.tv_ask_for_third);
		mTvAskFor04 = (TextView) findViewById(R.id.tv_ask_for_fourth);
		mLlAskForNow = (LinearLayout) findViewById(R.id.ll_ask_for_now);
	}
	
	private void setListener() {
		mTvAskFor01.setOnClickListener(this);
		mTvAskFor02.setOnClickListener(this);
		mTvAskFor03.setOnClickListener(this);
		mTvAskFor04.setOnClickListener(this);
		mLlAskForNow.setOnClickListener(this);
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(mPageName);
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageStart(mPageName);
		MobclickAgent.onPause(this);
	}
	
	@Override
	public void onClick(View v) {
		String url = "";
		//Intent intent = new Intent(Intent.ACTION_VIEW);
		//intent.setData(Uri.parse(url));
		Intent intent = new Intent(MainActivity.this, WebPageActivity.class);
		switch (v.getId()) {
		case R.id.tv_ask_for_first:
			url = "http://m.rong360.com/express?from=sem21&utm_source=wanluo&utm_medium=cpa&utm_campaign=1";
			intent.putExtra("url", url);
			intent.putExtra("title", "融360");
	        startActivity(intent);
			break;
		case R.id.tv_ask_for_second:
			url = "http://ios.wecash.net/wap/simple_h5.html?version=h5&channelId=80&channelCode=70071a";
			intent.putExtra("url", url);
			intent.putExtra("title", "闪银");
	        startActivity(intent);
			break;
		case R.id.tv_ask_for_third:
			url = "http://m.rong360.com/express?from=sem21&utm_source=wanluo&utm_medium=cpa&utm_campaign=1";
			intent.putExtra("url", url);
			intent.putExtra("title", "融360");
	        startActivity(intent);
			break;
		case R.id.tv_ask_for_fourth:
			url = "http://8.yun.haodai.com/?ref=hd_11012802";
			intent.putExtra("url", url);
			intent.putExtra("title", "好贷");
	        startActivity(intent);
			break;
		case R.id.ll_ask_for_now:
			url = "http://ios.wecash.net/wap/simple_h5.html?version=h5&channelId=80&channelCode=70071a";
			intent.putExtra("url", url);
			intent.putExtra("title", "闪银");
	        startActivity(intent);
			break;
		}
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			doubleClickToExit();
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
    
	private boolean doubleClickToExit() {
		if ((System.currentTimeMillis() - mExitTime) > 2000) {
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			mExitTime = System.currentTimeMillis();
		} else {
			finish();
		}
		return true;
	}
	
}
