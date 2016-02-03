package com.zuzhi.tianyou.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;

/**
 * opinion request activity 意见反馈页
 * */
public class OpinionActivity extends BaseActivity implements View.OnClickListener {

    EditText et_opinion;
    Button bt_opinion_submit;
    @Override
    protected int setContent() {
        return R.layout.activity_opinion;
    }

    @Override
    protected void initViews() {
        et_opinion = (EditText)findViewById(R.id.et_opinion);
        bt_opinion_submit = (Button)findViewById(R.id.bt_opinion_submit);

        bt_opinion_submit.setOnClickListener(this);
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

        tv_title_bar_text.setText(R.string.my_opinion);
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
            case R.id.bt_opinion_submit:
                Toast.makeText(this,R.string.submit,Toast.LENGTH_LONG).show();
                break;

        }
    }
}
