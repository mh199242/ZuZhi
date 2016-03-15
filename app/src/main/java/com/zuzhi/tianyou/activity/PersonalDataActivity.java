package com.zuzhi.tianyou.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.Cons;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * personal information activity 个人资料页
 */
public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {

    TextView tv_personal_name;
    CircleImageView civ_personal_head;

    @Override
    protected int setContent() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initViews() {
        tv_personal_name = (TextView) findViewById(R.id.tv_personal_name);
        civ_personal_head = (CircleImageView) findViewById(R.id.civ_personal_head);

        civ_personal_head.setOnClickListener(this);

        //修改姓名看是否跳页，如果不跳改成edittext
        tv_personal_name.setOnClickListener(this);

        //set user information
        tv_personal_name.setText(MyApplication.user.getName());
        ImageLoader.getInstance().displayImage(
                Cons.IMG_HOST + MyApplication.user.getHeadImg(),
                civ_personal_head,
                MyApplication.dis_ImgOptions
        );
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

        tv_title_bar_text.setText(R.string.personal_data);
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

            //change head icon 修改头像
            case R.id.civ_personal_head:
                Toast.makeText(this, R.string.modify_phone_num, Toast.LENGTH_LONG).show();
                break;

        }
    }
}
