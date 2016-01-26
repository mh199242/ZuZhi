package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;

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
     * intent 意图
     *
     * @return
     */
    Intent intent;

    @Override
    protected int setContent() {
        return R.layout.activity_find_pass_word;
    }

    @Override
    protected void initViews() {
        timeCount = new TimeCount(60000, 1000);

        bt_identifying_code = (Button) findViewById(R.id.bt_find_password_identifying_code);
        bt_confirm = (Button) findViewById(R.id.bt_find_password_confirm);

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
        showSystemUI(getWindow().getDecorView());

        ll_title_bar_left.setVisibility(View.VISIBLE);
        tv_title_bar_text.setVisibility(View.VISIBLE);


        bt_title_bar_left.setOnClickListener(this);
        ll_title_bar_left.setOnClickListener(this);

        tv_title_bar_text.setText(R.string.find_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回键
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                finish();
                break;
            //获取验证码
            case R.id.bt_find_password_identifying_code:
                timeCount.start();
                break;
            //确定键
            case R.id.bt_find_password_confirm:
                intent = new Intent(this, FindPasswordSuccessActivity.class);
                startActivity(intent);
                finish();
                break;
        }
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
}