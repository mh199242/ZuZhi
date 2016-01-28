package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.MainActivity;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    /**
     * edit text of username 用户名文字编辑框
     */
    private EditText et_login_user_name;

    /**
     * edit text of password 密码文字编辑框
     */
    private EditText et_login_password;

    /**
     * text of forget password 忘记密码文本
     */
    private TextView tv_forget_password;

    /**
     * text of regist quickly 快速注册文本
     */
    private TextView tv_regist_quickly;
    /**
     * intent 意图
     */
    private Intent intent;

    /**
     * login button 登录钮
     * @return
     */
    private Button bt_login;
    @Override
    protected int setContent() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        //add this to exit list
//        MyApplication.getInstance().addActivity(this);

        et_login_user_name = (EditText) findViewById(R.id.et_login_user_name);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        tv_forget_password = (TextView) findViewById(R.id.tv_login_forget_password);
        tv_regist_quickly = (TextView) findViewById(R.id.tv_login_regist_quickly);
        bt_login = (Button) findViewById(R.id.bt_login_login);

        bt_login.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        tv_regist_quickly.setOnClickListener(this);
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

        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //back 返回键
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                exitBy2Click();
                break;
            //forget password 忘记密码>
            case R.id.tv_login_forget_password:
                intent = new Intent(this, FindPasswordActivity.class);
                startActivity(intent);
                break;
            //regist quickly 快速注册
            case R.id.tv_login_regist_quickly:
                intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            //login 登录
            case R.id.bt_login_login:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * exit method of double click
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            //ready for exit 准备退出
            isExit = true;
            ToastUtil.showLongToast(this, getResources().getString(R.string.click_again_to_exit));
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    // cancle exit 取消退出
                    isExit = false;
                }
            }, 2000); // if don't click back in two second, start timertask to cancle mission
            // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
