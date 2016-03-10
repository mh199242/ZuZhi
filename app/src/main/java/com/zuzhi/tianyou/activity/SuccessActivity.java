package com.zuzhi.tianyou.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.MainActivity;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;

/**
 * find password success activity 找回密码成功页
 */
public class SuccessActivity extends BaseActivity implements View.OnClickListener {

    /**
     * button of back to login返回登录页按钮
     *
     * @return
     */
    private Button bt_back_to_login;

    /**
     * 获取intent 传参title字符串
     */
    private static int mTitleIndex;

    /**
     * intent 意图
     *
     * @return
     */
    private Intent intent;

    /**
     * message text view
     */
    private TextView tv_message;

    /**
     * start this activity with title
     *
     * @param context
     * @param titleIndex
     */
    public static void actionStart(Context context, int titleIndex) {
        Intent intent = new Intent(context, SuccessActivity.class);
        context.startActivity(intent);
        mTitleIndex = titleIndex;
    }

    @Override
    protected int setContent() {
        return R.layout.activity_find_password_success;
    }

    @Override
    protected void initViews() {

        tv_message = (TextView) findViewById(R.id.tv_message);
        bt_back_to_login = (Button) findViewById(R.id.bt_find_password_success_back_to_login);

        switch (mTitleIndex) {
            case FindPasswordActivity.MODIFY_PASSWORD:
                tv_message.setText(R.string.modify_successful);
                bt_back_to_login.setText(R.string.back);
                break;
            case FindPasswordActivity.BIND_CELLPHONE:
                tv_message.setText(R.string.bind_successful);
                bt_back_to_login.setText(R.string.go_index);
                break;
        }
    }

    @Override
    protected void initTitleBar() {
        bt_title_bar_left = (Button) findViewById(R.id.bt_title_bar_left);
        ll_title_bar_left = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        tv_title_bar_text = (TextView) findViewById(R.id.tv_title_bar_title);
    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        TitileBarSteep(getWindow().getDecorView());

        ll_title_bar_left.setVisibility(View.VISIBLE);
        tv_title_bar_text.setVisibility(View.VISIBLE);
        bt_title_bar_left.setVisibility(View.VISIBLE);

        switch (mTitleIndex) {
            case FindPasswordActivity.MODIFY_PASSWORD:
                tv_title_bar_text.setText(R.string.modify_password);
                break;
            case FindPasswordActivity.BIND_CELLPHONE:
                tv_title_bar_text.setText(R.string.bind_cellphone);
                break;
            default:
                tv_title_bar_text.setText(R.string.find_password);
                break;
        }


        bt_title_bar_left.setOnClickListener(this);
        ll_title_bar_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                finish();
                break;
        }
    }

    /**
     * main button logic
     *
     * @param view
     */
    public void mainButton(View view) {
        switch (mTitleIndex) {
            case FindPasswordActivity.MODIFY_PASSWORD:
                finish();
                break;
            case FindPasswordActivity.BIND_CELLPHONE:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                intent = new Intent(this, LoginGuideActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}
