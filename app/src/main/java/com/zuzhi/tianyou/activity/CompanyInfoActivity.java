package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.easeui.utils.EaseCommonUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
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
import com.zuzhi.tianyou.bean.ShopBean;
import com.zuzhi.tianyou.entity.AdEntity;
import com.zuzhi.tianyou.entity.ItemListEntity;
import com.zuzhi.tianyou.utils.BitmapUtils;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * company information activity 公司信息页
 */
public class CompanyInfoActivity extends BaseActivity implements View.OnClickListener {

    /**
     * item entity list
     */
    List<ItemListEntity> mItemList;
    /**
     * Shop Bean
     */
    ShopBean mShopBean;

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
    String shopId;

    /**
     * company name
     */
    TextView tv_company_info_company_name;

    /**
     * company des
     */
    TextView tv_company_info_company_des;

    /**
     * order number
     */
    TextView tv_company_info_order_num;

    /**
     * feed back rate
     */
    TextView tv_company_info_feed_back_rate;

    /**
     * com score
     */
    TextView tv_company_info_com_score;

    /**
     * company logo
     */
    CircleImageView civ_company_info_icon;

    /**
     * complete quality text view
     */
    TextView tv_company_details_complete_quality_rating;

    /**
     * complete quality progress bar
     */
    ProgressBar pb_company_details_complete_quality_rating;

    /**
     * work speed text view
     */
    TextView tv_company_details_work_speed_rating;

    /**
     * work speed progress bar
     */
    ProgressBar pb_company_details_work_speed_rating;


    /**
     * service attitude text view
     */
    TextView tv_company_details_service_attitude_rating;

    /**
     * service attitude progress bar
     */
    ProgressBar pb_company_details_service_attitude_rating;


    @Override

    protected int setContent() {
        return R.layout.activity_company_info;
    }

    @Override
    protected void initViews() {
        mContext = this;
        //init hot service test data
        getCompanyInfo();

        //init certificate test data
        ArrayList<HashMap<String, Object>> data_certificate = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("certificate", "证书" + i);
            data_certificate.add(map);
        }
        //init views
        tv_company_info_feed_back_rate = (TextView) findViewById(R.id.tv_company_info_feed_back_rate);
        tv_company_info_order_num = (TextView) findViewById(R.id.tv_company_info_order_num);
        tv_company_info_company_name = (TextView) findViewById(R.id.tv_company_info_company_name);
        vp_company_info = (ViewPager) findViewById(R.id.vp_company_info);
        tl_company_info = (TabLayout) findViewById(R.id.tl_company_info);
        tv_company_info_company_des = (TextView) findViewById(R.id.tv_company_info_company_des);
        sv_details = (ScrollView) LayoutInflater.from(this).inflate(R.layout.item_viewpager_company_details, null);
        rv_certificate = (RecyclerView) sv_details.findViewById(R.id.rv_company_details_certificate);
        tv_company_info_com_score = (TextView) findViewById(R.id.tv_company_info_com_score);
        civ_company_info_icon = (CircleImageView) findViewById(R.id.civ_company_info_icon);
        tv_company_details_complete_quality_rating = (TextView) sv_details.findViewById(R.id.tv_company_details_complete_quality_rating);
        pb_company_details_complete_quality_rating = (ProgressBar) sv_details.findViewById(R.id.pb_company_details_complete_quality_rating);
        tv_company_details_work_speed_rating = (TextView) sv_details.findViewById(R.id.tv_company_details_work_speed_rating);
        pb_company_details_work_speed_rating = (ProgressBar) sv_details.findViewById(R.id.pb_company_details_work_speed_rating);
        tv_company_details_service_attitude_rating = (TextView) sv_details.findViewById(R.id.tv_company_details_service_attitude_rating);
        pb_company_details_service_attitude_rating = (ProgressBar) sv_details.findViewById(R.id.pb_company_details_service_attitude_rating);


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
        //get company id
        if (TextUtils.isEmpty(getIntent().getStringExtra("shopId"))) {
            ToastUtil.showToast(this, getString(R.string.data_error));
            return;
        } else {
            shopId = getIntent().getStringExtra("shopId");
        }
        DialogUtils.showProgressDialog(this, getString(R.string.loading));

        // NoHttp zuzhi shop details
        String url = Cons.DOMAIN + Cons.SHOP_DETAILS;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("shopId", shopId);
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
                        mShopBean = MyApplication.gson.fromJson(jsonObject.toString(), ShopBean.class);
                        Cons.IMG_HOST = mShopBean.getImgHost();
                        onInitData();
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
                Logs.i("足智店铺", "----------Error-------");
                Logs.i("足智店铺", exception.toString());
            }

            @Override
            public void onFinish(int what) {
                DialogUtils.dismissProgressDialog();
            }
        });

    }


    /**
     * init server data
     */
    private void onInitData() {

        rv_company_service = new RecyclerView(this);
        HotServiceAdapter adp_hotService = new HotServiceAdapter(this, mShopBean.getValue().getItemList());
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
        MastersAdapter adp_masters = new MastersAdapter(this, mShopBean.getValue().getExpertList());
        rv_masters.setAdapter(adp_masters);
        rv_masters.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rv_masters.setLayoutManager(new LinearLayoutManager(this));

        CertificateAdapter adp_certificate = new CertificateAdapter(this, mShopBean.getValue().getShopCertificate());
        rv_certificate.setAdapter(adp_certificate);
        rv_certificate.setLayoutManager(new TopicLayoutManager(this, OrientationHelper.VERTICAL, false, mShopBean.getValue().getShopCertificate().size()));
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
                                        mShopBean.getValue().
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

        //set views data
        tv_company_info_company_name.setText(mShopBean.getValue().getName());
        tv_company_info_company_des.setText(mShopBean.getValue().getRemark());
        tv_company_info_order_num.setText(mShopBean.getValue().getOrderNum() + "单");
        tv_company_info_feed_back_rate.setText(mShopBean.getValue().getFeedbackRate());
        tv_company_info_com_score.setText(mShopBean.getValue().getComScore() + "分");
        ImageLoader.getInstance().displayImage(
                Cons.IMG_HOST + mShopBean.getValue().getShopLogo(),
                civ_company_info_icon,
                MyApplication.dis_ImgOptions);

        tv_company_details_complete_quality_rating.setText(
                mShopBean.getValue().getWczl() + "分");
        float rating1 = mShopBean.getValue().getWczl();
        pb_company_details_complete_quality_rating.setProgress(
                (int) (rating1 / 5.0f * 100));

        tv_company_details_work_speed_rating.setText(
                mShopBean.getValue().getGzsd() + "分");
        float rating2 = mShopBean.getValue().getGzsd();
        pb_company_details_work_speed_rating.setProgress(
                (int) (rating2 / 5.0f * 100));

        tv_company_details_service_attitude_rating.setText(
                mShopBean.getValue().getFwtd() + "分");
        float rating3 = mShopBean.getValue().getFwtd();
        pb_company_details_service_attitude_rating.setProgress(
                (int) (rating3 / 5.0f * 100));


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
}
