package com.zuzhi.tianyou.adapter.viewpageradapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * adater of class list viewpager 类目列表页卡适配器
 * Created by 超 on 2016/1/27.
 */
public class ClassListAdapter extends PagerAdapter {
    /**
     * viewgroup source 容器源
     */
    private ArrayList<View> mVies;

    /**
     * constructor of adapter 适配器构造器
     * @param views view列表
     */
    public ClassListAdapter(ArrayList<View> views){
        mVies = views;
    }
    //viewpager中的组件数量
    @Override
    public int getCount() {
        return mVies.size();
    }
    //滑动切换的时候销毁当前的组件
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        ((ViewPager) container).removeView(mVies.get(position));
    }
    //每次滑动的时候生成的组件
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(mVies.get(position).getParent() == null)
        ((ViewPager) container).addView(mVies.get(position));
        return mVies.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  super.getPageTitle(position);
//        return mViewGroup.get(position);
    }
}
