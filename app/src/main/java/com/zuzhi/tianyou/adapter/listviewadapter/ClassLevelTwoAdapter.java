package com.zuzhi.tianyou.adapter.listviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.adapter.layoutmanager.ClassLevelThreeLayoutManager;
import com.zuzhi.tianyou.adapter.recyclerviewadapter.ClassLevelThreeAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/1/23.
 */
public class ClassLevelTwoAdapter extends BaseAdapter {

    /**
     * data of class category 服务类目数据源
     */
    private ArrayList<HashMap<String, Object>> mData;

    private int mPosition;
    private LayoutInflater mInflater;

    private Context mContext;

    /**
     * constructor of class level one 一级服务类目构造器
     *
     * @param data    数据源
     * @param context 上下文
     */
    public ClassLevelTwoAdapter(ArrayList<HashMap<String, Object>> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf((String) mData.get(position).get("level_one_id"));
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_listview_class_level_two, null);
            holder.tv_class_level_two = (TextView) convertView.findViewById(R.id.tv_item_class_level_two);
            holder.rv_class_level_three = (RecyclerView) convertView.findViewById(R.id.rv_class_level_three);

            ClassLevelThreeAdapter adapter = new ClassLevelThreeAdapter(mContext, mData, position);
            holder.rv_class_level_three.setAdapter(adapter);
            holder.rv_class_level_three.setLayoutManager(new ClassLevelThreeLayoutManager(mContext, 2, ((String[]) mData.get(position).get("level_three")).length));
//            rv_select_profession.addItemDecoration(new SelectProfessionItemDecoration(this));
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_class_level_two.setText((String) mData.get(position).get("level_two"));

        return convertView;
    }

    public void setSelection(int position) {
        mPosition = position;
    }

    public int getSelection() {
        return mPosition;
    }

    class ViewHolder {
        TextView tv_class_level_two;
        RecyclerView rv_class_level_three;
    }
}
