package com.zuzhi.tianyou.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.exceptions.EaseMobException;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.bean.LoginBean;
import com.zuzhi.tianyou.bean.RegistBean;
import com.zuzhi.tianyou.entity.ProfessionEntity;
import com.zuzhi.tianyou.im.DemoHelper;
import com.zuzhi.tianyou.itemdecoration.SelectProfessionItemDecoration;
import com.zuzhi.tianyou.adapter.layoutmanager.SelectPerfessionLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.SelectProfessionAdapter;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DialogUtils;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.StringUtils;
import com.zuzhi.tianyou.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * select profession activity 选择职业页
 */
public class SelectProfessionActivity extends BaseActivity implements View.OnClickListener {

    Context mContext;
    /**
     * complete button 完成钮
     *
     * @return
     */
    private Button bt_complete;

    /**
     * intent 意图
     *
     * @return
     */
    private Intent intent;

    /**
     * profession recycylerview 职业列表
     *
     * @return
     */
    private RecyclerView rv_select_profession;

    /**
     * profession entity list
     */
    private List<ProfessionEntity> list;

    @Override
    protected int setContent() {
        return R.layout.activity_select_profession;
    }

    @Override
    protected void initViews() {
        mContext = this;
        rv_select_profession = (RecyclerView) findViewById(R.id.rv_select_profession);

        //init data
        getProfession();
    }

    @Override
    protected void initTitleBar() {
        ll_title_bar_left = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        bt_title_bar_left = (Button) findViewById(R.id.bt_title_bar_left);
        tv_title_bar_text = (TextView) findViewById(R.id.tv_title_bar_title);

        bt_title_bar_left.setOnClickListener(this);
        ll_title_bar_left.setOnClickListener(this);
    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        TitileBarSteep(getWindow().getDecorView());

        ll_title_bar_left.setVisibility(View.VISIBLE);
        tv_title_bar_text.setVisibility(View.VISIBLE);
        bt_title_bar_left.setVisibility(View.VISIBLE);

        tv_title_bar_text.setText(R.string.select_profession);

        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    /**
     * get profession
     */
    private void getProfession() {
        final ProgressDialog pd = ProgressDialog.show(this, null, getResources().getString(R.string.loading));
        // NoHttp get profession
        String url = Cons.DOMAIN + Cons.GET_PROFESSION;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("parentCode_eq", "position");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("获得职业列表", "---------url---------");
        Logs.i("获得职业列表", url);

        MyApplication.getInstance().queue.add(0, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                JSONObject jsonObject = null;
                try {
                    if (response.get() == null) {
                        ToastUtil.showToast(mContext, getResources().getString(R.string.data_error));
                        return;
                    }
                    jsonObject = response.get();

                    Logs.i("获得职业列表", jsonObject.toString());

                    RegistBean bean = MyApplication.gson.fromJson(jsonObject.toString(), RegistBean.class);
                    list = bean.getValue();

                    if (!jsonObject.getBoolean("success")) {
                        ToastUtil.showToast(mContext, getResources().getString(R.string.data_error));
                    } else {
                        SelectProfessionAdapter adapter = new SelectProfessionAdapter(mContext, list);
                        rv_select_profession.setAdapter(adapter);
                        rv_select_profession.setLayoutManager(new SelectPerfessionLayoutManager(mContext, 3, list.size()));
                        rv_select_profession.addItemDecoration(new SelectProfessionItemDecoration(mContext));
                        pd.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(SelectProfessionActivity.this, getResources().getString(R.string.request_fail));
                Logs.i("职业列表", "----------Error-------");
                Logs.i("职业列表", exception.toString());
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    /**
     * regist
     */
    public void regist(View view) {
        //init request data
        final String phone = getIntent().getStringExtra("phone");
        final String password = getIntent().getStringExtra("password");
        String yzm = getIntent().getStringExtra("yzm");
        long workId = list.get(0).getId();
        String userType = "1";
        String companyName = "";

        DialogUtils.showProgressDialog(this, getResources().getString(R.string.loading));

        // NoHttp regist
        String url = Cons.DOMAIN + Cons.REGIST;
        final Request<JSONObject> request =
                NoHttp.createJsonObjectRequest(url, RequestMethod.POST);

        JSONObject postJson = new JSONObject();
        try {
            postJson.put("callback", "");
            postJson.put("phone", phone);
            postJson.put("password", password);
            postJson.put("yzm", yzm);
            postJson.put("workId", workId);
            postJson.put("userType", userType);
            postJson.put("companyName", companyName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestBody(postJson.toString());

        Logs.i("注册", "---------url---------");
        Logs.i("注册", url);

        MyApplication.getInstance().queue.add(0, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                JSONObject jsonObject = null;
                try {
                    if (response.get() == null) {
                        ToastUtil.showToast(mContext, getResources().getString(R.string.data_error));
                        return;
                    }
                    jsonObject = response.get();

                    Logs.i("注册", jsonObject.toString());

                    if (jsonObject.getBoolean("success")) {
                        LoginBean bean = MyApplication.gson.fromJson(jsonObject.toString(), LoginBean.class);
                        MyApplication.user = bean.value;
                        //update user information
                        MyApplication.updataUserInfo(mContext);
                        ToastUtil.showToast(mContext, jsonObject.getString("message"));
                    } else {
                        ToastUtil.showToast(mContext, jsonObject.getString("errorMessage"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                ToastUtil.showToast(mContext, getResources().getString(R.string.request_fail));
                Logs.i("注册", "----------Error-------");
                Logs.i("注册", exception.toString());
            }

            @Override
            public void onFinish(int what) {
                DialogUtils.dismissProgressDialog();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //back button 返回键
            case R.id.ll_title_bar_left:
            case R.id.bt_title_bar_left:
                finish();
                break;
            //complete button 完成钮
            case R.id.bt_select_profession_complete:
                finish();
                break;
        }
    }

//    /**
//     * ease chat regist
//     *
//     * @param user
//     * @param pass
//     */
//    private void HXRegist(final String user, final String pass) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // 调用sdk注册方法
//                try {
//                    EMChatManager.getInstance().createAccountOnServer(user, pass);
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            // 保存用户名
//                            DemoHelper.getInstance().setCurrentUserName(user);
//                            DialogUtils.dismissProgressDialog();
//                            ToastUtil.showLongToast(mContext, getResources().getString(R.string.Registered_successfully));
//                            finish();
//                        }
//                    });
//                } catch (final EaseMobException e) {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            DialogUtils.dismissProgressDialog();
//                            int errorCode = e.getErrorCode();
//                            if (errorCode == EMError.NONETWORK_ERROR) {
//                                ToastUtil.showLongToast(mContext, getResources().getString(R.string.network_anomalies));
//                            } else if (errorCode == EMError.USER_ALREADY_EXISTS) {
//                                ToastUtil.showLongToast(mContext, getResources().getString(R.string.User_already_exists));
//                            } else if (errorCode == EMError.UNAUTHORIZED) {
//                                ToastUtil.showLongToast(mContext, getResources().getString(R.string.registration_failed_without_permission));
//                            } else if (errorCode == EMError.ILLEGAL_USER_NAME) {
//                                ToastUtil.showLongToast(mContext, getResources().getString(R.string.illegal_user_name));
//                            } else {
//                                ToastUtil.showLongToast(mContext, getResources().getString(R.string.Registration_failed) + e.getMessage());
//                            }
//                        }
//                    });
//                }
//            }
//        }).start();
//    }
}
