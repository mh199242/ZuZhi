package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.ImagePagerAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.HotServiceAdapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.ClassListAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.bean.BannerImageBaseBean;
import com.zuzhi.tianyou.entity.ImageEntity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ViewSetUtils;
import com.zuzhi.tianyou.views.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class IndexClassListActivity extends BaseActivity implements View.OnClickListener {

    /**
     * tablayout 标签页
     */
    private TabLayout tl_class_list;

    /**
     * viewpager(recyclerview) 页卡（内容为recyclerview）
     */
    private ViewPager vp_class_list;

    /**
     * viewpager content  页卡内容
     */
    private RecyclerView rv_class_list;

    /**
     * viewpager's view list 页卡列表
     */
    private ArrayList<View> list_views = new ArrayList<View>();

    /**
     * viewpager's view list 页卡标签列表
     */
    private ArrayList<String> list_tabs = new ArrayList<String>();

    /**
     * top tooll appbar   顶部toll appbar
     */
    private AppBarLayout abl_class_list_tool;

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

    @Override
    protected int setContent() {
        return R.layout.activity_class_list;
    }

    @Override
    protected void initViews() {
        //find views
        asvp_banner = (AutoScrollViewPager) findViewById(R.id.asvp_banner);
        ll_pointer_banner = (LinearLayout) findViewById(R.id.ll_pointer_banner);
        vp_class_list = (ViewPager) findViewById(R.id.vp_class_list);
        tl_class_list = (TabLayout) findViewById(R.id.tl_class_list);

        //init hot service test data 加载测试数据
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

        for (int i = 0; i < 8; i++) {
            //init and set up wiht recyclerview adapter 初始化并设置recyclerview适配器
            rv_class_list = new RecyclerView(this);
            HotServiceAdapter adp_hotService = new HotServiceAdapter(this, data_hotService);
            adp_hotService.setOnItemClickLitener(new HotServiceAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    //start commodity info activity 启动商品详情页
                    Intent intent = new Intent(mContext, CommodityInfoActivity.class);
                    startActivity(intent);
                }
            });
            rv_class_list.setAdapter(adp_hotService);
            //rv_class_list.setLayoutManager(new TopicLayoutManager(this, OrientationHelper.VERTICAL, false, data_hotService.size()));
            rv_class_list.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));

            //add test viewpager views
            vp_class_list.addView(rv_class_list);
            //add test viewpager view to list
            list_views.add(rv_class_list);
            //add tab views
            list_tabs.add("标签" + i);
            tl_class_list.addTab(tl_class_list.newTab().setText(list_tabs.get(i)));
        }

        //set up with viewpager adpater 给ViewPager设置适配器
        ClassListAdapter adp_classList = new ClassListAdapter(list_views, list_tabs);
        vp_class_list.setAdapter(adp_classList);

        //set up with tablayout adpater给Tabs设置适配器
        tl_class_list.setTabsFromPagerAdapter(adp_classList);

        //tablayout setup with viewPager 将TabLayout和ViewPager关联起来。
        tl_class_list.setupWithViewPager(vp_class_list);

        //set the proportion of autoscrollviewpager 设置轮播宽高比
        ViewSetUtils.setViewHeigh(this, asvp_banner, 3.5f, 1);

        //get image from internet
        getImage();


    }

    @Override
    protected void initTitleBar() {
        rl_title_bar_search = (RelativeLayout) findViewById(R.id.rl_title_bar_search);
        ll_title_bar_left = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        bt_title_bar_left = (Button) findViewById(R.id.bt_title_bar_left);
        bt_title_bar_search = (Button) findViewById(R.id.bt_title_bar_search);
        ll_title_bar_right = (LinearLayout) findViewById(R.id.ll_title_bar_right);
        tv_title_bar_right = (TextView) findViewById(R.id.tv_title_bar_right);
        abl_class_list_tool = (AppBarLayout) findViewById(R.id.abl_class_list_tool);
    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TitileBarSteep(getWindow().getDecorView());
        } else {
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = ViewSetUtils.dp2px(this, 44);
            abl_class_list_tool.setLayoutParams(params);
        }
        ll_title_bar_left.setVisibility(View.VISIBLE);
        bt_title_bar_left.setVisibility(View.VISIBLE);
        bt_title_bar_search.setVisibility(View.GONE);
        ll_title_bar_right.setVisibility(View.VISIBLE);
        tv_title_bar_right.setVisibility(View.VISIBLE);
        rl_title_bar_search.setVisibility(View.VISIBLE);

        tv_title_bar_right.setText(R.string.search);

        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //back 返回
            case R.id.bt_title_bar_left:
            case R.id.ll_title_bar_left:
                finish();
                break;
        }
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
        asvp_banner.setAdapter(new ImagePagerAdapter(this, list_Entity_Banner).setInfiniteLoop(true));
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

            ImageView iv_temp = new ImageView(this);
            iv_temp.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            iv_temp.setPadding(5, 0, 5, 0);
            iv_temp.setMinimumHeight((int) ViewSetUtils.px2dp(this, 15));
            iv_temp.setMaxHeight((int) ViewSetUtils.px2dp(this, 15));
            iv_temp.setMinimumWidth((int) ViewSetUtils.px2dp(this, 15));
            iv_temp.setMaxWidth((int) ViewSetUtils.px2dp(this, 15));

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
}
