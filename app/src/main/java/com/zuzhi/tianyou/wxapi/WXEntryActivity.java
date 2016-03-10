package com.zuzhi.tianyou.wxapi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;


import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;
import com.zuzhi.tianyou.MainActivity;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.activity.FindPasswordActivity;
import com.zuzhi.tianyou.activity.LoginActivity;
import com.zuzhi.tianyou.activity.LoginGuideActivity;
import com.zuzhi.tianyou.bean.LoginBean;
import com.zuzhi.tianyou.bean.WXLoginBean;
import com.zuzhi.tianyou.im.DemoHelper;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private final String TAG = getClass().getSimpleName();

    Context mContext;

    private String state;

    private WXLoginBean wxLoginBean = new WXLoginBean();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_wxentry);

        MyApplication.getInstance().wechat.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        MyApplication.getInstance().wechat.handleIntent(intent, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onReq(BaseReq baseReq) {
        Logs.i("微信", "onReq");
    }

    /**
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        state = ((SendAuth.Resp) baseResp).state;
        Intent intent;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                getAccessToken(baseResp);
                break;
            //取消登录
            case BaseResp.ErrCode.ERR_USER_CANCEL:
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                ToastUtil.showToast(this, getResources().getString(R.string.cancle_login));
                finish();
                break;
            default:

                break;
        }


    }


    /**
     * get access_token
     */
    private void getAccessToken(final BaseResp baseResp) {
        wxLoginBean.setCode(((SendAuth.Resp) baseResp).code);

        //use the code to get access_token
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(
                        "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
                                Cons.WECHAT_APPID +
                                "&secret=" +
                                Cons.WECHAT_SECRET +
                                "&code=" +
                                wxLoginBean.getCode() +
                                "&grant_type=authorization_code");
        MyApplication.getInstance().queue.add(0, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {

                JSONObject jsonObject = null;
                try {
                    if (response.get() == null) {
                        ToastUtil.showToast(WXEntryActivity.this, getResources().getString(R.string.data_error));
                        return;
                    }
                    jsonObject = response.get();
                    wxLoginBean.setAccess_token(jsonObject.get("access_token").toString());
                    wxLoginBean.setOpenid(jsonObject.get("openid").toString());
                    getUserInfo(baseResp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(WXEntryActivity.this, getResources().getString(R.string.request_fail));
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }


    /**
     * get user info
     */
    private void getUserInfo(BaseResp baseResp) {
        // NoHttp wechat get user info
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(
                        "https://api.weixin.qq.com/sns/userinfo?access_token=" +
                                wxLoginBean.getAccess_token() +
                                "&openid=" +
                                wxLoginBean.getOpenid());
        MyApplication.getInstance().queue.add(0, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                JSONObject jsonObject = null;
                try {
                    if (response.get() == null) {
                        ToastUtil.showToast(WXEntryActivity.this, getResources().getString(R.string.data_error));
                        return;
                    }
                    Logs.i("微信", "onSucceed");
                    jsonObject = response.get();
                    wxLoginBean.setNickname(jsonObject.get("nickname").toString());
                    wxLoginBean.setHeadimgurl(jsonObject.get("headimgurl").toString());
                    wechatLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(WXEntryActivity.this, getResources().getString(R.string.request_fail));
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }


    /**
     * wechat login
     */
    private void wechatLogin() {

        // NoHttp wechat login
        String url = Cons.DOMAIN + Cons.LOGIN;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("type", "wx");
            postJson.put("openid", wxLoginBean.getOpenid());
            postJson.put("headimgurl", wxLoginBean.getHeadimgurl());
            postJson.put("nickname", wxLoginBean.getNickname());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("微信登录", "---------WXLoginUrl---------");
        Logs.i("微信登录", url);

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
                    Logs.i("微信登录", jsonObject.toString());
                    //success
                    if (jsonObject.getBoolean("success")) {
                        LoginBean bean = MyApplication.gson.fromJson(jsonObject.toString(), LoginBean.class);
                        //update user information
                        MyApplication.user = bean.value;
                        MyApplication.updataUserInfo(mContext);
                        Cons.IMG_HOST = bean.getImgHost() + "/";
                        //start bind phone activity
                        if (MyApplication.user.getPhone() == null) {
                            ToastUtil.showToast(mContext, getResources().getString(R.string.please_bind_cellphone));
                            //start activity
                            FindPasswordActivity.actionStart(mContext, FindPasswordActivity.BIND_CELLPHONE);
                        } else {
                            HXLogin();
                        }
                        //fail
                    } else if (!jsonObject.getBoolean("success")) {
                        ToastUtil.showToast(mContext, jsonObject.getString("errorMessage"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(mContext, getResources().getString(R.string.request_fail));
                Logs.i("微信登录", "----------Error-------");
                Logs.i("微信登录", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    /**
     * ease chat login
     */
    public void HXLogin() {
        //easemob login 登陆环信
        EMChatManager.getInstance().login("18600364741",
                "111111", new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {

                        // 登陆成功，保存用户名
                        DemoHelper.getInstance().setCurrentUserName("18600364741");
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

                        if (!WXEntryActivity.this.isFinishing() && DialogUtils.isShow()) {
                            DialogUtils.dismissProgressDialog();
                        }
                        // 进入主页面

                        Intent intent = new Intent(WXEntryActivity.this,
                                MainActivity.class);
                        startActivity(intent);
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

                        Logs.i(TAG, "登陆聊天服务器失败！");
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return true;
    }
}