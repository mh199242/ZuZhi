package com.zuzhi.tianyou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.easeui.utils.EaseCommonUtils;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;
import com.zuzhi.tianyou.MainActivity;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.bean.LoginBean;
import com.zuzhi.tianyou.bean.WXLoginBean;
import com.zuzhi.tianyou.im.DemoHelper;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * login activity 登录页
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

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
     * entered user name 用户输入的用户名
     */
    private String currentUsername;

    /**
     * entered password 用户输入的密码
     */
    private String currentPassword;

    Context mContext;

    /**
     * start this activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        mContext = this;
        et_user_name = (EditText) findViewById(R.id.et_login_user_name);
        et_password = (EditText) findViewById(R.id.et_login_password);
        tv_forget_password = (TextView) findViewById(R.id.tv_login_forget_password);
        tv_regist_quickly = (TextView) findViewById(R.id.tv_login_regist_quickly);

        tv_forget_password.setOnClickListener(this);
        tv_regist_quickly.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initTitleBar() {
        ll_title_bar_left = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        tv_title_bar_text = (TextView) findViewById(R.id.tv_title_bar_title);
        bt_title_bar_left = (Button) findViewById(R.id.bt_title_bar_left);
    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        TitileBarSteep(getWindow().getDecorView());

        ll_title_bar_left.setVisibility(View.VISIBLE);
        tv_title_bar_text.setVisibility(View.VISIBLE);
        bt_title_bar_left.setVisibility(View.VISIBLE);

        tv_title_bar_text.setText(R.string.login);

        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    /**
     * wechat login 微信登陆
     */
    public void wechatLogin(View view) {
        DialogUtils.showProgressDialog(this, getString(R.string.loading));
        // send oauth request
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "login";
        MyApplication.getInstance().wechat.sendReq(req);
    }

    /**
     * ease chat login
     */
    public void HXLogin() {
        //easemob login 登陆环信
        EMChatManager.getInstance().login("13501140314",
                "123456", new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        // 登陆成功，保存用户名
                        DemoHelper.getInstance().setCurrentUserName("13501140314");
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

                        if (!LoginActivity.this.isFinishing() && DialogUtils.isShow()) {
                            DialogUtils.dismissProgressDialog();
                        }

                        // 进入主页面
                        finish();

                        Logs.i(TAG, "登陆聊天服务器成功！");
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, final String message) {
                        if (!DialogUtils.isShow()) {
                            return;
                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                DialogUtils.dismissProgressDialog();
                                ToastUtil.showLongToast(mContext, getString(R.string.Login_failed) + message);
                            }
                        });
                        MyApplication.clearUserInfo(mContext);
                        Logs.i(TAG, "登陆聊天服务器失败！");
                    }
                });
    }

    /**
     * login 登陆
     *
     * @param view
     */
    public void zzLogin(View view) {
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
        DialogUtils.showProgressDialog(this, getString(R.string.loading));


        // NoHttp zuzhi login
        String url = Cons.DOMAIN + Cons.LOGIN;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("type", "pt");
            postJson.put("phone", currentUsername);
            postJson.put("password", currentPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("足智登陆", "---------url---------");
        Logs.i("足智登陆", url);

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
                    Logs.i("足智登陆", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {
                        LoginBean bean = MyApplication.gson.fromJson(jsonObject.toString(), LoginBean.class);
                        //update user information
                        MyApplication.user = bean.value;
                        MyApplication.updataUserInfo(mContext);
                        Cons.IMG_HOST = bean.getImgHost() + "/";
                        HXLogin();
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
                Logs.i("足智登陆", "----------Error-------");
                Logs.i("足智登陆", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //forget password 忘记密码>
            case R.id.tv_login_forget_password:
                //start activity
                FindPasswordActivity.actionStart(mContext, 0);
                break;
            //regist quickly 快速注册
            case R.id.tv_login_regist_quickly:
                intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            //back button 返回键
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                finish();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
