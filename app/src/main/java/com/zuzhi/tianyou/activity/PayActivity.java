package com.zuzhi.tianyou.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;

/**
 * opinion request activity 付款页面
 * */
public class PayActivity extends BaseActivity implements View.OnClickListener {

    public static final String STR_ORDER_MONEY = "ORDER_MONEY";
    public static final String STR_ORDER_NUM = "ORDER_NUM";

    /** 选择支付方式用int型方便以后再添加支付方式 */
    private int checkPayIndex;

    public static final int  WECHAT_PAY = 0;
    public static final int  ALI_PAY = 1;

    /** 显示金额 */
    TextView tv_pay_orderMoney;
    /** 显示订单号 */
    TextView tv_pay_orderNum;
    /** 选择微信 */
    RelativeLayout rl_checkWechat;
    //选择微信支付
    RelativeLayout rl_checkAli;

    ImageView iv_checkWechat, iv_checkAlipay;


    Button bt_pay_right_now;

    /** intent 接收金额 */
    int orderNMoney;

    /** intent 接收订单号 */
    String orderNum;




    @Override
    protected int setContent() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initViews() {
        checkPayIndex = WECHAT_PAY;
        tv_pay_orderMoney = (TextView)findViewById(R.id.tv_pay_orderMoney);
        tv_pay_orderNum = (TextView)findViewById(R.id.tv_pay_orderNum);
        rl_checkWechat = (RelativeLayout)findViewById(R.id.rl_checkWechat);
        rl_checkAli = (RelativeLayout)findViewById(R.id.rl_checkAli);
        iv_checkWechat = (ImageView)findViewById(R.id.iv_checkWechat);
        iv_checkAlipay = (ImageView)findViewById(R.id.iv_checkAlipay);
        bt_pay_right_now = (Button)findViewById(R.id.bt_pay_right_now);
        bt_pay_right_now.setOnClickListener(this);
        rl_checkWechat.setOnClickListener(this);
        rl_checkAli.setOnClickListener(this);

        orderNMoney = getIntent().getIntExtra(STR_ORDER_MONEY, 0);
        orderNum = getIntent().getStringExtra(STR_ORDER_NUM);
        tv_pay_orderMoney.setText("￥"+orderNMoney);
        tv_pay_orderNum.setText(orderNum);
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

        tv_title_bar_text.setText(R.string.pay);
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
            case R.id.bt_pay_right_now:
                Toast.makeText(this,"payindex = "+checkPayIndex,Toast.LENGTH_LONG).show();
                break;

            //选择微信支付
            case R.id.rl_checkWechat:
                if ( checkPayIndex != WECHAT_PAY) {
                    checkPayIndex = WECHAT_PAY;
                    iv_checkWechat.setImageResource(R.drawable.order_choose);
                    iv_checkAlipay.setImageResource(R.drawable.order_n_choose);
                }
                break;

            //选择支付宝支付
            case R.id.rl_checkAli:

                if ( checkPayIndex != ALI_PAY) {
                    checkPayIndex = ALI_PAY;
                    iv_checkAlipay.setImageResource(R.drawable.order_choose);
                    iv_checkWechat.setImageResource(R.drawable.order_n_choose);
                }
                break;


        }
    }
}
