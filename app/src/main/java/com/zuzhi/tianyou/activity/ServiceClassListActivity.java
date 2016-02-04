package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.TopicLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.HotServiceAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.Cons;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * service class list activity 服务类目列表页
 */
public class ServiceClassListActivity extends BaseActivity implements View.OnClickListener {

    /**
     * service class list recyclerview 服务类目列表
     */
    RecyclerView rv_service_class_list;

    /**
     * synthesize text 综合
     */
    TextView tv_synthesize;

    /**
     * sales volume text 销量
     */
    TextView tv_sales_volume;

    /**
     * price text 价格
     */
    TextView tv_price;

    /**
     * price image价格图片
     */
    ImageView iv_price;

    /**
     * price layout 价格布局
     */
    LinearLayout ll_price;

    /**
     * location text 所在地
     */
    TextView tv_location;

    @Override
    protected int setContent() {
        return R.layout.activity_service_class_list;
    }

    @Override
    protected void initViews() {
        //find views
        rv_service_class_list = (RecyclerView) findViewById(R.id.rv_service_class_list);
        tv_synthesize = (TextView) findViewById(R.id.tv_service_class_list_synthesize);
        tv_sales_volume = (TextView) findViewById(R.id.tv_service_class_list_sales_volume);
        tv_price = (TextView) findViewById(R.id.tv_service_class_list_price);
        iv_price = (ImageView) findViewById(R.id.iv_service_class_list_price);
        ll_price = (LinearLayout) findViewById(R.id.ll_service_class_list_price);
        tv_location = (TextView) findViewById(R.id.tv_service_class_list_location);

        //init hot service test data
        ArrayList<HashMap<String, Object>> data_hotService = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < Cons.STRARR_INDEX_HOT_SERVICE_TITLE.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("hot_service_img", getResources().getDrawable(Cons.IDARR_INDEX_HOT_SERVICE_IMG[i]));
            map.put("hot_service_title", Cons.STRARR_INDEX_HOT_SERVICE_TITLE[i]);
            map.put("hot_service_info1", Cons.STRARR_INDEX_HOT_SERVICE_INFO1[i]);
            map.put("hot_service_info2", Cons.STRARR_INDEX_HOT_SERVICE_INFO2[i]);
            map.put("hot_service_price1", Cons.STRARR_INDEX_HOT_SERVICE_PRICE1[i]);
            map.put("hot_service_price2", Cons.STRARR_INDEX_HOT_SERVICE_PRICE2[i]);
            map.put("hot_service_attribute", Cons.STRARR_INDEX_HOT_SERVICE_ATTRIBUTE[i]);
            data_hotService.add(map);
        }

        //set adapters
        HotServiceAdapter adp_hotService = new HotServiceAdapter(this, data_hotService);
        adp_hotService.setOnItemClickLitener(new HotServiceAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //start commodity information activity 启动商品详情页
                Intent intent = new Intent(ServiceClassListActivity.this, CommodityInfoActivity.class);
                startActivity(intent);
            }
        });
        rv_service_class_list.setAdapter(adp_hotService);
        rv_service_class_list.setLayoutManager(new LinearLayoutManager(this));

        //set listeners
        tv_synthesize.setOnClickListener(this);
        tv_sales_volume.setOnClickListener(this);
        ll_price.setOnClickListener(this);
        tv_location.setOnClickListener(this);
    }


    @Override
    protected void initTitleBar() {
        ll_title_bar_left = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        bt_title_bar_left = (Button) findViewById(R.id.bt_title_bar_left);
        rl_title_bar_search = (RelativeLayout) findViewById(R.id.rl_title_bar_search);
        bt_title_bar_search = (Button) findViewById(R.id.bt_title_bar_search);
        ll_title_bar_right = (LinearLayout) findViewById(R.id.ll_title_bar_right);
    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        TitileBarSteep(getWindow().getDecorView());

        ll_title_bar_left.setVisibility(View.VISIBLE);
        bt_title_bar_left.setVisibility(View.VISIBLE);
        rl_title_bar_search.setVisibility(View.VISIBLE);
        bt_title_bar_search.setVisibility(View.VISIBLE);
        ll_title_bar_right.setVisibility(View.VISIBLE);

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

            //synthesize 综合
            case R.id.tv_service_class_list_synthesize:
                tv_synthesize.setTextColor(getResources().getColor(R.color.color_bg_button_main_normal));
                tv_sales_volume.setTextColor(getResources().getColor(R.color.color_text_hint));
                tv_price.setTextColor(getResources().getColor(R.color.color_text_hint));
                iv_price.setBackgroundDrawable(getResources().getDrawable(R.drawable.ser_down));
                tv_location.setTextColor(getResources().getColor(R.color.color_text_hint));
                break;
            //salse volume 销量
            case R.id.tv_service_class_list_sales_volume:
                tv_synthesize.setTextColor(getResources().getColor(R.color.color_text_hint));
                tv_sales_volume.setTextColor(getResources().getColor(R.color.color_bg_button_main_normal));
                tv_price.setTextColor(getResources().getColor(R.color.color_text_hint));
                iv_price.setBackgroundDrawable(getResources().getDrawable(R.drawable.ser_down));
                tv_location.setTextColor(getResources().getColor(R.color.color_text_hint));
                break;

            //price 价格
            case R.id.ll_service_class_list_price:
                tv_synthesize.setTextColor(getResources().getColor(R.color.color_text_hint));
                tv_sales_volume.setTextColor(getResources().getColor(R.color.color_text_hint));
                tv_price.setTextColor(getResources().getColor(R.color.color_bg_button_main_normal));
                iv_price.setBackgroundDrawable(getResources().getDrawable(R.drawable.ser_down_yellow));
                tv_location.setTextColor(getResources().getColor(R.color.color_text_hint));
                break;
            //location 所在地
            case R.id.tv_service_class_list_location:
                tv_synthesize.setTextColor(getResources().getColor(R.color.color_text_hint));
                tv_sales_volume.setTextColor(getResources().getColor(R.color.color_text_hint));
                tv_price.setTextColor(getResources().getColor(R.color.color_text_hint));
                iv_price.setBackgroundDrawable(getResources().getDrawable(R.drawable.ser_down));
                tv_location.setTextColor(getResources().getColor(R.color.color_bg_button_main_normal));
                break;
        }
    }
}
