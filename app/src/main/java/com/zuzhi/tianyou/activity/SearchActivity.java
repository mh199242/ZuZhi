package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.SearchHistoryLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.SearchHistoryAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.base.BaseFragment;
import com.zuzhi.tianyou.fragment.ClassFragment;
import com.zuzhi.tianyou.fragment.IndexFragment;
import com.zuzhi.tianyou.fragment.SearchHistoryFragment;
import com.zuzhi.tianyou.utils.Cons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    /**
     * fragment manager 碎片管理器
     */
    private FragmentManager fm;

    /**
     * search history fragment 搜索历史碎片
     */
    private SearchHistoryFragment searchHistoryFragment;

    /**
     * current fragment information intent 当前碎片信息意图
     */
    private Intent mIntent;

    /**
     * fragment list 碎片列表
     */
    private List<BaseFragment> fragmentList = new ArrayList<BaseFragment>();


    @Override
    protected int setContent() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        mIntent = getIntent();
        fm = getSupportFragmentManager();

        //display fragment 正确显示fragment
        switch (mIntent.getStringExtra("type_fragment")) {
            case Cons.FRAMENT_SEARCH_HISTORY:
                if (searchHistoryFragment == null) {
                    searchHistoryFragment = new SearchHistoryFragment();
                    fragmentList.add(searchHistoryFragment);
                    fm.beginTransaction().add(R.id.fm_search_container, searchHistoryFragment)
                            .commit();
                } else {
                    fm.beginTransaction().show(searchHistoryFragment).commit();
                }
                break;
        }
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
