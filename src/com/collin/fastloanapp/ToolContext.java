package com.collin.fastloanapp;

import android.content.Context;

/**
*  类名：ToolContext.java
 * 注释：拿到上下文工具
 * 日期：2016年6月15日
 * 作者：王超
 */
public abstract class ToolContext {
	private static Context context;

	public static void initContext(Context context) {
		ToolContext.context = context.getApplicationContext();
	}

	protected static Context getContext() {
		return context;
	}
}
