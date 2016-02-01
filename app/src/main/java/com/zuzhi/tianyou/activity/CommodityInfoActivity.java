package com.zuzhi.tianyou.activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.TopicLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.CertificateAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.EvaluateAdapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.CommodityInfoAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.ViewSetUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class CommodityInfoActivity extends BaseActivity implements View.OnClickListener {

    /**
     * commodity certificate recyclerview 商品证书列表
     */
    private RecyclerView rv_certificate;

    /**
     * tab layout(service details, evaluate) 标签页(服务详情, 评价)
     */
    private TabLayout tl_commodity_info;

    /**
     * viewpager (service details, evaluate) 页卡容器(服务详情, 评价)
     */
    private ViewPager vp_commdity_info;

    /**
     * text of service details 服务详情
     */
    private TextView tv_service_details;

    /**
     * evaluate recyclerview 评价列表
     */
    private RecyclerView rv_evaluate;

    /**
     * viewpager's view list 页卡列表
     */
    private ArrayList<View> list_views = new ArrayList<View>();

    /**
     * viewpager's view list 页卡标签列表
     */
    private ArrayList<String> list_tabs = new ArrayList<String>();

    /**
     * back button 返回
     */
    private Button bt_back;

    @Override
    protected int setContent() {
        return R.layout.activity_commodity_info;
    }

    @Override
    protected void initViews() {

        //finde views
        rv_certificate = (RecyclerView) findViewById(R.id.rv_commodity_info_certificate);
        tv_service_details = (TextView) LayoutInflater.from(this).inflate(R.layout.item_viewpager_service_details, null);
        rv_evaluate = new RecyclerView(this);
        tl_commodity_info = (TabLayout) findViewById(R.id.tl_commodity_info);
        vp_commdity_info = (ViewPager) findViewById(R.id.vp_commodity_info);
        bt_back = (Button) findViewById(R.id.bt_commodity_back);

        //init test data
        ArrayList<HashMap<String, Object>> data_certificate = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("certificate", "证书" + i);
            data_certificate.add(map);
        }

        ArrayList<HashMap<String, Object>> data_evaluate = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < Cons.IDARR_EVALUATE_HEAD.length + 1; i++) {
            HashMap<String, Object> map = map = new HashMap<String, Object>();
            if (i == 0) {
                map.put("percent", "98%");
                map.put("number", "53");
                data_evaluate.add(map);
            } else {
                map.put("head", getResources().getDrawable(Cons.IDARR_EVALUATE_HEAD[i - 1]));
                map.put("name", Cons.STRARR_EVALUATE_NAME[i - 1]);
                map.put("rating", Cons.RATINGARR_EVALUATE[i - 1]);
                map.put("date", Cons.STRARR_EVALUATE_DATE[i - 1]);
                map.put("content", Cons.STRARR_EVALUATE_CONTENT[i - 1]);
                data_evaluate.add(map);
            }
        }

        //set adapters
        CertificateAdapter adp_certificate = new CertificateAdapter(this, data_certificate);
        rv_certificate.setAdapter(adp_certificate);
        rv_certificate.setLayoutManager(new TopicLayoutManager(this, OrientationHelper.VERTICAL, false, data_certificate.size()));

        EvaluateAdapter adp_evaluate = new EvaluateAdapter(this, data_evaluate);
        rv_evaluate.setAdapter(adp_evaluate);
        rv_evaluate.setLayoutManager(new LinearLayoutManager(this));
//        rv_evaluate.setLayoutManager(new TopicLayoutManager(this, OrientationHelper.VERTICAL, false, data_evaluate.size()));

        //set viewpage
        //add viewpager views
        vp_commdity_info.addView(tv_service_details);
        list_views.add(tv_service_details);
        list_tabs.add(getResources().getString(R.string.service_details));

        vp_commdity_info.addView(rv_evaluate);
        list_views.add(rv_evaluate);
        list_tabs.add(getResources().getString(R.string.evaluate));

        //set up with viewpager adpater 给ViewPager设置适配器
        CommodityInfoAdapter adp_commodityInfo = new CommodityInfoAdapter(list_views, list_tabs);
        vp_commdity_info.setAdapter(adp_commodityInfo);
        ViewSetUtils.setViewHeigh(this, vp_commdity_info, 1f, 1f);

        //set up with tablayout adpater给Tabs设置适配器
        tl_commodity_info.setTabsFromPagerAdapter(adp_commodityInfo);

        //tablayout setup with viewPager 将TabLayout和ViewPager关联起来。
        tl_commodity_info.setupWithViewPager(vp_commdity_info);


        //set listeners
        bt_back.setOnClickListener(this);

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            //alpha status bar 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //alpha navigation bar 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_commodity_back:
                finish();
                break;
        }
    }
}
