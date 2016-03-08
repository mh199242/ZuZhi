package com.zuzhi.tianyou.wxapi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.Response;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.activity.LoginGuideActivity;
import com.zuzhi.tianyou.bean.WXLoginBean;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    Context mContext;

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
        Intent intent;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                final WXLoginBean wxLoginBean = new WXLoginBean();
                getAccessToken(wxLoginBean, baseResp);
                break;
            //取消登录
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtil.showToast(this, getResources().getString(R.string.cancle_login));
                finish();
                break;
            //登陆被拒
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                ToastUtil.showToast(this, getResources().getString(R.string.refuse_login));
                finish();
                break;
            default:

                break;
        }


    }


    /**
     * get access_token
     */
    private void getAccessToken(final WXLoginBean wxLoginBean, final BaseResp baseResp) {
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
                    getUserInfo(wxLoginBean, baseResp);
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
    private void getUserInfo(final WXLoginBean wxLoginBean, BaseResp baseResp) {
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
                    LoginGuideActivity.actionStart(WXEntryActivity.this, wxLoginBean);
                    finish();
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

}