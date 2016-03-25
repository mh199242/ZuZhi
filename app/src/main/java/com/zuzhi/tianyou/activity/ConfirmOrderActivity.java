package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.bean.LoginBean;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * confirm order activity 确认订单页面
 */
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {
    /**
     * eidit text of leave message to company 给服务商留言编辑框
     */
    private EditText et_message;

    /**
     * eidit text of leave message to company 给服务商留言编辑框
     */
    private TextView tv_commit_order;

    /**
     * 开发票View
     */
    private TextView mMakeInvoice;

    /**
     * order id
     */
    private String mOrderId;


    @Override
    protected int setContent() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initViews() {
        //find views
        et_message = (EditText) findViewById(R.id.et_confirm_order_message);
        tv_commit_order = (TextView) findViewById(R.id.tv_confirm_order_commit_order);
        mMakeInvoice = (TextView) findViewById(R.id.makeInvoice);
        // 解决嵌套在scrollview中无法获得焦点的问题
//        et_message.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View view, MotionEvent event) {
//                // TODO Auto-generated method stub
//                if (view.getId() == R.id.et_confirm_order_message) {
//                    view.getParent().requestDisallowInterceptTouchEvent(true);
//                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
//                        case MotionEvent.ACTION_UP:
//                            view.getParent().requestDisallowInterceptTouchEvent(false);
//                            break;
//                    }
//                }
//                return false;
//            }
//        });
        //set listeners
        tv_commit_order.setOnClickListener(this);
        mMakeInvoice.setOnClickListener(this);


        mOrderId = getIntent().getStringExtra("orderId");

        getOrderDetail();
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

        tv_title_bar_text.setText(getResources().getString(R.string.confirm_order));

        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //back 返回键
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                finish();
                break;
            //commit order 提交订单
            case R.id.tv_confirm_order_commit_order:
                finish();
                break;
            case R.id.makeInvoice:
                startActivity(new Intent(this, MakeInvoiceActivity.class));
                break;
        }

    }

    /**
     * get order detail
     */
    public void getOrderDetail() {
        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }

        DialogUtils.showProgressDialog(this, getString(R.string.loading));


        // NoHttp zuzhi login
        String url = Cons.DOMAIN + Cons.ORDER_DETAIL;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("orderId", mOrderId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("足智订单详情", "---------url---------");
        Logs.i("足智订单详情", url);

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
                    Logs.i("足智订单详情", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {

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
                Logs.i("足智订单详情", "----------Error-------");
                Logs.i("足智订单详情", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }
}
