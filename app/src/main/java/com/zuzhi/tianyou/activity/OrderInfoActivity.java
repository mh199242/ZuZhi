package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.TopicLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.HotServiceAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.Cons;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * opinion request activity 订单详情页
 */
public class OrderInfoActivity extends BaseActivity implements View.OnClickListener {

    EditText et_opinion;
    Button bt_opinion_submit;
    RecyclerView rv_recommend;

    ScrollView scrollView;

    /**
     * layout of company name 公司名称布局
     */
    LinearLayout ll_company_name;

    /**
     * refund button 退款按钮
     */
    Button bt_refund;

    /**
     * check info button 查看详情按钮
     */
    Button bt_check_info;

    /**
     * rating button 评价按钮
     */
    Button bt_rating;

    @Override
    protected int setContent() {
        return R.layout.activity_order_info;
    }

    @Override
    protected void initViews() {
        //find views
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        ll_company_name = (LinearLayout) findViewById(R.id.ll_order_info_company_name);
        bt_refund = (Button) findViewById(R.id.bt_order_info_refund);
        bt_check_info = (Button) findViewById(R.id.bt_order_info_check_info);
        bt_rating = (Button) findViewById(R.id.bt_order_info_rating);

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

        //重画recylerview以后会显示最下面，手动滚动到顶端
        scrollView.smoothScrollTo(0, 0);
        rv_recommend = (RecyclerView) findViewById(R.id.rv_recommend);
        HotServiceAdapter adp_hotService = new HotServiceAdapter(this, data_hotService);
        rv_recommend.setAdapter(adp_hotService);
        rv_recommend.setLayoutManager(new TopicLayoutManager(this, OrientationHelper.VERTICAL, false, data_hotService.size()));

        //set listeners
        adp_hotService.setOnItemClickLitener(new HotServiceAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(
                        OrderInfoActivity.this, CommodityInfoActivity.class);
                startActivity(intent);
            }
        });
        ll_company_name.setOnClickListener(this);
        bt_refund.setOnClickListener(this);
        bt_check_info.setOnClickListener(this);
        bt_rating.setOnClickListener(this);
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

        tv_title_bar_text.setText(R.string.order_info);
        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            //back 返回
            case R.id.bt_title_bar_left:
            case R.id.ll_title_bar_left:
                finish();
                break;
            //company name 公司名称
            case R.id.ll_order_info_company_name:
                //start companyinfo activity 启动公司信息页面
                intent = new Intent(this, CompanyInfoActivity.class);
                startActivity(intent);
                break;
            //refund 退款
            case R.id.bt_order_info_refund:
                //start refund activity 启动退款页面
                intent = new Intent(this, RefundActivity.class);
                startActivity(intent);
                break;
            //check info 查看详情
            case R.id.bt_order_info_check_info:
                //start check info activity 启动查看详情页面
                intent = new Intent(this, CheckInfoActivity.class);
                startActivity(intent);
                break;
            //rating 评价
            case R.id.bt_order_info_rating:
                //start rating activity 启动评价页面
                intent = new Intent(this, RatingActivity.class);
                startActivity(intent);
                break;

        }
    }
}
