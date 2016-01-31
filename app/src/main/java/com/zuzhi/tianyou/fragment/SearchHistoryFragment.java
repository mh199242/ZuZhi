package com.zuzhi.tianyou.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.SearchHistoryLayoutManager;
import com.zuzhi.tianyou.adapter.listviewadapter.ClassLevelOneAdapter;
import com.zuzhi.tianyou.adapter.listviewadapter.ClassLevelTwoAdapter;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.SearchHistoryAdapter;
import com.zuzhi.tianyou.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchHistoryFragment extends BaseFragment implements View.OnClickListener {

    /**
     * search history recycyler 搜索历史列表
     */
    RecyclerView rv_history;

    @Override
    protected void setTitleBar() {

    }

    @Override
    protected int setLayoutID() {
        return R.layout.fragment_search_history;
    }

    @Override
    protected void initView(View view) {
        rv_history = (RecyclerView) view.findViewById(R.id.rv_search_search_history);

        //init history test data
        ArrayList<HashMap<String, Object>> data_history = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(i % 2 == 0)
                map.put("string", "最近");
            else
                map.put("string", "最近搜索" + i);
            data_history.add(map);
        }

        //set adapter
        SearchHistoryAdapter adp_history = new SearchHistoryAdapter(getContext(), data_history);
        rv_history.setAdapter(adp_history);
        rv_history.setLayoutManager(new SearchHistoryLayoutManager(getContext(), 4, StaggeredGridLayoutManager.HORIZONTAL));

    }

    @Override
    public void onClick(View v) {

    }


}

