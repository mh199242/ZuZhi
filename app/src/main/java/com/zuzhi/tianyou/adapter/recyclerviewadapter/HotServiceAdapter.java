package com.zuzhi.tianyou.adapter.recyclerviewadapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuzhi.tianyou.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * adpater of hot service recyclerview 热门服务适配器
 */
public class HotServiceAdapter extends RecyclerView.Adapter<HotServiceAdapter.MyViewHolder> {
    public ArrayList<HashMap<String, Object>> mData;
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
    public HotServiceAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_index_hot_service, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.iv_service_img.setBackgroundDrawable((Drawable) mData.get(position).get("hot_service_img"));

        holder.tv_service_title.setText((String) mData.get(position).get("hot_service_title"));

        holder.tv_service_info1.setText((String) mData.get(position).get("hot_service_info1"));
        holder.tv_service_info2.setText((String) mData.get(position).get("hot_service_info2"));

        holder.tv_service_price1.setText((String) mData.get(position).get("hot_service_price1"));
        holder.tv_service_price2.setText((String) mData.get(position).get("hot_service_price2"));
        holder.tv_service_price2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        holder.tv_service_attribute.setText((String) mData.get(position).get("hot_service_attribute"));

        if (holder.tv_service_attribute.getText().toString().equals("普普通通")) {
            holder.tv_service_attribute.setVisibility(View.GONE);
        }
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends ViewHolder {

        /**
         * service icon  服务图标
         */
        ImageView iv_service_img;

        /**
         * service title  服务标题
         */
        TextView tv_service_title;

        /**
         * service info1  服务描述1
         */
        TextView tv_service_info1;

        /**
         * service info2  服务描述2
         */
        TextView tv_service_info2;

        /**
         * service price1  服务价格1
         */
        TextView tv_service_price1;

        /**
         * service price2  服务价格2
         */
        TextView tv_service_price2;

        /**
         * service attribute  服务属性
         */
        TextView tv_service_attribute;


        public MyViewHolder(View itemView) {
            super(itemView);
            iv_service_img = (ImageView) itemView.findViewById(R.id.iv_item_hot_service_icon);
            tv_service_title = (TextView) itemView.findViewById(R.id.tv_item_hot_service_title);

            tv_service_info1 = (TextView) itemView.findViewById(R.id.tv_item_hot_service_info1);
            tv_service_info2 = (TextView) itemView.findViewById(R.id.tv_item_hot_service_info2);

            tv_service_price1 = (TextView) itemView.findViewById(R.id.tv_item_hot_service_price1);
            tv_service_price2 = (TextView) itemView.findViewById(R.id.tv_item_hot_service_price2);

            tv_service_attribute = (TextView) itemView.findViewById(R.id.tv_item_hot_service_attribute);
        }
    }
}
