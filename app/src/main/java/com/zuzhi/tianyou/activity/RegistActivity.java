package com.zuzhi.tianyou.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.exceptions.EaseMobException;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.im.DemoHelper;
import com.zuzhi.tianyou.im.ui.RegisterActivity;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.StringUtils;
import com.zuzhi.tianyou.utils.ToastUtil;

/**
 * regist activity 注册页
 */
public class RegistActivity extends BaseActivity implements View.OnClickListener, com.bigkoo.alertview.OnItemClickListener, com.bigkoo.alertview.OnDismissListener {

    /**
     * intent 意图
     */
    private Intent intent;

    /**
     * next step button 下一步键
     *
     * @return
     */
    private Button bt_next_step;

    /**
     * get identifying code button 获取验证码键
     *
     * @return
     */
    private Button bt_identifying_code;

    /**
     * CountDownTimer 计时器
     */
    private TimeCount timeCount;

    /**
     * button of show password 明文密码键
     */
    private Button bt_show_password;

    /**
     * edit text of password 密码编辑框
     */
    private EditText et_password;

    /**
     * boolean of show password 是否显示密码
     */
    private boolean b_isShowPassWord = false;

    /**
     * 手机号编辑框
     */
    private EditText et_enter_cellphone;

    @Override
    protected int setContent() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initViews() {
        timeCount = new TimeCount(60000, 1000);

        bt_next_step = (Button) findViewById(R.id.bt_regist_next_step);
        bt_identifying_code = (Button) findViewById(R.id.bt_regist_identifying_code);
        bt_show_password = (Button) findViewById(R.id.bt_regist_show_password);
        et_password = (EditText) findViewById(R.id.et_regist_password);
        et_enter_cellphone = (EditText) findViewById(R.id.et_regist_enter_cellphone);

        bt_show_password.setOnClickListener(this);
        bt_identifying_code.setOnClickListener(this);
    }


    /**
     * regist 注册
     */
    public void register(View view) {
        if (!StringUtils.isMobileNO(et_enter_cellphone.getText().toString())) {
            ToastUtil.showLongToast(this, "请输入正确的手机号！");
        } else if (!StringUtils.isPwd(et_password.getText().toString())) {
            ToastUtil.showLongToast(this, "密码位数应在6-16位之间！");
        } else if (StringUtils.isChinese(et_password.getText().toString())) {
            ToastUtil.showLongToast(this, "密码中不应包含中文汉字和符号！");
        } else {
            DialogUtils.showProgressDialog(mContext, "注册中...");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 调用sdk注册方法
                    try {
                        EMChatManager.getInstance().createAccountOnServer(et_enter_cellphone.getText().toString(),
                                et_password.getText().toString());
                        runOnUiThread(new Runnable() {
                            public void run() {
                                DialogUtils.dismissProgressDialog();
                                // 保存用户名
                                DemoHelper.getInstance().setCurrentUserName(et_enter_cellphone.getText().toString());
                                ToastUtil.showLongToast(mContext, getResources().getString(R.string.Registered_successfully));
                                //start selectprofession activity 启动选择职业页
                                intent = new Intent(mContext, SelectProfessionActivity.class);
                                startActivity(intent);
                            }
                        });
                    } catch (final EaseMobException e) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                DialogUtils.dismissProgressDialog();
                                int errorCode = e.getErrorCode();
                                if (errorCode == EMError.NONETWORK_ERROR) {
                                    ToastUtil.showLongToast(mContext, getResources().getString(R.string.network_anomalies));
                                } else if (errorCode == EMError.USER_ALREADY_EXISTS) {
                                    ToastUtil.showLongToast(mContext, getResources().getString(R.string.User_already_exists));
                                } else if (errorCode == EMError.UNAUTHORIZED) {
                                    ToastUtil.showLongToast(mContext, getResources().getString(R.string.registration_failed_without_permission));
                                } else if (errorCode == EMError.ILLEGAL_USER_NAME) {
                                    ToastUtil.showLongToast(mContext, getResources().getString(R.string.illegal_user_name));
                                } else {
                                    ToastUtil.showLongToast(mContext, getResources().getString(R.string.Registration_failed) + e.getMessage());
                                }
                            }
                        });
                    }
                }
            }).start();

        }
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

        tv_title_bar_text.setText(R.string.regist);

        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intent = new Intent(this, LoginGuideActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //back button 返回键
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                intent = new Intent(this, LoginGuideActivity.class);
                startActivity(intent);
                break;
            //next step button 下一步键
//            case R.id.bt_regist_next_step:
//                new AlertView(null, null, getResources().getString(R.string.alert_cellphone_already_exist),
//                        getResources().getString(R.string.cancel),
//                        new String[]{getResources().getString(R.string.confirm)},
//                        null, this, AlertView.Style.Alert, this)
//                        .setCancelable(true)
//                        .setOnDismissListener(this)
//                        .show();
//                break;
            //get identifying code button 获取验证码
            case R.id.bt_regist_identifying_code:
                timeCount.start();
                break;
            //show password buton 明文密码键
            case R.id.bt_regist_show_password:
                if (!b_isShowPassWord) {
                    et_password.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());
                    b_isShowPassWord = !b_isShowPassWord;
                } else {
                    et_password.setTransformationMethod(PasswordTransformationMethod
                            .getInstance());
                    b_isShowPassWord = !b_isShowPassWord;
                }
                break;

        }
    }


    @Override
    public void onItemClick(Object o, int position) {
        //判断是否是拓展窗口View，而且点击的是非取消按钮
//        if(o == mAlertViewExt && position != AlertView.CANCELPOSITION){
//            String name = etName.getText().toString();
//            if(name.isEmpty()){
//                Toast.makeText(this, "啥都没填呢", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(this, "hello,"+name, Toast.LENGTH_SHORT).show();
//            }
//
//            return;
//        }
        switch (position) {
            //cancle 取消
            case -1:

                break;
            //cnfirm 确定
            case 0:
                Looper.prepare();
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            // 调用sdk注册方法
                            EMChatManager.getInstance().createAccountOnServer(et_enter_cellphone.getText().toString(),
                                    et_password.getText().toString());
                        } catch (final EaseMobException e) {
                            //注册失败
                            int errorCode = e.getErrorCode();
                            if (errorCode == EMError.NONETWORK_ERROR) {
                                Toast.makeText(getApplicationContext(), "网络异常，请检查网络！", Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.USER_ALREADY_EXISTS) {
                                Toast.makeText(getApplicationContext(), "用户已存在！", Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.UNAUTHORIZED) {
                                Toast.makeText(getApplicationContext(), "注册失败，无权限！", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "注册失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                    }
                }).start();
                intent = new Intent(this, SelectProfessionActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDismiss(Object o) {

    }

    /**
     * CountDownTimer 计数器
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        /**
         * onFinish 计时完毕时触发
         */
        @Override
        public void onFinish() {
            bt_identifying_code.setText(R.string.get_identifying_code);
            bt_identifying_code.setTextColor(getResources().getColor(R.color.color_white));
            bt_identifying_code.setBackgroundResource(R.drawable.button_focus_identifying_code);
            bt_identifying_code.setClickable(true);
        }

        /**
         * onTick 计时中
         *
         * @param millisUntilFinished 已耗时
         */
        @Override
        public void onTick(long millisUntilFinished) {
            bt_identifying_code.setClickable(false);
            bt_identifying_code.setTextColor(getResources().getColor(R.color.color_text_identifying_code_disable));
            bt_identifying_code.setBackgroundResource(R.drawable.button_disable_identifying_code);
            bt_identifying_code.setText(millisUntilFinished / 1000 + getResources().getString(R.string.resend_second));
        }
    }
}
