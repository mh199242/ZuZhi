package com.zuzhi.tianyou.fragment;

import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.ImagePagerAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.IndexGuideAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.NearlyVisitAdapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.IndexConsultAapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.IndexGuideAapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.IndexIPOAapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.IndexProxyAdapter;
import com.zuzhi.tianyou.base.BaseFragment;
import com.zuzhi.tianyou.bean.BannerImageBaseBean;
import com.zuzhi.tianyou.entity.ImageEntity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ViewSetUtils;
import com.zuzhi.tianyou.views.AutoScrollViewPager;

import android.os.Handler;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class IndexFragment extends BaseFragment implements View.OnClickListener {

    /**
     * data of class category 首页数据源
     */
    private ArrayList<HashMap<String, Object>> mData;

    /**
     * banner entity list 轮播图片实体类列表
     */
    private List<ImageEntity> list_Entity_Banner = new ArrayList<ImageEntity>();

    /**
     * banner 轮播器
     */
    private AutoScrollViewPager asvp_banner;

    /**
     * list of banner pointer 轮播指示器列表
     */
    private List<ImageView> list_bannerPointer = new ArrayList<ImageView>();

    /**
     * banner pointer 轮播指示器
     */
    private LinearLayout ll_pointer_banner;

    /**
     * handler 消息分发者
     */
    private Handler mHandler;

    /**
     * position of banner pointer 轮播指示器下标
     */
    private int i_bannerPointerIndex;

    /**
     * tag of download banner 轮播图组下载tag
     */
    private boolean b_bannerDownload = false;

    /**
     * recyclerview of nearly visit 最近访问列表
     */
    private RecyclerView rv_nearly_visit;

    /**
     * recyclerview of guide 导航栏列表
     */
    private RecyclerView rv_guide;

    /**
     * viewpager of report 鉴证报告页卡
     */
    private ViewPager vp_report;

    /**
     * viewpager of consult 顾问咨询页卡
     */
    private ViewPager vp_consult;

    /**
     * viewpager of ipo 上市服务页卡
     */
    private ViewPager vp_ipo;

    /**
     * viewpager of business proxy 商务代理页卡
     */
    private ViewPager vp_proxy;

    /**
     * viewpager of gold master 金牌专家页卡
     */
    private ViewPager vp_master;



    /**
     * view list of report viewpager 鉴证报告页卡view列表
     */
    private ArrayList<View> list_reportViews = new ArrayList<View>();

    /**
     * view list of report viewpager 顾问咨询页卡view列表
     */
    private ArrayList<View> list_consultViews = new ArrayList<View>();

    /**
     * view list of business proxy 商务代理view列表
     */
    private ArrayList<View> list_proxyViews = new ArrayList<View>();

    /**
     * view list of ipo viewpager 上市服务页卡view列表
     */
    private ArrayList<View> list_ipoViews = new ArrayList<View>();

    /**
     * view list of master viewpager 金牌专家页卡view列表
     */
    private ArrayList<View> list_masterViews = new ArrayList<View>();

    /**
     * item layout of report viewpager 鉴证报告页卡item布局
     */
    private LinearLayout ll_index_report;

    /**
     * item layout of consult viewpager 顾问咨询页卡item布局
     */
    private LinearLayout ll_index_consult;


    @Override
    protected void initTitleBar(View view) {

    }

    @Override
    protected void setTitleBar() {

    }

    @Override
    protected int setLayoutID() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView(View view) {
        //init nearly visti test data
        ArrayList<HashMap<String, Object>> data_nearlyVisit = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("string", "最近访问" + i);
            data_nearlyVisit.add(map);
        }

        //init guide test data
        ArrayList<HashMap<String, Object>> data_guide = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < Cons.STRARR_INDEX_GUIDE.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("string", Cons.STRARR_INDEX_GUIDE[i]);
            map.put("image", getResources().getDrawable(Cons.ID_DRAWABLE_INDEX_GUIDE[i]));
            data_guide.add(map);
        }


        //find views
        asvp_banner = (AutoScrollViewPager) view.findViewById(R.id.asvp_banner);
        ll_pointer_banner = (LinearLayout) view.findViewById(R.id.ll_pointer_banner);
        rv_nearly_visit = (RecyclerView) view.findViewById(R.id.rv_nearly_visit);
        rv_guide = (RecyclerView) view.findViewById(R.id.rv_index_guide);
        vp_report = (ViewPager) view.findViewById(R.id.vp_index_report);
        vp_consult = (ViewPager) view.findViewById(R.id.vp_index_consult);
        vp_ipo = (ViewPager) view.findViewById(R.id.vp_index_ipo);
        vp_proxy = (ViewPager) view.findViewById(R.id.vp_index_proxy);
        vp_master = (ViewPager) view.findViewById(R.id.vp_index_master);

        ll_index_consult = (LinearLayout) LayoutInflater.from(getContext()).
                inflate(R.layout.item_viewpager_index_consult, null);
        ll_index_report = (LinearLayout) LayoutInflater.from(getContext()).
                inflate(R.layout.item_viewpager_index_report, null);
//        //log the single view'width in viewpager 记录页卡中单个view的测量宽度
//        i_singleWidth_reprotViewPaer = ll_index_report.
//                findViewById(R.id.ll_item_viepager_index_report1).getMeasuredWidth();
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        params.height = i_singleWidth_reprotViewPaer;
//init report test data
        ArrayList<HashMap<String, Object>> data_report = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < Cons.STRARR_INDEX_REPORT_TITLE.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("title", Cons.STRARR_INDEX_REPORT_TITLE[i]);
            map.put("info", Cons.STRARR_INDEX_REPORT_INFO[i]);
            map.put("image", getResources().getDrawable(R.drawable.temp_vp_report2));
            data_guide.add(map);
            View v_tempReport = LayoutInflater.from(
                    getContext()).
                    inflate(R.layout.item_viewpager_index_report, null);
            list_reportViews.add(v_tempReport);

            View v_tempConsult = LayoutInflater.from(
                    getContext()).
                    inflate(R.layout.item_viewpager_index_consult, null);
            list_consultViews.add(v_tempConsult);

            View v_tempIpo = LayoutInflater.from(
                    getContext()).
                    inflate(R.layout.item_viewpager_index_report, null);
            list_ipoViews.add(v_tempIpo);

            View v_tempPorxy = LayoutInflater.from(
                    getContext()).
                    inflate(R.layout.item_viewpager_index_consult, null);
            list_proxyViews.add(v_tempPorxy);

            View v_tempMaster = LayoutInflater.from(
                    getContext()).
                    inflate(R.layout.item_viewpager_index_report, null);
            list_masterViews.add(v_tempMaster);
        }
        //set adapters
        vp_report.setAdapter(new IndexGuideAapter(list_reportViews));

        vp_consult.setAdapter(new IndexConsultAapter(list_consultViews));

        vp_ipo.setAdapter(new IndexIPOAapter(list_ipoViews));

        vp_proxy.setAdapter(new IndexProxyAdapter(list_proxyViews));

        vp_master.setAdapter(new IndexIPOAapter(list_masterViews));

        NearlyVisitAdapter adp_nearlyVisit = new NearlyVisitAdapter(getContext(), data_nearlyVisit);
        rv_nearly_visit.setAdapter(adp_nearlyVisit);
        rv_nearly_visit.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));

        IndexGuideAdapter adp_guide = new IndexGuideAdapter(getContext(), data_guide);
        rv_guide.setAdapter(adp_guide);
        rv_guide.setLayoutManager(new GridLayoutManager(getContext(), 4));

        //set the proportion of autoscrollviewpager 设置轮播宽高比
        ViewSetUtils.setViewHeigh(getContext(), asvp_banner, 2.5f, 1);
        getImage();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        asvp_banner.startAutoScroll();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        asvp_banner.startAutoScroll();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        asvp_banner.stopAutoScroll();
    }

    /**
     * get image for net 联网获取轮播图
     */
    private void getImage() {

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    //complete download 下载完毕
                    case 0x01:
                        b_bannerDownload = true;
                        setBanner();
                        break;
                    //banner scroll message 轮播图滚动
                    case 0x02:
                        if (b_bannerDownload) {
                            setBannerIndex(ll_pointer_banner, (i_bannerPointerIndex) % list_Entity_Banner.size());
                        }
                        break;

                }
            }
        };

        String url = "http://byh.qweweq.com/index.php/App/index/banner";

        //create a volley request queue for url 根据给定的URL新建一个请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //ui safe 在这里操作UI组件是安全的，因为响应返回时这个函数会被post到UI线程来执行
                        // 在这里尽情蹂躏响应的String。
                        Logs.i(Cons.FRAMENT_INDEX, response);
                        BannerImageBaseBean bean = MyApplication.gson.fromJson(response, BannerImageBaseBean.class);
                        List<ImageEntity> list = bean.data;
                        list_Entity_Banner.addAll(list);
                        Message msg = Message.obtain();
                        msg.what = 0x01;
                        mHandler.sendMessage(msg);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error 出错了怎么办？凉拌！并且在这里拌。
            }
        });
        //add the request to volley request queue把这个请求加入请求队列
        MyApplication.mVolleyQueue.add(stringRequest);

    }


    /**
     * set banner options 设置轮播属性
     */
    private void setBanner() {
        // TODO Auto-generated method stub
        asvp_banner.setAdapter(new ImagePagerAdapter(getContext(), list_Entity_Banner).setInfiniteLoop(true));
        asvp_banner.setOnPageChangeListener(new MyOnPageChangeListener());
        asvp_banner.setInterval(5000);
        asvp_banner.startAutoScroll();
        asvp_banner.setCurrentItem(100 - 100 % list_Entity_Banner.size());
        setBannerIndex(ll_pointer_banner, 0);
    }


    /**
     * set banner index 设置banner下标
     *
     * @param viewgroup view group of banner pointer 轮播指示器容器
     * @param position  current position 当前位置
     * @return
     */
    private List<ImageView> setBannerIndex(LinearLayout viewgroup, int position) {

        int size = list_Entity_Banner == null ? 0 : list_Entity_Banner.size();

        if (size == 0)
            return null;

        viewgroup.removeAllViews();

        for (int i = 0; i < size; i++) {

            ImageView iv_temp = new ImageView(getContext());
            iv_temp.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            iv_temp.setPadding(5, 0, 5, 0);
            iv_temp.setMinimumHeight((int) ViewSetUtils.px2dp(getContext(), 15));
            iv_temp.setMaxHeight((int) ViewSetUtils.px2dp(getContext(), 15));
            iv_temp.setMinimumWidth((int) ViewSetUtils.px2dp(getContext(), 15));
            iv_temp.setMaxWidth((int) ViewSetUtils.px2dp(getContext(), 15));

            if (i == position) {
                iv_temp.setImageResource(R.drawable.bp_focus);
            } else {
                iv_temp.setImageResource(R.drawable.bp_disable);
            }

            list_bannerPointer.add(iv_temp);

            viewgroup.addView(iv_temp);
        }

        return list_bannerPointer;
    }

    /**
     * banner's listener 轮播图监听
     *
     * @author Administrator
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            i_bannerPointerIndex = position;

            Message msg = Message.obtain();
            msg.what = 0x02;
            mHandler.sendMessage(msg);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
}

