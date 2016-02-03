package com.zuzhi.tianyou.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;

/**
 * set cellphone number activity 设置手机号码页
 */
public class SetCellPhoneNumActivity extends BaseActivity implements View.OnClickListener {

    TextView tv_get_phoneNum;
    Button bt_modify_phone_num;
    @Override
    protected int setContent() {
        return R.layout.activity_set_phonenum;
    }

    @Override
    protected void initViews() {
        tv_get_phoneNum = (TextView)findViewById(R.id.tv_get_phoneNum);
        bt_modify_phone_num = (Button)findViewById(R.id.bt_modify_cellphone_num);


        //从SharedPreferences里面获取手机号
//        tv_get_phoneNum.setText();

        bt_modify_phone_num.setOnClickListener(this);
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

        tv_title_bar_text.setText(R.string.login_phone_number);
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
            case R.id.bt_modify_cellphone_num:
                Toast.makeText(this,R.string.modify_phone_num,Toast.LENGTH_LONG).show();
                break;

        }
    }
}
