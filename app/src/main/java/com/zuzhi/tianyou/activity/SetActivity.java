package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;

/**
 * set activity 设置页
 */
public class SetActivity extends BaseActivity implements View.OnClickListener {

    RelativeLayout rl_new_message,rl_modify_password,
            rl_login_phone_number,rl_clear_cache,rl_clear_chat_message;

    /** text of cache 缓存文本 */
    TextView tv_set_cache;

    @Override
    protected int setContent() {
        return R.layout.activity_set;
    }

    @Override
    protected void initViews() {
        rl_new_message = (RelativeLayout)findViewById(R.id.rl_new_message);
        rl_modify_password = (RelativeLayout)findViewById(R.id.rl_modify_password);
        rl_login_phone_number = (RelativeLayout)findViewById(R.id.rl_login_phone_number);
        rl_clear_cache = (RelativeLayout)findViewById(R.id.rl_clear_cache);
        rl_clear_chat_message = (RelativeLayout)findViewById(R.id.rl_clear_chat_message);
        tv_set_cache = (TextView)findViewById(R.id.tv_set_cache);

        rl_new_message.setOnClickListener(this);
        rl_modify_password.setOnClickListener(this);
        rl_login_phone_number.setOnClickListener(this);
        rl_clear_cache.setOnClickListener(this);
        rl_clear_chat_message.setOnClickListener(this);

        //get and update cache information 获取缓存并赋值ֵ
        tv_set_cache.setText(MyApplication.getInstance().checkCache());
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

        tv_title_bar_text.setText(R.string.set_title);
        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //back ����
            case R.id.bt_title_bar_left:
            case R.id.ll_title_bar_left:
                finish();
                break;

            case R.id.rl_new_message:
                Toast.makeText(SetActivity.this,R.string.new_message,Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_modify_password:
                Intent inModifyPassword = new Intent(SetActivity.this, FindPasswordActivity.class);
                inModifyPassword.putExtra(FindPasswordActivity.MODIFY_PASSWORD_STR,FindPasswordActivity.MODIFY_PASSWORD);
                startActivity(inModifyPassword);
                Toast.makeText(SetActivity.this,R.string.modify_password,Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_login_phone_number:
                Intent inSetPhoneNum = new Intent(SetActivity.this, SetCellPhoneNumActivity.class);
                startActivity(inSetPhoneNum);
                break;
            case R.id.rl_clear_cache:
                if(MyApplication.getInstance().clearCache()){
                    //clear cache success, upadate cache value 清理缓存成功，刷新缓存数值
                    tv_set_cache.setText(MyApplication.getInstance().checkCache());
                } else{
                    Toast.makeText(SetActivity.this,R.string.clear_cache_fail,Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.rl_clear_chat_message:
                Toast.makeText(SetActivity.this,R.string.clear_chat_message,Toast.LENGTH_SHORT).show();
                break;




        }
    }
}
