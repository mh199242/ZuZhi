package com.zuzhi.tianyou.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.MyCouponAdapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.MyOrderAdapter;
import com.zuzhi.tianyou.base.BaseActivity;

import java.util.ArrayList;

/**
 * my order activity 我的优惠券
 */
public class MyCouponActivity extends BaseActivity implements View.OnClickListener {
    /**
     * tablayout 标签页
     */
    private TabLayout tl_my_coupon;

    /**
     * viewpager(recyclerview) 页卡（内容为recyclerview）
     */
    private ViewPager vp_my_Coupon;

    /**
     * rv_canUseCoupon recyclerview 未使用优惠券列表
     */
    private RecyclerView rv_canUseCoupon;
    /**
     * unavailable recyclerview 不可用优惠券列表
     */
    private RecyclerView rl_unavailable;

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
        tl_my_coupon = (TabLayout) findViewById(R.id.tl_my_order);
        vp_my_Coupon = (ViewPager) findViewById(R.id.vp_my_order);

        //add view to viewpager
        rv_canUseCoupon = new RecyclerView(this);
        rv_canUseCoupon.setBackgroundColor(getResources().getColor(R.color.color_bg_identifying_code_disable));
        MyCouponAdapter canUseAdapter = new MyCouponAdapter(this, true);
        rv_canUseCoupon.setAdapter(canUseAdapter);
        rv_canUseCoupon.setLayoutManager(new LinearLayoutManager(this));

        rl_unavailable = new RecyclerView(this);
        rl_unavailable.setBackgroundColor(getResources().getColor(R.color.color_bg_identifying_code_disable));
        MyCouponAdapter unavailableAdapter = new MyCouponAdapter(this, false);
        rl_unavailable.setAdapter(unavailableAdapter);
        rl_unavailable.setLayoutManager(new LinearLayoutManager(this));

        list_views.add(rv_canUseCoupon);
        list_tabs.add(getResources().getString(R.string.can_use));
        list_views.add(rl_unavailable);
        list_tabs.add(getResources().getString(R.string.unavailable));

        vp_my_Coupon.addView(rv_canUseCoupon);
        vp_my_Coupon.addView(rl_unavailable);
        for (int i = 0; i < list_tabs.size(); i++) {
            tl_my_coupon.addTab(tl_my_coupon.newTab().setText(list_tabs.get(i)));
        }

        vp_my_Coupon.setAdapter(new MyOrderAdapter(list_views, list_tabs));
        tl_my_coupon.setupWithViewPager(vp_my_Coupon);
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

        tv_title_bar_text.setText(R.string.my_coupon);

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
