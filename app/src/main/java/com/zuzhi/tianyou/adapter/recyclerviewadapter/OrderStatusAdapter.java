package com.zuzhi.tianyou.adapter.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.utils.Cons;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * adpater of order status recyclerview 订单状态适配器
 */
public class OrderStatusAdapter extends RecyclerView.Adapter<OrderStatusAdapter.MyViewHolder> {
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
     */
    public OrderStatusAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_order_status, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.civ_point.setImageResource(Cons.IDARR_ORDER_COLOR[position]);
        holder.tv_text.setText(Cons.STRARR_ORDER_STATUS[position]);
        holder.tv_text.setTextColor(mContext.getResources().getColor(Cons.IDARR_ORDER_COLOR[position]));
        holder.tv_date.setText(Cons.STRARR_ORDER_DATE[position]);
        holder.tv_date.setTextColor(mContext.getResources().getColor(Cons.IDARR_ORDER_COLOR[position]));
        holder.tv_time.setText(Cons.STRARR_ORDER_TIME[position]);
        holder.tv_time.setTextColor(mContext.getResources().getColor(Cons.IDARR_ORDER_COLOR[position]));
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
        return Cons.STRARR_ORDER_DATE.length;
    }

    class MyViewHolder extends ViewHolder {

        /**
         * order point 订单状态圆点
         */
        CircleImageView civ_point;

        /**
         * order status text  订单状态文本
         */
        TextView tv_text;

        /**
         * order status date  订单状态日期
         */
        TextView tv_date;

        /**
         * order status time  订单状态时间
         */
        TextView tv_time;


        public MyViewHolder(View itemView) {
            super(itemView);
            civ_point = (CircleImageView) itemView.findViewById(R.id.civ_item_order_status_point);
            tv_text = (TextView) itemView.findViewById(R.id.tv_item_order_status_text);
            tv_date = (TextView) itemView.findViewById(R.id.tv_item_order_status_date);
            tv_time = (TextView) itemView.findViewById(R.id.tv_item_order_status_time);
        }
    }
}
