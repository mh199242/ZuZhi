package com.zuzhi.tianyou.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.easeui.utils.EaseCommonUtils;
import com.pingplusplus.android.PaymentActivity;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.bean.AliPayChargeBean;
import com.zuzhi.tianyou.bean.WXPayChargeBean;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * opinion request activity 付款页面
 */
public class PayActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_PAYMENT = 1;
    public static final String STR_ORDER_MONEY = "ORDER_MONEY";
    public static final String STR_ORDER_NUM = "ORDER_NUM";

    /**
     * 选择支付方式用int型方便以后再添加支付方式
     */
    private int checkPayIndex;

    public static final int WECHAT_PAY = 0;
    public static final int ALI_PAY = 1;

    /**
     * 显示金额
     */
    TextView tv_pay_orderMoney;
    /**
     * 显示订单号
     */
    TextView tv_pay_orderNum;
    /**
     * 选择微信
     */
    RelativeLayout rl_checkWechat;
    //选择微信支付
    RelativeLayout rl_checkAli;

    ImageView iv_checkWechat, iv_checkAlipay;


    Button bt_pay_right_now;

    /**
     * intent 接收金额
     */
    int orderNMoney;

    /**
     * intent 接收订单号
     */
    String orderNum;


    @Override
    protected int setContent() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initViews() {
        checkPayIndex = WECHAT_PAY;
        tv_pay_orderMoney = (TextView) findViewById(R.id.tv_pay_orderMoney);
        tv_pay_orderNum = (TextView) findViewById(R.id.tv_pay_orderNum);
        rl_checkWechat = (RelativeLayout) findViewById(R.id.rl_checkWechat);
        rl_checkAli = (RelativeLayout) findViewById(R.id.rl_checkAli);
        iv_checkWechat = (ImageView) findViewById(R.id.iv_checkWechat);
        iv_checkAlipay = (ImageView) findViewById(R.id.iv_checkAlipay);
        bt_pay_right_now = (Button) findViewById(R.id.bt_pay_right_now);
        bt_pay_right_now.setOnClickListener(this);
        rl_checkWechat.setOnClickListener(this);
        rl_checkAli.setOnClickListener(this);

        orderNMoney = getIntent().getIntExtra(STR_ORDER_MONEY, 0);
        orderNum = getIntent().getStringExtra(STR_ORDER_NUM);
        tv_pay_orderMoney.setText("￥" + orderNMoney);
        tv_pay_orderNum.setText(orderNum);
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

        tv_title_bar_text.setText(R.string.pay);
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

            //change cellphone number 更换手机号
            case R.id.bt_pay_right_now:
                getPayCharge();
                break;

            //选择微信支付
            case R.id.rl_checkWechat:
                if (checkPayIndex != WECHAT_PAY) {
                    checkPayIndex = WECHAT_PAY;
                    iv_checkWechat.setImageResource(R.drawable.order_choose);
                    iv_checkAlipay.setImageResource(R.drawable.order_n_choose);
                }
                break;

            //选择支付宝支付
            case R.id.rl_checkAli:

                if (checkPayIndex != ALI_PAY) {
                    checkPayIndex = ALI_PAY;
                    iv_checkAlipay.setImageResource(R.drawable.order_choose);
                    iv_checkWechat.setImageResource(R.drawable.order_n_choose);
                }
                break;


        }
    }


    /**
     * get pay charge
     */
    public void getPayCharge() {
        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }

        if (MyApplication.user.getId() == 0) {
            Toast.makeText(this, R.string.unlogin, Toast.LENGTH_SHORT).show();
            return;
        }

        DialogUtils.showProgressDialog(this, getString(R.string.loading));


        // NoHttp get pay charge
        String url = Cons.DOMAIN + Cons.PAY_CHARGE;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("orderId", "1459137256747007");
            postJson.put("userId", String.valueOf(MyApplication.user.getId()));
            postJson.put("amount", "100");
            switch (checkPayIndex) {
                case WECHAT_PAY:
                    postJson.put("channel", "wx");
                    break;
                case ALI_PAY:
                    postJson.put("channel", "alipay");
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("足智paycharge", "---------url---------");
        Logs.i("足智paycharge", url);

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
                    Logs.i("足智paycharge", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {
                        switch (checkPayIndex) {
                            case WECHAT_PAY:
                                WXPayChargeBean wxbean = MyApplication.gson.fromJson(jsonObject.toString(), WXPayChargeBean.class);
                                String wxvalue = MyApplication.gson.toJson(wxbean.getValue());
                                //start charge
                                Intent wxintent = new Intent(PayActivity.this, PaymentActivity.class);
                                wxintent.putExtra(PaymentActivity.EXTRA_CHARGE, wxvalue);
                                startActivityForResult(wxintent, REQUEST_CODE_PAYMENT);
                                break;
                            case ALI_PAY:
                                AliPayChargeBean alibean = MyApplication.gson.fromJson(jsonObject.toString(), AliPayChargeBean.class);
                                String alivalue = MyApplication.gson.toJson(alibean.getValue());
                                //start charge
                                Intent aliintent = new Intent(PayActivity.this, PaymentActivity.class);
                                aliintent.putExtra(PaymentActivity.EXTRA_CHARGE, alivalue);
                                startActivityForResult(aliintent, REQUEST_CODE_PAYMENT);
                                break;
                        }

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
                Logs.i("足智paycharge", "----------Error-------");
                Logs.i("足智paycharge", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }


    /**
     * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
     * 最终支付成功根据异步通知为准
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                switch (result) {
                    case "success":
                        ToastUtil.showToast(mContext, "支付成功");
                        break;
                    case "fail":
                        ToastUtil.showToast(mContext, "支付失败");
                        break;
                    case "cancel":
                        ToastUtil.showToast(mContext, "支付取消");
                        break;
                    case "invalid":
                        ToastUtil.showToast(mContext, "插件未安装");
                        break;

                }
//                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
//                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                DialogUtils.dismissProgressDialog();
//                showMsg(result, errorMsg, extraMsg);
            }
        }
    }


    public void showMsg(String title, String msg1, String msg2) {
        String str = title;
        if (null !=msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null !=msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }
}
