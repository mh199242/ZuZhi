package com.zuzhi.tianyou.adapter.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * adpater of setlect profession recyclerview 选择职业适配器
 */
public class ClassLevelThreeAdapter extends RecyclerView.Adapter<ClassLevelThreeAdapter.MyViewHolder> {
    private ArrayList<HashMap<String, Object>> mData;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;
    private int mIndex;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    /**
     * init 初始化适配器，载入数据源
     *
     * @param context 上下文
     * @param data    数据源
     */
    public ClassLevelThreeAdapter(Context context, ArrayList<HashMap<String, Object>> data, int index) {
        mData = data;
        mContext = context;
        mIndex = index;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_class_levle_three, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_item_class_level_three.setText(((String[]) mData.get(position).get("level_three"))[position]);
        if (mOnItemClickLitener != null) {


        }
    }

    @Override
    public int getItemCount() {
        return ((String[]) mData.get(mIndex).get("level_three")).length;
    }

    class MyViewHolder extends ViewHolder {

        /**
         * list of class level three  三级类目列表
         */
        LinearLayout ll_class_level_three;

        /**
         * text of class level three  三级类目文本
         */
        TextView tv_item_class_level_three;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll_class_level_three = (LinearLayout) itemView.findViewById(R.id.ll_item_class_level_three);
            tv_item_class_level_three = (TextView) itemView.findViewById(R.id.tv_item_class_level_three);
        }
    }
}
