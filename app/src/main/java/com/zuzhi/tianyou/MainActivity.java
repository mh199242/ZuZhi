package com.zuzhi.tianyou;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.easemob.easeui.utils.EaseCommonUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;
import com.zuzhi.tianyou.activity.MapActivity;
import com.zuzhi.tianyou.activity.SearchHistoryActivity;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.base.BaseFragment;
import com.zuzhi.tianyou.bean.IndexBean;
import com.zuzhi.tianyou.bean.LoginBean;
import com.zuzhi.tianyou.fragment.ClassFragment;
import com.zuzhi.tianyou.fragment.IndexFragment;
import com.zuzhi.tianyou.fragment.MyFragment;
import com.zuzhi.tianyou.utils.AMapUtils;
import com.zuzhi.tianyou.utils.ActivityCollector;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, AMapLocationListener {

    /**
     * fragment manager 碎片管理器
     */
    private FragmentManager fm;

    /**
     * class fragment 类目碎片
     */
    private ClassFragment classFragment;

    /**
     * index fragment 首页碎片
     */
    private IndexFragment indexFragment;

    /**
     * my fragment 我的碎片
     */
    private MyFragment myFragment;

    /**
     * fragment list 碎片列表
     */
    private List<BaseFragment> fragmentList = new ArrayList<BaseFragment>();

    /**
     * radio button 单选按钮
     */
    private int[] indexRadioIds = {R.id.rb_main_index,
            R.id.rb_main_service, R.id.rb_main_tender, R.id.rb_main_my};


    /**
     * 单选按钮组
     */
    private RadioGroup rg_main;

    /**
     * intent 意图
     */
    private Intent mIntent;

    //amap unit 高德地图组件相关
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    Handler mHandler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            switch (msg.what) {
                //start location 开始定位
                case AMapUtils.MSG_LOCATION_START:
                    tv_title_bar_city.setText("...");
                    break;
                //location complete 定位完成
                case AMapUtils.MSG_LOCATION_FINISH:
                    AMapLocation loc = (AMapLocation) msg.obj;
//                    String result = AMapUtils.getLocationStr(loc);
                    String result = AMapUtils.getSimpleAddressStr(loc);
                    tv_title_bar_city.setText(result);
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected int setContent() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

        ll_title_bar = (LinearLayout) findViewById(R.id.ll_main_titlebar);

        fm = getSupportFragmentManager();
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        rg_main.setOnCheckedChangeListener(this);

    }

    @Override
    protected void initTitleBar() {
        tv_title_bar_city = (TextView) findViewById(R.id.tv_title_bar_city);
        rl_title_bar_search = (RelativeLayout) findViewById(R.id.rl_title_bar_search);
        bt_title_bar_search = (Button) findViewById(R.id.bt_title_bar_search);
        ll_title_bar_right = (LinearLayout) findViewById(R.id.ll_title_bar_right);
    }

    @Override
    protected void setTitleBar() {
        rg_main.check(indexRadioIds[0]);
        //open the steep mode 沉浸模式
//        TitileBarSteep(getWindow().getDecorView());

        tv_title_bar_city.setVisibility(View.VISIBLE);
        rl_title_bar_search.setVisibility(View.VISIBLE);


        tv_title_bar_city.setOnClickListener(this);

        locationClient = new AMapLocationClient(getApplicationContext());
        locationOption = new AMapLocationClientOption();

        // set high precision location mode设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // set location listener 设置定位监听
        locationClient.setLocationListener(this);
        // keep location times 持续定位或单次定位
        locationOption.setOnceLocation(true);
        // set location params 设置定位参数
        locationClient.setLocationOption(locationOption);
        // start location 启动定位
        locationClient.startLocation();

        mHandler.sendEmptyMessage(AMapUtils.MSG_LOCATION_START);

        //set listeners
        bt_title_bar_search.setOnClickListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        hideAllFragment();
        switch (checkedId) {
            case R.id.rb_main_service://service 服务

                tv_title_bar_city.setVisibility(View.GONE);
                bt_title_bar_search.setVisibility(View.GONE);
                ll_title_bar_right.setVisibility(View.VISIBLE);

                if (classFragment == null) {
                    classFragment = new ClassFragment(ll_title_bar);
                    fragmentList.add(classFragment);
                    fm.beginTransaction().add(R.id.fm_main_container, classFragment)
                            .commit();
                } else {
                    fm.beginTransaction().show(classFragment).commit();
                }
                break;
            case R.id.rb_main_index://index 首页

                tv_title_bar_city.setVisibility(View.VISIBLE);
                rl_title_bar_search.setVisibility(View.VISIBLE);
                ll_title_bar_right.setVisibility(View.GONE);
                bt_title_bar_search.setVisibility(View.VISIBLE);

                if (indexFragment == null) {
                    indexFragment = new IndexFragment(ll_title_bar);
                    fragmentList.add(indexFragment);
                    fm.beginTransaction().add(R.id.fm_main_container, indexFragment)
                            .commit();
                } else {
                    fm.beginTransaction().show(indexFragment).commit();
                }
                break;

            case R.id.rb_main_my://my 我的
                if (myFragment == null) {
                    myFragment = new MyFragment(ll_title_bar);
                    fragmentList.add(myFragment);
                    fm.beginTransaction().add(R.id.fm_main_container, myFragment)
                            .commit();
                } else {
                    fm.beginTransaction().show(myFragment).commit();

                }
                break;

            default:
                break;
        }
    }


    /**
     * hide all fragment 隐藏所有Fragment布局
     */
    private void hideAllFragment() {

        for (int i = 0; i < fragmentList.size(); i++) {
            fm.beginTransaction().hide(fragmentList.get(i)).commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //back  返回
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                exitBy2Click();
                break;
            //city 城市
            case R.id.tv_title_bar_city:
                mIntent = new Intent(this, MapActivity.class);
                startActivity(mIntent);
                break;
            //search 搜索
            case R.id.bt_title_bar_search:
                //display SearchHistoryActivity 启动搜索历史页面
                mIntent = new Intent(this, SearchHistoryActivity.class);
                startActivity(mIntent);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        exitBy2Click();
        return true;
    }

    // location listener 定位监听
    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = mHandler.obtainMessage();
            msg.obj = loc;
            msg.what = AMapUtils.MSG_LOCATION_FINISH;
            mHandler.sendMessage(msg);
        }
    }

    /**
     * exit method of double click
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            //ready for exit 准备退出
            isExit = true;
            ToastUtil.showLongToast(this, getResources().getString(R.string.click_again_to_exit));
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    // cancle exit 取消退出
                    isExit = false;
                }
            }, 2000); // if don't click back in two second, start timertask to cancle mission
            // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            //exit
            //高德地图
            if (null != locationClient) {
                /**
                 * 如果AMapLocationClient是在当前Activity实例化的，
                 * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
                 */
                locationClient.onDestroy();
                locationClient = null;
                locationOption = null;
            }
            MyApplication.getInstance().queue.cancelAll();// 退出APP时停止所有请求
            MyApplication.getInstance().queue.stop();// 退出APP时停止队列
            ActivityCollector.finishAll();
            android.os.Process.killProcess(Process.myPid());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //高德地图
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }



}
