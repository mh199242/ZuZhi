package com.zuzhi.tianyou.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.easeui.utils.EaseCommonUtils;
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.Response;
import com.zuzhi.tianyou.MainActivity;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.bean.LoginBean;
import com.zuzhi.tianyou.bean.WXLoginBean;
import com.zuzhi.tianyou.entity.LoginEntity;
import com.zuzhi.tianyou.im.DemoHelper;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;
import com.zuzhi.tianyou.wxapi.WXEntryActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * login guide activity 导航登录页
 */
public class LoginGuideActivity extends BaseActivity implements View.OnClickListener {
    /**
     * wechat login request code
     */
    private final int REQUEST_WX_LOGIN = 0;

    private String TAG = "com.zuzhi.tianyou.activity.LoginGuideActivity";
    /**
     * edit text of username 用户名文字编辑框
     */
    private EditText et_user_name;

    /**
     * edit text of password 密码文字编辑框
     */
    private EditText et_password;

    /**
     * text of forget password 忘记密码文本
     */
    private TextView tv_forget_password;

    /**
     * text of regist quickly 快速注册文本
     */
    private TextView tv_regist_quickly;
    /**
     * intent 意图
     */
    private Intent intent;

    /**
     * login_guide button 登录钮
     *
     * @return
     */
    private Button bt_login;

    /**
     * text of go to index 去首页逛逛>>文本
     */
    private TextView tv_go_to_index;

    /**
     * entered user name 用户输入的用户名
     */
    private String currentUsername;

    /**
     * entered password 用户输入的密码
     */
    private String currentPassword;

    /**
     * the boolean of progress show 进度框是否已显示
     */
    private boolean progressShow;

    /**
     * the wechat authorization code 微信授权码
     */
    private String wx_code;
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    private GoogleApiClient client;

    @Override
    protected int setContent() {
        return R.layout.activity_login_guide;
    }

    @Override
    protected void initViews() {
        et_user_name = (EditText) findViewById(R.id.et_login_guide_user_name);
        et_password = (EditText) findViewById(R.id.et_login_guide_password);
        tv_forget_password = (TextView) findViewById(R.id.tv_login_guide_forget_password);
        tv_regist_quickly = (TextView) findViewById(R.id.tv_login_guide_regist_quickly);
        bt_login = (Button) findViewById(R.id.bt_login_guide_login_guide);
        tv_go_to_index = (TextView) findViewById(R.id.tv_login_guide_go_to_index);

        tv_go_to_index.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        tv_regist_quickly.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void setTitleBar() {
        //steep mode沉浸模式
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //alpha status bar 透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //alpha navigation bar 透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//
//        }

    }

    /**
     * wechat login 微信登陆
     */
    public void wechatLogin(View view) {
        Intent intent = new Intent(this, WXEntryActivity.class);
        intent.putExtra("request", REQUEST_WX_LOGIN);
        startActivityForResult(intent, REQUEST_WX_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_WX_LOGIN:
                switch (resultCode) {
                    case RESULT_OK:
                        if (data.getExtras() != null) {
                            WXLoginBean wxLoginBean =
                                    (WXLoginBean) data.getExtras().getSerializable("WXLoginBean");
                            // NoHttp wechat login
                            final Request<JSONObject> request =
                                    NoHttp.createJsonObjectRequest(Cons.DOMAIN + Cons.LOGIN
                                            + "?type=wx&openid=" + wxLoginBean.getOpenid()
                                            + "&headimgurl=" + wxLoginBean.getHeadimgurl()
                                            + "&nickname=" + wxLoginBean.getNickname());
                            MyApplication.getInstance().queue.add(0, request, new OnResponseListener<JSONObject>() {
                                @Override
                                public void onStart(int what) {

                                }

                                @Override
                                public void onSucceed(int what, Response<JSONObject> response) {
                                    JSONObject jsonObject = null;
                                    try {
                                        if (response.get() == null) {
                                            ToastUtil.showToast(LoginGuideActivity.this, getResources().getString(R.string.data_error));
                                            return;
                                        }
                                        jsonObject = response.get();
                                        if (jsonObject.get("isSuccess").equals("true")) {
                                            LoginBean bean = MyApplication.gson.fromJson(jsonObject.toString(), LoginBean.class);
                                            LoginEntity value = bean.value;
                                            ToastUtil.showToast(LoginGuideActivity.this, jsonObject.getString("message"));

                                        } else if (jsonObject.get("isSuccess").equals("false")) {
                                            ToastUtil.showToast(LoginGuideActivity.this, jsonObject.getString("errorMessage"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                @Override
                                public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                                    ToastUtil.showToast(LoginGuideActivity.this, getResources().getString(R.string.request_fail));
                                }

                                @Override
                                public void onFinish(int what) {

                                }
                            });
                        }


                        break;
                }
                break;
        }
    }

    /**
     * login 登陆
     *
     * @param view
     */
    public void loginer(View view) {
        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }
        currentUsername = et_user_name.getText().toString().trim();
        currentPassword = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(currentUsername)) {
            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        progressShow = true;
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                progressShow = false;
            }
        });
        pd.setMessage(getString(R.string.Is_landing));
        pd.show();
        //easemob login 登陆环信
        EMChatManager.getInstance().login(et_user_name.getText().toString(),
                et_password.getText().toString(), new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        if (!progressShow) {
                            return;
                        }


                        if (!progressShow) {
                            return;
                        }
                        // 登陆成功，保存用户名
                        DemoHelper.getInstance().setCurrentUserName(currentUsername);
                        // 注册群组和联系人监听
                        DemoHelper.getInstance().registerGroupAndContactListener();

                        // ** 第一次登录或者之前logout后再登录，加载所有本地群和回话
                        // ** manually load all local groups and
                        EMGroupManager.getInstance().loadAllGroups();
                        EMChatManager.getInstance().loadAllConversations();

                        // 更新当前用户的nickname 此方法的作用是在ios离线推送时能够显示用户nick
                        boolean updatenick = EMChatManager.getInstance().updateCurrentUserNick(
                                MyApplication.currentUserNick.trim());
                        if (!updatenick) {
                            Log.e("LoginActivity", "update current user nick fail");
                        }
                        //异步获取当前用户的昵称和头像(从自己服务器获取，demo使用的一个第三方服务)
                        DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();

                        if (!LoginGuideActivity.this.isFinishing() && pd.isShowing()) {
                            pd.dismiss();
                        }

                        // 进入主页面
                        Intent intent = new Intent(LoginGuideActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                        Cons.B_ISLOGIN = true;
                        Logs.i(TAG, "登陆聊天服务器成功！");
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, final String message) {
                        if (!progressShow) {
                            return;
                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                pd.dismiss();
                                ToastUtil.showLongToast(mContext, getString(R.string.Login_failed) + message);
                            }
                        });

                        Logs.i(TAG, "登陆聊天服务器失败！");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //forget password 忘记密码>
            case R.id.tv_login_guide_forget_password:
                intent = new Intent(this, FindPasswordActivity.class);
                startActivity(intent);
                break;
            //regist quickly 快速注册
            case R.id.tv_login_guide_regist_quickly:
                intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            //go to index 去首页逛逛
            case R.id.tv_login_guide_go_to_index:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
            Process.killProcess(Process.myPid());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
