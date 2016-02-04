package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.TopicLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.CertificateAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.EvaluateAdapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.CommodityInfoAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ViewSetUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * commodity information activity 商品详情页
 */
public class CommodityInfoActivity extends BaseActivity implements View.OnClickListener, com.bigkoo.alertview.OnItemClickListener, com.bigkoo.alertview.OnDismissListener {

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

    /**
     * collection layout 收藏布局
     */
    private LinearLayout ll_collection;

    /**
     * collection check box 收藏状态钮
     */
    private CheckBox cb_collection;

    /**
     * contanct us layout 联系我们布局
     */
    private LinearLayout ll_contact_us;

    /**
     * contanct us button 联系我们按钮
     */
    private Button bt_contact_us;

    /**
     * coolection boolean 是否收藏
     */
    private Boolean b_isCollect = false;

    /**
     * bottom alertview icon list下部弹出对话框图组
     */
    private Drawable[] mDrawables;

    /**
     * text price2 价格2
     *
     * @return
     */
    private TextView tv_commodity_info_price2;

    /**
     * company name layout 公司名字布局
     */
    RelativeLayout rl_company_name;

    /**
     * text buy right now 立即购买文本
     */
    private TextView tv_buy;

    @Override
    protected int setContent() {
        return R.layout.activity_commodity_info;
    }

    @Override
    protected void initViews() {
        //set sheet alert icon
        mDrawables = new Drawable[]{
                getResources().getDrawable(R.drawable.ser_service_blue),
                getResources().getDrawable(R.drawable.ser_phone)
        };
        //finde views
        rv_certificate = (RecyclerView) findViewById(R.id.rv_commodity_info_certificate);
        tv_service_details = (TextView) LayoutInflater.from(this).inflate(R.layout.item_viewpager_service_details, null);
        rv_evaluate = new RecyclerView(this);
        tl_commodity_info = (TabLayout) findViewById(R.id.tl_commodity_info);
        vp_commdity_info = (ViewPager) findViewById(R.id.vp_commodity_info);
        bt_back = (Button) findViewById(R.id.bt_commodity_back);
        ll_collection = (LinearLayout) findViewById(R.id.ll_commodity_collection);
        cb_collection = (CheckBox) findViewById(R.id.cb_commodity_info_cellection);
        ll_contact_us = (LinearLayout) findViewById(R.id.ll_commodity_contact_us);
        bt_contact_us = (Button) findViewById(R.id.bt_commodity_info_contact_us);
        tv_commodity_info_price2 = (TextView) findViewById(R.id.tv_commodity_info_price2);
        tv_commodity_info_price2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        rl_company_name = (RelativeLayout) findViewById(R.id.rl_commodity_info_company_name);
        tv_buy = (TextView) findViewById(R.id.tv_commodity_info_buy);


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
        adp_evaluate.setOnItemClickLitener(new EvaluateAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    //start all evaluate activity 启动全部评价页面
                    Intent intent = new Intent(CommodityInfoActivity.this, AllEvaluateActivity.class);
                    startActivity(intent);
                }
            }
        });
        rv_evaluate.setAdapter(adp_evaluate);
        rv_evaluate.setLayoutManager(new LinearLayoutManager(this));
        ViewSetUtils.setRecyclverViewHeightBasedOnChildren(rv_evaluate);
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
        ll_collection.setOnClickListener(this);
        cb_collection.setOnClickListener(this);
        ll_contact_us.setOnClickListener(this);
        bt_contact_us.setOnClickListener(this);
        rl_company_name.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        //work out the problem : when the edittext in scrollview, edittext can't get the focus

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        NormalSteep(getWindow().getDecorView());
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            //back 返回
            case R.id.bt_commodity_back:
                finish();
                break;
            //collect 收藏
            case R.id.cb_commodity_info_cellection:
            case R.id.ll_commodity_collection:
                b_isCollect = !b_isCollect;
                cb_collection.setChecked(b_isCollect);
                break;
            //contact us 联系我们
            case R.id.ll_commodity_contact_us:
            case R.id.bt_commodity_info_contact_us:
                //alert customs dialog 弹出自己定对话框
                new AlertView(mDrawables, null, getResources().getString(R.string.warrning),
                        getResources().getString(R.string.cancel),
                        new String[]{getResources().getString(R.string.send_message),
                                getResources().getString(R.string.our_phone)},
                        null, this, AlertView.Style.ActionSheet, this)
                        .setCancelable(true)
                        .setOnDismissListener(this)
                        .show();
                break;
            //comany name 公司名字
            case R.id.rl_commodity_info_company_name:
                //start company info activity 启动商户详情页面
                intent = new Intent(this, CompanyInfoActivity.class);
                startActivity(intent);
                break;
            //buy right now 立即购买
            case R.id.tv_commodity_info_buy:
                //start confirm order activity 启动确认订单页面
                intent = new Intent(this, ConfirmOrderActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDismiss(Object o) {

    }

    @Override
    public void onItemClick(Object o, int position) {
        Logs.i(Cons.ACTIVITY_COMMODITYINFO, "点击了位置为" + position + "的按钮");
        switch (position) {
            //send message 发送消息
            case 0:

                break;
            //call cellphone 拨打电话
            case 1:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.our_phone)));
                startActivity(intent);
                break;
        }
    }
}
