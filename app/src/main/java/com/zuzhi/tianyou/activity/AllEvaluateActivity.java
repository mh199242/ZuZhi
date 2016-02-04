package com.zuzhi.tianyou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.EvaluateAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.ViewSetUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * all evaluate activity 全部评价页
 */
public class AllEvaluateActivity extends BaseActivity implements View.OnClickListener {

    /**
     * all evaluate recyclerview 全部评价列表
     */
    RecyclerView rv_all_evaluate;

    @Override
    protected int setContent() {
        return R.layout.activity_all_evaluate;
    }

    @Override
    protected void initViews() {
        //find views
        rv_all_evaluate = (RecyclerView) findViewById(R.id.rv_all_evaluate);

        //init test data
        ArrayList<HashMap<String, Object>> data_evaluate = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < Cons.IDARR_EVALUATE_HEAD.length + 1; i++) {
            HashMap<String, Object> map = map = new HashMap<String, Object>();
            if (i == 0) {
                map.put("percent", "98%");
                map.put("number", "53");
                data_evaluate.add(map);
            } else {
                map.put("head", getResources().getDrawable(Cons.IDARR_EVALUATE_HEAD[i - 1]));
                map.put("name", Cons.STRARR_EVALUATE_NAME[i - 1]);
                map.put("rating", Cons.RATINGARR_EVALUATE[i - 1]);
                map.put("date", Cons.STRARR_EVALUATE_DATE[i - 1]);
                map.put("content", Cons.STRARR_EVALUATE_CONTENT[i - 1]);
                data_evaluate.add(map);
            }
        }

        //set adapters
        EvaluateAdapter adp_evaluate = new EvaluateAdapter(this, data_evaluate);
        adp_evaluate.setTitleType(adp_evaluate.TITLE_HIDE_ALL_EVALUATE);//hide all evaluate layout 隐藏全部评价部分
        rv_all_evaluate.setAdapter(adp_evaluate);
        rv_all_evaluate.setLayoutManager(new LinearLayoutManager(this));
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

        tv_title_bar_text.setText(getResources().getString(R.string.all_evaluate));

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
