package com.zuzhi.tianyou.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.ImagePagerAdapter;
import com.zuzhi.tianyou.base.BaseFragment;
import com.zuzhi.tianyou.bean.BannerImageBaseBean;
import com.zuzhi.tianyou.entity.ImageEntity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ViewSetUtils;
import com.zuzhi.tianyou.views.AutoScrollViewPager;


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
        asvp_banner = (AutoScrollViewPager) view.findViewById(R.id.asvp_index);

        //set the proportion of autoscrollviewpager 设置轮播宽高比
        ViewSetUtils.setViewHeigh(getContext(), asvp_banner, 2, 1);
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

    /**
     * get image for net 联网获取轮播图
     */
    private void getImage() {

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
                        setBanner();
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
        asvp_banner.setAdapter(new ImagePagerAdapter(context, list_Entity_Banner).setInfiniteLoop(true));
        asvp_banner.setOnPageChangeListener(new MyOnPageChangeListener());
        asvp_banner.setInterval(5000);
        asvp_banner.startAutoScroll();
        asvp_banner.setCurrentItem(100 - 100 % list_Entity_Banner.size());
    }


    /**
     * banner's listener 轮播图监听
     *
     * @author Administrator
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
//            setGalleryIndex(layout, (position) % imageIdList.size());
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

