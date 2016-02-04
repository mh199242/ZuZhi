package com.zuzhi.tianyou.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.itemdecoration.SelectProfessionItemDecoration;
import com.zuzhi.tianyou.adapter.layoutmanager.SelectPerfessionLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.SelectProfessionAdapter;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * select profession activity 选择职业页
 */
public class SelectProfessionActivity extends BaseActivity implements View.OnClickListener {

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
     * profession data 职业数据源
     *
     * @return
     */
    private ArrayList<HashMap<String, Object>> mData;

    @Override
    protected int setContent() {
        return R.layout.activity_select_profession;
    }

    @Override
    protected void initViews() {

        mData = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("string", "会计师");
            mData.add(map);
        }
        bt_complete = (Button) findViewById(R.id.bt_select_profession_complete);
        rv_select_profession = (RecyclerView) findViewById(R.id.rv_select_profession);

        SelectProfessionAdapter adapter = new SelectProfessionAdapter(this, mData);
        rv_select_profession.setAdapter(adapter);
        rv_select_profession.setLayoutManager(new SelectPerfessionLayoutManager(this, 3, mData.size()));
        rv_select_profession.addItemDecoration(new SelectProfessionItemDecoration(this));

        bt_complete.setOnClickListener(this);
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
}
