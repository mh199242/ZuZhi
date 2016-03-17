package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.zuzhi.tianyou.adapter.ImagePagerAdapter;
import com.zuzhi.tianyou.adapter.layoutmanager.TopicLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.HotServiceAdapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.ClassListAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.bean.BannerImageBean;
import com.zuzhi.tianyou.bean.ClassListBean;
import com.zuzhi.tianyou.bean.IndexBean;
import com.zuzhi.tianyou.bean.ItemListBean;
import com.zuzhi.tianyou.entity.AdEntity;
import com.zuzhi.tianyou.entity.ImageEntity;
import com.zuzhi.tianyou.entity.ItemListEntity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;
import com.zuzhi.tianyou.utils.ViewSetUtils;
import com.zuzhi.tianyou.views.AutoScrollViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * index class list activity 首页类目列表页
 */

public class IndexClassListActivity extends BaseActivity implements View.OnClickListener {

    /**
     * used to browse item list's position
     */
    Set<Integer> browseSet = new HashSet<Integer>();

    /**
     * ad id
     */
    String adId;

    /**
     * ItemListEntity list
     */
    List<ItemListEntity> mItemList;

    /**
     * Class List java Bean
     */
    ClassListBean mClassListBean;

    /**
     * Item List java Bean
     */
    ItemListBean mItemListBean;

    /**
     * Class List CategoryEntity list
     */
    List<ClassListBean.ValueEntity.CategoryEntity> mCategoryList;

    private String TAG = "com.zuzhi.tianyou.activity.IndexClassListActivity";
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

    /**
     * 轮播适配器
     */
    ImagePagerAdapter adp_ip;

    @Override
    protected int setContent() {
        return R.layout.activity_index_class_list;
    }

    @Override
    protected void initViews() {
        //get class list data
        classList();
        //find views
        asvp_banner = (AutoScrollViewPager) findViewById(R.id.asvp_banner);
        ll_pointer_banner = (LinearLayout) findViewById(R.id.ll_pointer_banner);
        vp_class_list = (ViewPager) findViewById(R.id.vp_class_list);
        tl_class_list = (TabLayout) findViewById(R.id.tl_class_list);
    }

    @Override
    protected void initTitleBar() {
        rl_title_bar_search = (RelativeLayout) findViewById(R.id.rl_title_bar_search);
        ll_title_bar_left = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        bt_title_bar_left = (Button) findViewById(R.id.bt_title_bar_left);
        bt_title_bar_search = (Button) findViewById(R.id.bt_title_bar_search);
        ll_title_bar_right = (LinearLayout) findViewById(R.id.ll_title_bar_right);
        tv_title_bar_right = (TextView) findViewById(R.id.tv_title_bar_right);
//        abl_class_list_tool = (AppBarLayout) findViewById(R.id.abl_class_list_tool);
    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TitileBarSteep(getWindow().getDecorView());
        } else {
//            RelativeLayout.LayoutParams params =
//                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//            params.height = ViewSetUtils.dp2px(this, 44);
//            abl_class_list_tool.setLayoutParams(params);
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
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url);
        MyApplication.getInstance().queue.add(0, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                JSONObject jsonObject = null;
                if (response.get() == null) {
                    ToastUtil.showToast(IndexClassListActivity.this, getResources().getString(R.string.data_error));
                    return;
                }
                jsonObject = response.get();
                BannerImageBean bean = MyApplication.gson.fromJson(jsonObject.toString(), BannerImageBean.class);
                List<ImageEntity> list = bean.data;
                list_Entity_Banner.addAll(list);
                Message msg = Message.obtain();
                msg.what = 0x01;
                mHandler.sendMessage(msg);

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(IndexClassListActivity.this, getResources().getString(R.string.request_fail));
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }


    /**
     * set banner options 设置轮播属性
     */
    private void setBanner() {
//        // TODO Auto-generated method stub
//        adp_ip = new ImagePagerAdapter(this, list_Entity_Banner).setInfiniteLoop(true);
//        asvp_banner.setAdapter(adp_ip);
//        asvp_banner.setOnPageChangeListener(new MyOnPageChangeListener());
//        asvp_banner.setInterval(5000);
//        asvp_banner.startAutoScroll();
//        asvp_banner.setCurrentItem(100 - 100 % list_Entity_Banner.size());
//        setBannerIndex(ll_pointer_banner, 0);
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
        asvp_banner.stopAutoScroll();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        asvp_banner.startAutoScroll();
    }


    /**
     * get class list data
     */
    public void classList() {
        if (!EaseCommonUtils.isNetWorkConnected(mContext)) {
            Toast.makeText(mContext, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(getIntent().getStringExtra("adId"))) {
            ToastUtil.showToast(mContext, getString(R.string.data_error));
            return;
        } else {
            adId = getIntent().getStringExtra("adId");
        }


        // NoHttp zuzhi class list
        String url = Cons.DOMAIN + Cons.CLASS_LIST
                + "?adId="
                + adId;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.GET);

        Logs.i("足智类目列表", "---------url---------");
        Logs.i("足智类目列表", url);


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
                    Logs.i("足智类目列表", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {
                        mClassListBean = MyApplication.gson.fromJson(jsonObject.toString(),
                                ClassListBean.class);
                        mCategoryList = mClassListBean.getValue().getCategory();
                        onInitData();
                        browseSet.add(0);
                        getItemData(0);
                    } else {
                        ToastUtil.showToast(mContext, jsonObject.getString("errorMessage"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(mContext, getResources().getString(R.string.request_fail));
                Logs.i("足智类目列表", "----------Error-------");
                Logs.i("足智类目列表", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    /**
     * init view data
     */
    private void onInitData() {
        //add views
        for (int i = 0; i < mCategoryList.size(); i++) {
            //init and set up wiht recyclerview adapter 初始化并设置recyclerview适配器
            rv_class_list = new RecyclerView(this);

            //add test viewpager views
            vp_class_list.addView(rv_class_list);
            //add test viewpager view to list
            list_views.add(rv_class_list);
            //add tab views
            list_tabs.add(mCategoryList.get(i).getName());
            tl_class_list.addTab(tl_class_list.newTab().setText(list_tabs.get(i)));
        }


        //set up with viewpager adpater 给ViewPager设置适配器
        ClassListAdapter adp_classList = new ClassListAdapter(list_views, list_tabs);
        vp_class_list.setAdapter(adp_classList);
        vp_class_list.setOnPageChangeListener(new MyViewPagerChangeListener());

        //set up with tablayout adpater给Tabs设置适配器
        tl_class_list.setTabsFromPagerAdapter(adp_classList);

        //tablayout setup with viewPager 将TabLayout和ViewPager关联起来。
        tl_class_list.setupWithViewPager(vp_class_list);

        //set the proportion of autoscrollviewpager 设置轮播宽高比
        ViewSetUtils.setViewHeigh(this, asvp_banner, 2, 1);

        //get image from internet
        getImage();
    }


    /**
     * get item data
     */
    private void getItemData(final int position) {

        if (!EaseCommonUtils.isNetWorkConnected(mContext)) {
            Toast.makeText(mContext, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }

        // NoHttp zuzhi item
        String url = Cons.DOMAIN + Cons.ITEM;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);
        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("adId",
                    String.valueOf(mCategoryList.get(position).getId()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("足智物品列表", "---------url---------");
        Logs.i("足智物品列表", url);

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
                    Logs.i("足智物品列表", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {
                        mItemListBean = MyApplication.gson.fromJson(jsonObject.toString(),
                                ItemListBean.class);
                        initItemList(mItemListBean.getValue());
                        initItemData(position);
                    } else {
                        ToastUtil.showToast(mContext, jsonObject.getString("errorMessage"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(mContext, getResources().getString(R.string.request_fail));
                Logs.i("足智物品列表", "----------Error-------");
                Logs.i("足智物品列表", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    /**
     * set item data to views
     */
    private void initItemData(int position) {
        HotServiceAdapter adp_hotService = new HotServiceAdapter(this, mItemList);
        adp_hotService.setOnItemClickLitener(new HotServiceAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //start commodity info activity 启动商品详情页
                Intent intent = new Intent(mContext, CommodityInfoActivity.class);
                intent.putExtra("itemId", String.valueOf(mItemList.get(position).getId()));
                startActivity(intent);
            }
        });
        ((RecyclerView) list_views.get(position)).setAdapter(adp_hotService);
        ((RecyclerView) list_views.get(position)).setLayoutManager(
                new TopicLayoutManager(this, OrientationHelper.VERTICAL, false, mItemList.size()));

    }

    class MyViewPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //check used to browse data
            for (int i : browseSet) {
                if (i == position)
                    return;
            }
            //if not browse,then get data
            browseSet.add(position);
            getItemData(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    }

    /**
     * change data to ItemList
     *
     * @param list
     */
    private void initItemList(List<ItemListBean.ValueEntity> list) {
        mItemList = new ArrayList<ItemListEntity>();
        for (int i = 0; i < list.size(); i++) {
            ItemListEntity entity = new ItemListEntity();
            entity.setItemShopPrice(list.get(i).getItemShopPrice());
            entity.setItemPromoteEndDate(list.get(i).getItemPromoteEndDate());
            entity.setItemPromoteStartDate(list.get(i).getItemPromoteStartDate());
            entity.setExpertId(list.get(i).getExpertId());
            entity.setExpertName(list.get(i).getExpertName());
            entity.setId(list.get(i).getId());
            entity.setItemPromotePrice(list.get(i).getItemPromotePrice());
            entity.setItemMarketPrice(list.get(i).getItemMarketPrice());
            entity.setItemPromote(list.get(i).isItemPromote());
            entity.setShopName(list.get(i).getShopName());
            entity.setShopId(list.get(i).getShopId());
            entity.setItemImg(list.get(i).getItemImg());
            entity.setName(list.get(i).getName());
            entity.setExpertWorkingHours(list.get(i).getExpertWorkingHours());
            entity.setItemThumbImg(list.get(i).getItemThumbImg());
            mItemList.add(entity);
        }

    }
}

