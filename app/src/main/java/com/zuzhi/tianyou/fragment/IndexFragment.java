package com.zuzhi.tianyou.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.activity.CommodityInfoActivity;
import com.zuzhi.tianyou.activity.IndexClassListActivity;
import com.zuzhi.tianyou.adapter.ImagePagerAdapter;
import com.zuzhi.tianyou.adapter.layoutmanager.GuideLayoutManager;
import com.zuzhi.tianyou.adapter.layoutmanager.TopicLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.HotServiceAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.IndexGuideAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.IndexTopicAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.VisitHistoryAdapter;
import com.zuzhi.tianyou.base.BaseFragment;
import com.zuzhi.tianyou.bean.BannerImageBaseBean;
import com.zuzhi.tianyou.entity.ImageEntity;
import com.zuzhi.tianyou.utils.AMapUtils;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ViewSetUtils;
import com.zuzhi.tianyou.views.AutoScrollViewPager;

import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;


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
    private RecyclerView rv_visit_history;

    /**
     * recyclerview of guide 导航栏列表
     */
    private RecyclerView rv_guide;

    /**
     * recyclerview of topic 推荐列表
     */
    private RecyclerView rv_topic;

    /**
     * recyclerview of hot service 热门服务列表
     */
    private RecyclerView rv_hot_service;

    /**
     * layout of phone contact 电话联系布局
     */
    private LinearLayout ll_phone_contact;

    /**
     * titlebar 标题栏透传
     */
    LinearLayout titleBar;

    public IndexFragment(){};

    public IndexFragment(LinearLayout titleBar) {
        this.titleBar = titleBar;
    }

    @Override
    protected void initTitleBar(View view) {

    }

    @Override
    protected void setTitleBar() {
        if (titleBar.getVisibility() != View.VISIBLE) {
            titleBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int setLayoutID() {
        return R.layout.fragment_index;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initView(View view) {
        //init nearly visti test data
        ArrayList<HashMap<String, Object>> data_visitHistory = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("string", "最近访问" + i);
            data_visitHistory.add(map);
        }

        //init guide test data
        ArrayList<HashMap<String, Object>> data_guide = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < Cons.STRARR_INDEX_GUIDE.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("string", Cons.STRARR_INDEX_GUIDE[i]);
            map.put("image", getResources().getDrawable(Cons.ID_DRAWABLE_INDEX_GUIDE[i]));
            data_guide.add(map);
        }

        //init topic test data
        ArrayList<HashMap<String, Object>> data_topic = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < Cons.STRARR_INDEX_TOPIC.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("topic", Cons.STRARR_INDEX_TOPIC[i]);
            map.put("arr_title", Cons.STRARR_INDEX_TOPIC_TITLE[i]);
            map.put("arr_info", Cons.STRARR_INDEX_TOPIC_INFO[i]);
            Drawable[] drawables = new Drawable[Cons.IDARR_INDEX_TOPIC_IMG[i].length];
            for (int j = 0; j < drawables.length; j++) {
                drawables[j] = getResources().getDrawable(Cons.IDARR_INDEX_TOPIC_IMG[i][j]);
            }
            map.put("arr_img", drawables);
            data_topic.add(map);
        }


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

        //find views
        asvp_banner = (AutoScrollViewPager) view.findViewById(R.id.asvp_banner);
        ll_pointer_banner = (LinearLayout) view.findViewById(R.id.ll_pointer_banner);
        rv_visit_history = (RecyclerView) view.findViewById(R.id.rv_visit_history);
        rv_guide = (RecyclerView) view.findViewById(R.id.rv_index_guide);
        rv_topic = (RecyclerView) view.findViewById(R.id.rv_index_topic);
        rv_hot_service = (RecyclerView) view.findViewById(R.id.rv_index_hot_service);
        ll_phone_contact = (LinearLayout) view.findViewById(R.id.ll_index_phone_contact);


        //set adapters
        HotServiceAdapter adp_hotService = new HotServiceAdapter(getContext(), data_hotService);
        rv_hot_service.setAdapter(adp_hotService);
        rv_hot_service.setLayoutManager(new TopicLayoutManager(getContext(), OrientationHelper.VERTICAL, false, data_hotService.size()));

        com.zuzhi.tianyou.adapter.recyclerviewadapter.IndexTopicAdapter adp_topic =
                new com.zuzhi.tianyou.adapter.recyclerviewadapter.IndexTopicAdapter(getContext(), data_topic);
        rv_topic.setAdapter(adp_topic);
        rv_topic.setLayoutManager(new TopicLayoutManager(getContext(), OrientationHelper.VERTICAL, false, data_topic.size()));

        VisitHistoryAdapter adp_visitHistory = new VisitHistoryAdapter(getContext(), data_visitHistory);
        rv_visit_history.setAdapter(adp_visitHistory);
        rv_visit_history.setLayoutManager(new LinearLayoutManager(getContext(), OrientationHelper.HORIZONTAL, false));

        IndexGuideAdapter adp_guide = new IndexGuideAdapter(getContext(), data_guide);
        rv_guide.setAdapter(adp_guide);
        rv_guide.setLayoutManager(new GuideLayoutManager(getContext(), 4, data_guide.size()));

//        rv_guide.setLayoutManager(new GridLayoutManager(getContext(), 4));

        //set the proportion of autoscrollviewpager 设置轮播宽高比
        ViewSetUtils.setViewHeigh(getContext(), asvp_banner, 2.5f, 1);

        //set listeners
        ll_phone_contact.setOnClickListener(this);
        adp_topic.setOnItemClickLitener(new IndexTopicAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //start class level activity 启动类目列表页面
                Intent intent = new Intent(getContext(), IndexClassListActivity.class);
                getContext().startActivity(intent);
            }
        });
        adp_guide.setOnItemClickLitener(new IndexGuideAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //start class level activity 启动类目列表页面
                Intent intent = new Intent(getContext(), IndexClassListActivity.class);
                getContext().startActivity(intent);
            }
        });
        adp_hotService.setOnItemClickLitener(new HotServiceAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //start class level activity 启动商品详情页面
                Intent intent = new Intent(getContext(), CommodityInfoActivity.class);
                getContext().startActivity(intent);
            }
        });
        //get image from internet
        getImage();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //call phone 拨打电话
            case R.id.ll_index_phone_contact:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.setData(Uri.parse("tel:" + 10086));
                startActivity(intent);
                break;
        }
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

    @Override
    //Fragment 从隐藏切换至显示，会调用onHiddenChanged(boolean hidden)方法
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (titleBar.getVisibility() != View.VISIBLE) {
                titleBar.setVisibility(View.VISIBLE);
            }
        }
    }
}

