package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.bean.LoginBean;
import com.zuzhi.tianyou.entity.UserEntity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.StringUtils;
import com.zuzhi.tianyou.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * find password activity 找回密码页
 */
public class FindPasswordActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 获取验证码按钮
     *
     * @return
     */
    private Button bt_identifying_code;

    /**
     * CountDownTimer 计时器
     */
    private TimeCount timeCount;

    /**
     * confirm button 确定键
     *
     * @return
     */
    private Button bt_confirm;

    /**
     * cellphone number edit text
     */
    private EditText et_find_password_enter_cellphone;

    /**
     * identifying code edit text
     */
    private EditText et_find_password_enter_identifying_code;

    /**
     * enter password edit text
     */
    private EditText et_find_password_enter_password;

    /**
     * confirm password edit text
     */
    private EditText et_find_password_confirm_password;


    /**
     * intent 意图
     *
     * @return
     */
    Intent intent;


    /**
     * 获取intent 传参title字符串
     */
    private int titleIndex;

    /**
     * 修改密码
     */
    public static final int MODIFY_PASSWORD = 1;
    /**
     * 绑定手机
     */
    public static final int BIND_CELLPHONE = 2;
    /**
     * 修改密码intent传参名字
     */
    public static final String MODIFY_PASSWORD_STR = "MODIFY_PASSWORD_STR";


    @Override
    protected int setContent() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void initViews() {
        //add this to exit list

        titleIndex = getIntent().getIntExtra("title", 0);


        timeCount = new TimeCount(60000, 1000);

        bt_identifying_code = (Button) findViewById(R.id.bt_find_password_identifying_code);
        bt_confirm = (Button) findViewById(R.id.bt_find_password_confirm);
        et_find_password_enter_cellphone = (EditText) findViewById(R.id.et_find_password_enter_cellphone);
        et_find_password_enter_identifying_code = (EditText) findViewById(R.id.et_find_password_enter_identifying_code);
        et_find_password_enter_password = (EditText) findViewById(R.id.et_find_password_enter_password);
        et_find_password_confirm_password = (EditText) findViewById(R.id.et_find_password_confirm_password);

        bt_identifying_code.setOnClickListener(this);
        bt_confirm.setOnClickListener(this);
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


        bt_title_bar_left.setOnClickListener(this);
        ll_title_bar_left.setOnClickListener(this);
        switch (titleIndex) {
            case MODIFY_PASSWORD:
                tv_title_bar_text.setText(R.string.modify_password);
                break;
            case BIND_CELLPHONE:
                tv_title_bar_text.setText(R.string.bind_cellphone);
                break;
            default:
                tv_title_bar_text.setText(R.string.find_password);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回键
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                intent = new Intent(this, LoginGuideActivity.class);
                startActivity(intent);
                break;
            //获取验证码
            case R.id.bt_find_password_identifying_code:
                indentifyingCode();
                break;
            //确定键
            case R.id.bt_find_password_confirm:
                resetPassword();

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intent = new Intent(this, LoginGuideActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * CountDownTimer 计数器
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            bt_identifying_code.setText(R.string.get_identifying_code);
            bt_identifying_code.setTextColor(getResources().getColor(R.color.color_white));
            bt_identifying_code.setBackgroundResource(R.drawable.button_focus_identifying_code);
            bt_identifying_code.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            bt_identifying_code.setClickable(false);
            bt_identifying_code.setTextColor(getResources().getColor(R.color.color_text_identifying_code_disable));
            bt_identifying_code.setBackgroundResource(R.drawable.button_disable_identifying_code);
            bt_identifying_code.setText(millisUntilFinished / 1000 + getResources().getString(R.string.resend_second));
        }
    }

    /**
     * get identifying code
     */
    private void indentifyingCode() {
        String cellphone = et_find_password_enter_cellphone.getText().toString();
        //check cellphone number
        if (!StringUtils.isMobileNO(cellphone)) {
            ToastUtil.showToast(this, getResources().getString(R.string.cellphone_error));
            return;
        }
        timeCount.start();
        // NoHttp get indentifying code
        String url = Cons.DOMAIN + Cons.IDENTIFYING_CODE;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("phone", cellphone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("获取验证码", "---------url---------");
        Logs.i("获取验证码", url);

        MyApplication.getInstance().queue.add(0, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                JSONObject jsonObject = null;
                try {
                    if (response.get() == null) {
                        ToastUtil.showToast(FindPasswordActivity.this, getResources().getString(R.string.data_error));
                        return;
                    }
                    jsonObject = response.get();
                    Logs.i("获取验证码", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {
                        ToastUtil.showToast(FindPasswordActivity.this, getResources().getString(R.string.send_success));
                    } else {
                        ToastUtil.showToast(FindPasswordActivity.this, getResources().getString(R.string.send_fail));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(FindPasswordActivity.this, getResources().getString(R.string.request_fail));
                Logs.i("获取验证码", "----------Error-------");
                Logs.i("获取验证码", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    /**
     * reset password
     */
    private void resetPassword() {
        String newPass = et_find_password_enter_password.getText().toString();
        String confirmPass = et_find_password_confirm_password.getText().toString();
        String identifyingCode = et_find_password_enter_identifying_code.getText().toString();
        String cellphone = et_find_password_enter_cellphone.getText().toString();
        //check password
        if (!StringUtils.checkNumLength(newPass, 6, 16)) {
            ToastUtil.showLongToast(this, "密码位数应在6-16位之间");
            return;
        } else if (StringUtils.isChinese(newPass)) {
            ToastUtil.showLongToast(this, "密码中不应包含中文汉字和符号");
            return;
        } else if (!newPass.equals(confirmPass)) {
            ToastUtil.showLongToast(this, "两次输入的密码不匹配");
            return;
        }
        timeCount.start();
        // NoHttp find password
        String url = Cons.DOMAIN + Cons.FIND_PASSWORD;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("password", newPass);
            postJson.put("phone", cellphone);
            postJson.put("yzm", identifyingCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("找回密码", "---------url---------");
        Logs.i("找回密码", url);

        MyApplication.getInstance().queue.add(0, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                JSONObject jsonObject = null;
                try {
                    if (response.get() == null) {
                        ToastUtil.showToast(FindPasswordActivity.this, getResources().getString(R.string.data_error));
                        return;
                    }
                    jsonObject = response.get();
                    Logs.i("找回密码", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {
                        intent = new Intent(FindPasswordActivity.this, FindPasswordSuccessActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.showToast(FindPasswordActivity.this, jsonObject.getString("errorMessage"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(FindPasswordActivity.this, getResources().getString(R.string.request_fail));
                Logs.i("获取验证码", "----------Error-------");
                Logs.i("获取验证码", exception.toString());
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}

