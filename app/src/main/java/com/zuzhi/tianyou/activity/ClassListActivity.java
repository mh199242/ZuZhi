package com.zuzhi.tianyou.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.TopicLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.HotServiceAdapter;
import com.zuzhi.tianyou.adapter.viewpageradapter.ClassListAdapter;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.ViewSetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ClassListActivity extends BaseActivity implements View.OnClickListener{

    /**
     * tablayout 标签页
     */
    private TabLayout tl_class_list;

    /**
     * viewpager(recyclerview) 页卡（内容为recyclerview）
     */
    private ViewPager vp_class_list;

    /**
     * viewpager content  页卡内容
     */
    private RecyclerView rv_class_list;

    /**
     * viewpager's view list 页卡列表
     */
    private ArrayList<View> list_views = new ArrayList<View>();

    /**
     * top tooll appbar   顶部toll appbar
     */
    private AppBarLayout abl_class_list_tool;

    @Override
    protected int setContent() {
        return R.layout.activity_class_list;
    }

    @Override
    protected void initViews() {
        vp_class_list = (ViewPager) findViewById(R.id.vp_class_list);
        tl_class_list = (TabLayout) findViewById(R.id.tl_class_list);

        //init hot service test data 加载测试数据
        ArrayList<HashMap<String, Object>> data_hotService = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < Cons.STRARR_INDEX_HOT_SERVICE_TITLE.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("hot_service_img", getResources().getDrawable(Cons.IDARR_INDEX_HOT_SERVICE_IMG[i]));
            map.put("hot_service_title", Cons.STRARR_INDEX_HOT_SERVICE_TITLE[i]);
            map.put("hot_service_info1", Cons.STRARR_INDEX_HOT_SERVICE_INFO1[i]);
            map.put("hot_service_info2", Cons.STRARR_INDEX_HOT_SERVICE_INFO2[i]);
            map.put("hot_service_price1", Cons.STRARR_INDEX_HOT_SERVICE_PRICE1[i]);
            map.put("hot_service_price2", Cons.STRARR_INDEX_HOT_SERVICE_PRICE2[i]);
            map.put("hot_service_attribute", Cons.STRARR_INDEX_HOT_SERVICE_ATTRIBUTE[i]);

            data_hotService.add(map);
        }

        for (int i = 0; i < 8; i++) {
            //init and set up wiht recyclerview adapter 初始化并设置recyclerview适配器
            rv_class_list = new RecyclerView(this);
            HotServiceAdapter adp_hotService = new HotServiceAdapter(this, data_hotService);
            rv_class_list.setAdapter(adp_hotService);
            //rv_class_list.setLayoutManager(new TopicLayoutManager(this, OrientationHelper.VERTICAL, false, data_hotService.size()));
            rv_class_list.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));

            //add test viewpager views
            vp_class_list.addView(rv_class_list);
            //add test viewpager view to list
            list_views.add(rv_class_list);
            //add tab views
            tl_class_list.addTab(tl_class_list.newTab().setText("标签" + i));
        }

        //set up with viewpager adpater 给ViewPager设置适配器
        ClassListAdapter adp_classList = new ClassListAdapter(list_views);
        vp_class_list.setAdapter(adp_classList);
        //tablayout setup with viewPager 将TabLayout和ViewPager关联起来。
        tl_class_list.setupWithViewPager(vp_class_list);
        //set up with tablayout adpater给Tabs设置适配器
        tl_class_list.setTabsFromPagerAdapter(adp_classList);

    }

    @Override
    protected void initTitleBar() {
        rl_title_bar_search = (RelativeLayout) findViewById(R.id.rl_title_bar_search);
        ll_title_bar_left = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        bt_title_bar_left = (Button) findViewById(R.id.bt_title_bar_left);
        bt_title_bar_search = (Button) findViewById(R.id.bt_title_bar_search);
        ll_title_bar_right = (LinearLayout) findViewById(R.id.ll_title_bar_right);
        tv_title_bar_right = (TextView) findViewById(R.id.tv_title_bar_right);
        abl_class_list_tool = (AppBarLayout) findViewById(R.id.abl_class_list_tool);
    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            showSystemUI(getWindow().getDecorView());
        }else{
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = ViewSetUtils.dp2px(this, 44);
            abl_class_list_tool.setLayoutParams(params);
        }
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
