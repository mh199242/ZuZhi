package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.MyOrderAdapter;
import com.zuzhi.tianyou.base.BaseActivity;

import java.util.ArrayList;

/**
 * my order activity 我的订单页
 */
public class MyOrderActivity extends BaseActivity implements View.OnClickListener {
    /**
     * tablayout 标签页
     */
    private TabLayout tl_my_order;

    /**
     * viewpager(recyclerview) 页卡（内容为recyclerview）
     */
    private ViewPager vp_my_order;

    /**
     * processsing recyclerview 进行中订单列表
     */
    private RecyclerView rv_processing_order;

    /**
     * completed recyclerview 已完成的订单列表
     */
    private RecyclerView rv_completed_order;

    /**
     * viewpager's view list 页卡列表
     */
    private ArrayList<View> list_views = new ArrayList<View>();

    /**
     * viewpager's view list 页卡标签列表
     */
    private ArrayList<String> list_tabs = new ArrayList<String>();

    @Override
    protected int setContent() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initViews() {
        //find views
        tl_my_order = (TabLayout) findViewById(R.id.tl_my_order);
        vp_my_order = (ViewPager) findViewById(R.id.vp_my_order);

        //add view to viewpager
        rv_processing_order = new RecyclerView(this);
        MyOrderAdapter adp_processing = new MyOrderAdapter(this);
        adp_processing.setOnItemClickLitener(new MyOrderAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent in = new Intent(MyOrderActivity.this, OrderInfoActivity.class);
                MyOrderActivity.this.startActivity(in);
            }
        });
        adp_processing.setOrderType(adp_processing.PROCESSING);
        rv_processing_order.setAdapter(adp_processing);
        rv_processing_order.setLayoutManager(new LinearLayoutManager(this));

        rv_completed_order = new RecyclerView(this);
        MyOrderAdapter adp_completed = new MyOrderAdapter(this);
        adp_completed.setOrderType(adp_processing.COMPLETED);
        rv_completed_order.setAdapter(adp_completed);
        rv_completed_order.setLayoutManager(new LinearLayoutManager(this));

        list_views.add(rv_processing_order);
        list_tabs.add(getResources().getString(R.string.processing));
        list_views.add(rv_completed_order);
        list_tabs.add(getResources().getString(R.string.completed));

        vp_my_order.addView(rv_processing_order);
        for (int i = 0; i < list_tabs.size(); i++) {
            tl_my_order.addTab(tl_my_order.newTab().setText(list_tabs.get(i)));
        }

        vp_my_order.setAdapter(new com.zuzhi.tianyou.adapter.viewpageradapter.MyOrderAdapter(list_views, list_tabs));
        tl_my_order.setupWithViewPager(vp_my_order);
    }

    @Override
    protected void initTitleBar() {
        ll_title_bar_left = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        tv_title_bar_text = (TextView) findViewById(R.id.tv_title_bar_title);
        bt_title_bar_left = (Button) findViewById(R.id.bt_title_bar_left);
    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        TitileBarSteep(getWindow().getDecorView());

        ll_title_bar_left.setVisibility(View.VISIBLE);
        tv_title_bar_text.setVisibility(View.VISIBLE);
        bt_title_bar_left.setVisibility(View.VISIBLE);

        tv_title_bar_text.setText(R.string.my_order);

        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //back 返回键
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                finish();
                break;
        }
    }
}
