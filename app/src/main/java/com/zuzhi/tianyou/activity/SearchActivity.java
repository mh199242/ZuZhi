package com.zuzhi.tianyou.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.SearchHistoryAdapter;
import com.zuzhi.tianyou.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    /**
     * search history recycyler 搜索历史列表
     */
    RecyclerView rv_history;

    @Override
    protected int setContent() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        rv_history = (RecyclerView) findViewById(R.id.rv_search_search_history);

        //init history test data
        ArrayList<HashMap<String, Object>> data_history = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(i == 3)
            map.put("string", "最近");
            else
                map.put("string", "最近搜索" + i);
            data_history.add(map);
        }

        //set adapter
        SearchHistoryAdapter adp_history = new SearchHistoryAdapter(this, data_history);
        rv_history.setAdapter(adp_history);
        rv_history.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    protected void initTitleBar() {
        rl_title_bar_search = (RelativeLayout) findViewById(R.id.rl_title_bar_search);
        ll_title_bar_left = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        bt_title_bar_left = (Button) findViewById(R.id.bt_title_bar_left);
        bt_title_bar_search = (Button) findViewById(R.id.bt_title_bar_search);
        ll_title_bar_right = (LinearLayout) findViewById(R.id.ll_title_bar_right);
        tv_title_bar_right = (TextView) findViewById(R.id.tv_title_bar_right);
    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        showSystemUI(getWindow().getDecorView());

        ll_title_bar_left.setVisibility(View.VISIBLE);
        bt_title_bar_left.setVisibility(View.VISIBLE);
        bt_title_bar_search.setVisibility(View.GONE);
        ll_title_bar_right.setVisibility(View.VISIBLE);
        tv_title_bar_right.setVisibility(View.VISIBLE);
        rl_title_bar_search.setVisibility(View.VISIBLE);

        tv_title_bar_right.setText(R.string.search);

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
        }
    }
}
