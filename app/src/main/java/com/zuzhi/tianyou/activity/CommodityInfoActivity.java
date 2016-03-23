package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.easemob.easeui.utils.EaseCommonUtils;
import com.google.gson.annotations.Until;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.TopicLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.CertificateAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.EvaluateAdapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.CommodityInfoAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.bean.ItemDetailBean;
import com.zuzhi.tianyou.bean.LoginBean;
import com.zuzhi.tianyou.entity.ShopCertificateEntity;
import com.zuzhi.tianyou.im.Constant;
import com.zuzhi.tianyou.im.ui.ChatActivity;
import com.zuzhi.tianyou.utils.BitmapUtils;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;
import com.zuzhi.tianyou.utils.ViewSetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
     * itemDetail java bean
     */
    ItemDetailBean mItemDetailBean;


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
     * web of service details 服务详情
     */
    private WebView wv_service_details;

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
     * bottom alertview icon list下部弹出对话框图组
     */
    private Drawable[] mDrawables, mShareDrawables;


    /**
     * company name layout 公司名字布局
     */
    RelativeLayout rl_company_name;

    /**
     * text buy right now 立即购买文本
     */
    private TextView tv_buy;

    /**
     * item name
     */
    private TextView tv_title;

    /**
     * price 1
     */
    private TextView tv_price1;

    /**
     * price 2
     */
    private TextView tv_price2;

    /**
     * attribute
     */
    private TextView tv_attribute;

    /**
     * saled number
     */
    private TextView tv_saled;

    /**
     * worker name
     */
    private TextView tv_info1;

    /**
     * worker exp
     */
    private TextView tv_info2;

    /**
     * company name
     */
    private TextView tv_company_name;

    /**
     * company description
     */
    private TextView tv_company_des;

    /**
     * share layout
     */
    LinearLayout ll_commodity_share;


    /**
     * item id
     */
    private String itemId;

    /**
     * alretview
     */
    private AlertView av_contact, av_share;


    /**
     * is cellection
     */
    private boolean b_isCollection = false;

    @Override
    protected int setContent() {
        return R.layout.activity_commodity_info;
    }

    @Override
    protected void initViews() {
        mContext = this;
        DialogUtils.showProgressDialog(this, getString(R.string.loading));
        //get data from server
        getItemDetail();
        //set sheet alert icon
        mDrawables = new Drawable[]{
                getResources().getDrawable(R.drawable.ser_service_blue),
                getResources().getDrawable(R.drawable.ser_phone)
        };
        mShareDrawables = new Drawable[]{
                getResources().getDrawable(R.drawable.wechat),
                getResources().getDrawable(R.drawable.wechatmoments),
                getResources().getDrawable(R.drawable.qq),
                getResources().getDrawable(R.drawable.sinaweibo)
        };
        //finde views
        rv_certificate = (RecyclerView) findViewById(R.id.rv_commodity_info_certificate);
        wv_service_details = (WebView) LayoutInflater.from(this).inflate(R.layout.item_viewpager_service_details, null);
        rv_evaluate = new RecyclerView(this);
        tl_commodity_info = (TabLayout) findViewById(R.id.tl_commodity_info);
        vp_commdity_info = (ViewPager) findViewById(R.id.vp_commodity_info);
        bt_back = (Button) findViewById(R.id.bt_commodity_back);
        ll_collection = (LinearLayout) findViewById(R.id.ll_commodity_collection);
        cb_collection = (CheckBox) findViewById(R.id.cb_commodity_info_cellection);
        ll_contact_us = (LinearLayout) findViewById(R.id.ll_commodity_contact_us);
        bt_contact_us = (Button) findViewById(R.id.bt_commodity_info_contact_us);
        rl_company_name = (RelativeLayout) findViewById(R.id.rl_commodity_info_company_name);
        tv_buy = (TextView) findViewById(R.id.tv_commodity_info_buy);
        tv_title = (TextView) findViewById(R.id.tv_commodity_info_title);
        tv_price1 = (TextView) findViewById(R.id.tv_commodity_info_price1);
        tv_price2 = (TextView) findViewById(R.id.tv_commodity_info_price2);
        tv_attribute = (TextView) findViewById(R.id.tv_commodity_info_attribute);
        tv_saled = (TextView) findViewById(R.id.tv_commodity_info_saled);
        tv_info1 = (TextView) findViewById(R.id.tv_commodity_info_info1);
        tv_info2 = (TextView) findViewById(R.id.tv_commodity_info_info2);
        tv_company_name = (TextView) findViewById(R.id.tv_commodity_info_company_name);
        tv_company_des = (TextView) findViewById(R.id.tv_commodity_info_company_des);
        ll_commodity_share = (LinearLayout) findViewById(R.id.ll_commodity_share);

        av_contact = new AlertView(mDrawables, null, getResources().getString(R.string.warrning),
                getResources().getString(R.string.cancel),
                new String[]{getResources().getString(R.string.send_message),
                        getResources().getString(R.string.our_phone)},
                null, this, AlertView.Style.ActionSheet, this)
                .setCancelable(true)
                .setOnDismissListener(this);
        av_share = new AlertView(mShareDrawables,
                null,
                "请选择分享平台",
                getResources().getString(R.string.cancel),
                new String[]{"微信好友", "微信朋友圈", "QQ", "新浪微博"},
                null, this, AlertView.Style.ActionSheet, this)
                .setCancelable(true)
                .setOnDismissListener(this);
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
                if (b_isCollection) {
                    unCollection();
                } else {
                    collection();
                }
                break;
            //contact us 联系我们
            case R.id.ll_commodity_contact_us:
            case R.id.bt_commodity_info_contact_us:
                //alert customs dialog 弹出自己定对话框
                av_contact.show();
                break;
            //comany name 公司名字
            case R.id.rl_commodity_info_company_name:
                //start company info activity 启动商户详情页面
                intent = new Intent(this, CompanyInfoActivity.class);
                intent.putExtra("shopId", String.valueOf(mItemDetailBean.getValue().getShopId()));
                startActivity(intent);
                break;
            //buy right now 立即购买
            case R.id.tv_commodity_info_buy:
                //start confirm order activity 启动确认订单页面
                intent = new Intent(this, ConfirmOrderActivity.class);
                startActivity(intent);
                break;
            //share
            case R.id.ll_commodity_share:
                //alert customs dialog 弹出自己定对话框
                av_share.show();
                break;
        }
    }

    @Override
    public void onDismiss(Object o) {

    }

    @Override
    public void onItemClick(Object o, int position) {
        Logs.i(Cons.ACTIVITY_COMMODITYINFO, "点击了位置为" + position + "的按钮");
        if (o == av_contact) {
            switch (position) {
                //send message 发送消息
                case 0:
                    String username = String.valueOf(mItemDetailBean.getValue().getId());
                    if (TextUtils.isEmpty(username)) {
                        ToastUtil.showToast(mContext, getString(R.string.data_error));
                    } else {
                        // enter chat activity
                        Intent intent = new Intent(mContext, ChatActivity.class);
                        intent.putExtra(Constant.EXTRA_USER_ID, username);
                        startActivity(intent);
                    }
                    break;
                //call cellphone 拨打电话
                case 1:
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.CALL");
                    intent.setData(Uri.parse("tel:" + getResources().getString(R.string.our_phone)));
                    startActivity(intent);
                    break;
            }
        } else if (o == av_share) {
            switch (position) {
                //share to wechat friends
                case 0:
                    // 初始化一个WXTextObject对象
                    WXWebpageObject wxWebpageObject = new WXWebpageObject();
                    wxWebpageObject.webpageUrl = "http://www.hichinavc.com/";
                    // 用WXWebpageObject对象初始化一个WXWebpageObject对象,填写标题、描述
                    WXMediaMessage msg = new WXMediaMessage(wxWebpageObject);
                    msg.title = "测试标题";
                    msg.description = "测试描述";
                    Bitmap thumb =
                            BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    thumb.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    msg.thumbData = byteArray;

                    // 构造一个Req
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis()); // transaction字段用于唯一标识一个请求
                    req.message = msg;
                    req.scene = SendMessageToWX.Req.WXSceneSession;
                    // 调用api接口发送数据到微信
                    MyApplication.getInstance().wechat.sendReq(req);
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


    /**
     * item detail
     */
    public void getItemDetail() {

        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(getIntent().getStringExtra("itemId"))) {
            ToastUtil.showToast(this, getString(R.string.data_error));
            return;
        } else {
            itemId = getIntent().getStringExtra("itemId");
        }

        // NoHttp zuzhi item detail
        String url = Cons.DOMAIN + Cons.ITEM_DETAIL;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("itemId", itemId);
            if (MyApplication.user.getId() != 0) {
                postJson.put("userId",
                        String.valueOf(MyApplication.user.getId()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("足智物品详情", "---------url---------");
        Logs.i("足智物品详情", url);

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
                    Logs.i("足智物品详情", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {
                        mItemDetailBean = MyApplication.gson.fromJson(jsonObject.toString(),
                                ItemDetailBean.class);
                        initViewData();
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
                Logs.i("足智物品详情", "----------Error-------");
                Logs.i("足智物品详情", exception.toString());
                DialogUtils.dismissProgressDialog();
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    /**
     * init views data from bean
     */
    private void initViewData() {
        //init test data
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
        CertificateAdapter adp_certificate = new CertificateAdapter(this,
                mItemDetailBean.getValue().getShopCertificate());
        rv_certificate.setAdapter(adp_certificate);
        rv_certificate.setLayoutManager(new TopicLayoutManager(this, OrientationHelper.VERTICAL, false,
                mItemDetailBean.getValue().getShopCertificate().size()));
        adp_certificate.setOnItemClickLitener(new CertificateAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, final int position) {
                DialogUtils.showProgressDialog(mContext, getString(R.string.loading));
                //if not start a new thread, will block the UI thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ImageLoader.getInstance().loadImage(
                                Cons.IMG_HOST +
                                        mItemDetailBean.getValue().
                                                getShopCertificate().get(position).getUrl(),
                                new ImageLoadingListener() {
                                    @Override
                                    public void onLoadingStarted(String imageUri, View view) {

                                    }

                                    @Override
                                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                                        DialogUtils.dismissProgressDialog();
                                        ToastUtil.showToast(mContext, getString(R.string.data_error));
                                    }

                                    @Override
                                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                        //use the system image explorer
                                        DialogUtils.dismissProgressDialog();
                                        String cachePath =
                                                ImageLoader
                                                        .getInstance()
                                                        .getDiskCache()
                                                        .get(imageUri).getPath();
                                        //user the imageloader path to save bitmap
                                        File file = new File(
                                                StorageUtils.getOwnCacheDirectory(mContext, Cons.CACHE_IMAGE_DIR).getPath(),
                                                "certificate.png"
                                        );
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        Uri mUri = Uri.parse("file://" + BitmapUtils.save(file, loadedImage));
                                        intent.setDataAndType(mUri, "image/*");
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onLoadingCancelled(String imageUri, View view) {
                                        DialogUtils.dismissProgressDialog();
                                        ToastUtil.showToast(mContext, getString(R.string.data_error));
                                    }
                                });
                    }
                }).start();


            }
        });

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

        //set viewpager
        //add viewpager views
        wv_service_details.loadUrl
                (Cons.IMG_HOST + mItemDetailBean.getValue().getItemViewUrl());
        vp_commdity_info.addView(wv_service_details);
        list_views.add(wv_service_details);
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


        //set text
        tv_title.setText(mItemDetailBean.getValue().getName());
        if (mItemDetailBean.getValue().isItemPromote()) {
            tv_price1.setText("￥" + String.valueOf(mItemDetailBean.getValue().getItemPromotePrice()));
        } else {
            tv_attribute.setVisibility(View.GONE);
            tv_price1.setText("￥" + String.valueOf(mItemDetailBean.getValue().getItemShopPrice()));
            tv_price2.setVisibility(View.GONE);
        }
        tv_price2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_price2.setText("￥" + String.valueOf(mItemDetailBean.getValue().getItemMarketPrice()));
        tv_saled.setText("已售出" + mItemDetailBean.getValue().getItemSaleNum() + "单");
        tv_info1.setText("由" + mItemDetailBean.getValue().getExpertName() + "提供服务");
        tv_info2.setText(mItemDetailBean.getValue().getExpertWorkingHours() + "年经验");
        tv_company_name.setText(mItemDetailBean.getValue().getShopName());
        tv_company_des.setText(mItemDetailBean.getValue().getShopRemark());
        switch (mItemDetailBean.getValue().getCollectionFlag()) {
            case 0:
                b_isCollection = false;
                cb_collection.setChecked(b_isCollection);
                break;
            case 1:
                b_isCollection = true;
                cb_collection.setChecked(b_isCollection);
                break;
        }


        //set listeners
        bt_back.setOnClickListener(this);
        ll_collection.setOnClickListener(this);
        cb_collection.setOnClickListener(this);
        ll_contact_us.setOnClickListener(this);
        bt_contact_us.setOnClickListener(this);
        rl_company_name.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        ll_commodity_share.setOnClickListener(this);
        DialogUtils.dismissProgressDialog();
    }


    /**
     * collection
     */
    private void collection() {

        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(String.valueOf(MyApplication.user.getId()))) {
            ToastUtil.showToast(this, getString(R.string.unlogin));
            return;
        }
        // NoHttp zuzhi collection
        String url = Cons.DOMAIN + Cons.COLLECTION;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("userId", String.valueOf(MyApplication.user.getId()));
            postJson.put("itemId", itemId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("足智收藏", "---------url---------");
        Logs.i("足智收藏", url);

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
                    Logs.i("足智收藏", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {
                        b_isCollection = true;
                        cb_collection.setChecked(b_isCollection);

                    } else {
                        ToastUtil.showToast(mContext, jsonObject.getString("message"));
                        DialogUtils.dismissProgressDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(mContext, getResources().getString(R.string.request_fail));
                Logs.i("足智收藏", "----------Error-------");
                Logs.i("足智收藏", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    /**
     * uncollection
     */
    private void unCollection() {

        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(String.valueOf(MyApplication.user.getId()))) {
            ToastUtil.showToast(this, getString(R.string.unlogin));
            return;
        }
        // NoHttp zuzhi collection
        String url = Cons.DOMAIN + Cons.UNCOLLECTION;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("userId", String.valueOf(MyApplication.user.getId()));
            postJson.put("itemId", itemId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("足智取消收藏", "---------url---------");
        Logs.i("足智取消收藏", url);

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
                    Logs.i("足智取消收藏", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {
                        b_isCollection = false;
                        cb_collection.setChecked(b_isCollection);

                    } else {
                        cb_collection.setChecked(true);
                        ToastUtil.showToast(mContext, jsonObject.getString("message"));
                        DialogUtils.dismissProgressDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(mContext, getResources().getString(R.string.request_fail));
                Logs.i("足智取消收藏", "----------Error-------");
                Logs.i("足智取消收藏", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }
}
