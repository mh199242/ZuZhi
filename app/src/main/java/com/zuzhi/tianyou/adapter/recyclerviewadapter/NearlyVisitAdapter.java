package com.zuzhi.tianyou.adapter.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zuzhi.tianyou.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * adpater of nearly visit recyclerview 最近访问适配器
 */
public class NearlyVisitAdapter extends RecyclerView.Adapter<NearlyVisitAdapter.MyViewHolder> {
    private ArrayList<HashMap<String, Object>> mData;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

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
    public NearlyVisitAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_nearly_visit, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_nearly_visit.setText((String) mData.get(position).get("string"));

        if (mOnItemClickLitener != null) {


        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends ViewHolder {
        /**
         * text of nearly visit 最近访问文本
         */
        TextView tv_nearly_visit;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_nearly_visit = (TextView) itemView.findViewById(R.id.tv_item_nearly_visit);
        }
    }
}
