package com.zuzhi.tianyou.wxapi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.activity.LoginGuideActivity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        MyApplication.getInstance().wechat.handleIntent(getIntent(), this);
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
                //get the wechat grant 获得微信权授code
                MyApplication.getInstance().setWECHAT_CODE(((SendAuth.Resp) baseResp).code);
                finish();
                //start loginguide activity 启动登陆导航页
                intent = new Intent(this, LoginGuideActivity.class);
                startActivity(intent);
                break;
            //取消登录
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtil.showToast(this, getResources().getString(R.string.cancle_login));
                break;
            //登陆被拒
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                ToastUtil.showToast(this, getResources().getString(R.string.refuse_login));
                break;
            default:

                break;
        }

    }
}