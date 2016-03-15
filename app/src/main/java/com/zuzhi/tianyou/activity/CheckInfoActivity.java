package com.zuzhi.tianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.MainActivity;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.TopicLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.HotServiceAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.OrderStatusAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.Cons;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * check info activity 查看详情（订单）页面
 */
public class CheckInfoActivity extends BaseActivity implements View.OnClickListener {

    /**
     * order status recyclerview 订单状态列表
     */
    RecyclerView rv_status;

    /**
     * recommend for you recyclerview 为您推荐列表
     */
    RecyclerView rv_recommend;

    @Override
    protected int setContent() {
        return R.layout.activity_check_info;
    }

    @Override
    protected void initViews() {
        //find views
        rv_status = (RecyclerView) findViewById(R.id.rv_check_info_order_info);
        rv_recommend = (RecyclerView) findViewById(R.id.rv_check_info_recommend);

        //set adapters
        OrderStatusAdapter adp_orderStatus = new OrderStatusAdapter(this);
        rv_status.setAdapter(adp_orderStatus);
        rv_status.setLayoutManager(new TopicLayoutManager(this, OrientationHelper.VERTICAL, false, Cons.IDARR_ORDER_COLOR.length));

//        HotServiceAdapter adp_hotService = new HotServiceAdapter(this, data_hotService);
//        rv_recommend.setAdapter(adp_hotService);
//        rv_recommend.setLayoutManager(new TopicLayoutManager(this, OrientationHelper.VERTICAL, false, data_hotService.size()));

        //set listeners
//        adp_hotService.setOnItemClickLitener(new HotServiceAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(
//                        CheckInfoActivity.this, CommodityInfoActivity.class);
//                startActivity(intent);
//            }
//        });
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

        tv_title_bar_text.setText(R.string.check_info);

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
