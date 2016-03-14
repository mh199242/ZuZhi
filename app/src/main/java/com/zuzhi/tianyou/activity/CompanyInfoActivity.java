package com.zuzhi.tianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.easeui.utils.EaseCommonUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.TopicLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.CertificateAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.HotServiceAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.MastersAdapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.CompanyInfoAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.bean.IndexBean;
import com.zuzhi.tianyou.bean.LoginBean;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;
import com.zuzhi.tianyou.utils.ViewSetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * company information activity 公司信息页
 */
public class CompanyInfoActivity extends BaseActivity implements View.OnClickListener {

    /**
     * back button 返回按钮
     */
    Button bt_back;

    /**
     * view list of viewpager页卡列表
     */
    ArrayList<View> list_view = new ArrayList<View>();

    /**
     * tab list of viewpager页卡标签项列表
     */
    ArrayList<String> list_tab = new ArrayList<String>();

    /**
     * viewpager 页卡
     */
    ViewPager vp_company_info;

    /**
     * tablayout 页卡标签
     */
    TabLayout tl_company_info;

    /**
     * company service recyclerview 商家服务列表
     */
    RecyclerView rv_company_service;

    /**
     * company masters recyclerview 公司专家团列表
     */
    RecyclerView rv_masters;

    /**
     * company  certifacate recyclerview 公司证书列表
     */
    RecyclerView rv_certificate;

    /**
     * company details 公司介绍
     */
    ScrollView sv_details;

    /**
     * company id
     */
    String comanyId;

    @Override
    protected int setContent() {
        return R.layout.activity_company_info;
    }

    @Override
    protected void initViews() {
        //init hot service test data
        getCompanyInfo();



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
        //init certificate test data
        ArrayList<HashMap<String, Object>> data_certificate = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("certificate", "证书" + i);
            data_certificate.add(map);
        }
        //init views
        vp_company_info = (ViewPager) findViewById(R.id.vp_company_info);
        tl_company_info = (TabLayout) findViewById(R.id.tl_company_info);
        sv_details = (ScrollView) LayoutInflater.from(this).inflate(R.layout.item_viewpager_company_details, null);
        rv_certificate = (RecyclerView) sv_details.findViewById(R.id.rv_company_details_certificate);

        CertificateAdapter adp_certificate = new CertificateAdapter(this, data_certificate);
        rv_certificate.setAdapter(adp_certificate);
        rv_certificate.setLayoutManager(new TopicLayoutManager(this, OrientationHelper.VERTICAL, false, data_certificate.size()));

        rv_company_service = new RecyclerView(this);
        HotServiceAdapter adp_hotService = new HotServiceAdapter(this, data_hotService);
        adp_hotService.setOnItemClickLitener(new HotServiceAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //start commodity info activity  启动商品详情页
                Intent intent = new Intent(CompanyInfoActivity.this, CommodityInfoActivity.class);
                startActivity(intent);
            }
        });
        rv_company_service.setAdapter(adp_hotService);
        rv_company_service.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rv_company_service.setLayoutManager(new LinearLayoutManager(this));

        rv_masters = new RecyclerView(this);
        MastersAdapter adp_masters = new MastersAdapter(this);
        rv_masters.setAdapter(adp_masters);
        rv_company_service.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rv_masters.setLayoutManager(new LinearLayoutManager(this));

        //add views
        list_view.add(rv_company_service);
        list_view.add(rv_masters);
        list_view.add(sv_details);

        list_tab.add(getResources().getString(R.string.service_list));
        list_tab.add(getResources().getString(R.string.company_masters));
        list_tab.add(getResources().getString(R.string.company_details));

        for (int i = 0; i < list_view.size(); i++) {
            vp_company_info.addView(list_view.get(i));
            tl_company_info.addTab(tl_company_info.newTab().setText(list_tab.get(i)));
        }

        //set up viewpager
        vp_company_info.setAdapter(new CompanyInfoAdapter(list_view, list_tab));
        vp_company_info.setOffscreenPageLimit(3);
//        ViewSetUtils.setViewHeigh(this, vp_company_info, 1, 5);
        tl_company_info.setupWithViewPager(vp_company_info);

    }

    @Override
    protected void initTitleBar() {
        bt_back = (Button) findViewById(R.id.bt_company_back);
    }

    @Override
    protected void setTitleBar() {
        //open steep mode 沉浸模式
        NormalSteep(getWindow().getDecorView());

        bt_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //back button 返回键
            case R.id.bt_company_back:
                finish();
                break;
        }
    }

    /**
     * get company info 获取公司信息
     */
    public void getCompanyInfo() {
        //check network state
        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }
        DialogUtils.showProgressDialog(this, getString(R.string.loading));

        //get company id
        if (getIntent().getExtras().getSerializable("AdEntity") != null) {
            IndexBean.ValueEntity.AdEntity adEntity
                    = (IndexBean.ValueEntity.AdEntity) getIntent().getExtras().getSerializable("AdEntity");
            comanyId = adEntity.getObjId();
        }
        // NoHttp zuzhi shop details
        String url = Cons.DOMAIN + Cons.SHOP_DETAILS;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("shopId", comanyId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("足智店铺", "---------url---------");
        Logs.i("足智店铺", url);

        MyApplication.getInstance().queue.add(0, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                JSONObject jsonObject = null;
                try {
                    if (response.get() == null) {
                        ToastUtil.showToast(mContext, getResources().getString(R.string.data_error));
                        return;
                    }
                    jsonObject = response.get();
                    Logs.i("足智店铺", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {

                    } else {
                        ToastUtil.showToast(mContext, jsonObject.getString("errorMessage"));
                        DialogUtils.dismissProgressDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(mContext, getResources().getString(R.string.request_fail));
                Logs.i("足智店铺", "----------Error-------");
                Logs.i("足智店铺", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }
}
