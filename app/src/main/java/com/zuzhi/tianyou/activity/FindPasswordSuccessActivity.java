package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;

public class FindPasswordSuccessActivity extends BaseActivity implements View.OnClickListener{

    /**
     * button of back to login返回登录页按钮
     * @return
     */
    private Button bt_back_to_login;

    /**
     * intent 意图
     * @return
     */
    private Intent intent;
    @Override
    protected int setContent() {
        return R.layout.activity_find_password_success;
    }

    @Override
    protected void initViews() {
        bt_back_to_login = (Button) findViewById(R.id.bt_find_password_success_back_to_login);

        bt_back_to_login.setOnClickListener(this);
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
        showSystemUI(getWindow().getDecorView());

        ll_title_bar_left.setVisibility(View.VISIBLE);
        tv_title_bar_text.setVisibility(View.VISIBLE);
        bt_title_bar_left.setVisibility(View.VISIBLE);

        tv_title_bar_text.setText(R.string.find_password);

        bt_title_bar_left.setOnClickListener(this);
        ll_title_bar_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                finish();
                break;
            case R.id.bt_find_password_success_back_to_login:
                intent = new Intent(this, LoginGuideActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
