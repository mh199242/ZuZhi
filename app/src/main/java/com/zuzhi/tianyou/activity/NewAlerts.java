package com.zuzhi.tianyou.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcw.togglebutton.ToggleButton;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;

/**
 * Created by Corydon on 2016/3/4.
 */
public class NewAlerts extends BaseActivity implements View.OnClickListener {

    /** 通知消息显示详情button */
    private ToggleButton mToggleButton;

    @Override
    protected int setContent() {
        return R.layout.activity_new_alerts;
    }

    @Override
    protected void initViews() {
        mToggleButton = (ToggleButton) findViewById(R.id.newAlertsButton);
        mToggleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {

            }
        });
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

        tv_title_bar_text.setText(getResources().getString(R.string.new_alerts));

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
        }
    }
}
