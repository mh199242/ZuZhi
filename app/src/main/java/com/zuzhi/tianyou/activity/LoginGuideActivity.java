package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zuzhi.tianyou.MainActivity;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;
/**
 * login guide activity 导航登录页
 */
public class LoginGuideActivity extends BaseActivity implements View.OnClickListener {
    /**
     * edit text of username 用户名文字编辑框
     */
    private EditText et_user_name;

    /**
     * edit text of password 密码文字编辑框
     */
    private EditText et_password;

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
     * login_guide button 登录钮
     *
     * @return
     */
    private Button bt_login;

    /**
     * text of go to index 去首页逛逛>>文本
     */
    private TextView tv_go_to_index;

    @Override
    protected int setContent() {
        return R.layout.activity_login_guide;
    }

    @Override
    protected void initViews() {
        //add this to exit list
        MyApplication.getInstance().addActivity(this);

        et_user_name = (EditText) findViewById(R.id.et_login_guide_user_name);
        et_password = (EditText) findViewById(R.id.et_login_guide_password);
        tv_forget_password = (TextView) findViewById(R.id.tv_login_guide_forget_password);
        tv_regist_quickly = (TextView) findViewById(R.id.tv_login_guide_regist_quickly);
        bt_login = (Button) findViewById(R.id.bt_login_guide_login_guide);
        tv_go_to_index = (TextView) findViewById(R.id.tv_login_guide_go_to_index);

        tv_go_to_index.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        tv_regist_quickly.setOnClickListener(this);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void setTitleBar() {
        //steep mode沉浸模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //alpha status bar 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //alpha navigation bar 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //forget password 忘记密码>
            case R.id.tv_login_guide_forget_password:
                intent = new Intent(this, FindPasswordActivity.class);
                startActivity(intent);
                break;
            //regist quickly 快速注册
            case R.id.tv_login_guide_regist_quickly:
                intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            //login 登录
            case R.id.bt_login_guide_login_guide:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            //go to index 去首页逛逛
            case R.id.tv_login_guide_go_to_index:
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
            //exit
            MyApplication.getInstance().exit();
        }
    }
}
