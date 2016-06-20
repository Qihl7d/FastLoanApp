package com.collin.fastloanapp;

/**
*  类名：ResTool.java
 * 注释：资源获取工具
 * 日期：2016年6月15日
 * 作者：王超
 */
public class ResourceTool extends ToolContext {

	@SuppressWarnings("deprecation")
	public static int getColor(int id) {
		return getContext().getResources().getColor(id);
	}

	public static int getDimen(int id) {
		return (int) getContext().getResources().getDimension(id);
	}

	public static String getString(int id) {
		return getContext().getResources().getString(id);
	}

}