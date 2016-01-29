package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;

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
     *
     * @return
     */
    private Button bt_show_password;

    /**
     * edit text of password 密码编辑框
     *
     * @return
     */
    private EditText et_password;

    /**
     * boolean of show password 是否显示密码
     */
    private boolean b_isShowPassWord = false;

    @Override
    protected int setContent() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initViews() {
        //add this to exit list
        MyApplication.getInstance().addActivity(this);

        timeCount = new TimeCount(60000, 1000);

        bt_next_step = (Button) findViewById(R.id.bt_regist_next_step);
        bt_identifying_code = (Button) findViewById(R.id.bt_regist_identifying_code);
        bt_show_password = (Button) findViewById(R.id.bt_regist_show_password);
        et_password = (EditText) findViewById(R.id.et_regist_password);

        bt_show_password.setOnClickListener(this);
        bt_identifying_code.setOnClickListener(this);
        bt_next_step.setOnClickListener(this);
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
        showSystemUI(getWindow().getDecorView());

        ll_title_bar_left.setVisibility(View.VISIBLE);
        tv_title_bar_text.setVisibility(View.VISIBLE);
        bt_title_bar_left.setVisibility(View.VISIBLE);

        tv_title_bar_text.setText(R.string.regist);

        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
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
            case R.id.bt_regist_next_step:
                new AlertView(null, getResources().getString(R.string.alert_cellphone_already_exist),
                        getResources().getString(R.string.cancel),
                        new String[]{getResources().getString(R.string.confirm)},
                        null, this, AlertView.Style.Alert, this)
                        .setCancelable(true)
                        .setOnDismissListener(this)
                        .show();
                break;
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
