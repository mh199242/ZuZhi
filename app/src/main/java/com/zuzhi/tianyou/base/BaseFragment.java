package com.zuzhi.tianyou.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;




/**
 * BaseFragment 基Fragment
 * 
 * @author lichao
 * 
 */
public abstract class BaseFragment extends Fragment {

	/**
	 * left title bar layout 左标题栏布局
	 */
	protected RelativeLayout rl_title_bar_left;

	/**
	 *title text of title bar 标题栏标题文字
	 */
	protected TextView tv_title_bar_text;

	/**
	 * left button 左按键
	 */
	protected Button bt_title_bar_left;

	/**
	 * city 城市
	 */
	protected TextView tv_title_bar_city;

	/**
	 * search bar whith icon 图片按钮搜索栏
     */
	protected LinearLayout ll_title_bar_search_icon;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(setLayoutID(), null);
		initTitleBar(view);
		setTitleBar();
		initView(view);
		return view;
	}

	/**
	 * set title 设置标题
	 */
	protected abstract void setTitleBar();

	/**
	 * init title bar初始化标题栏
	 * @param view parent view 父view
	 */
	protected void initTitleBar(View view) {

	}


	/**
	 * set layout ID 设置布局文件
	 * 
	 * @return
	 */
	protected abstract int setLayoutID();

	/**
	 * inti Views 初始化控件
	 * 
	 * @param view parent view 父view
	 */
	protected abstract void initView(View view);


}
