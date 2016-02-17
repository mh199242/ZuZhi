package com.zuzhi.tianyou.adapter.recyclerviewadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.activity.CheckInfoActivity;
import com.zuzhi.tianyou.activity.CompanyInfoActivity;
import com.zuzhi.tianyou.activity.PayActivity;
import com.zuzhi.tianyou.utils.Cons;

/**
 * adpater of processing order recyclerview 进行中订单适配器
 */
public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> implements View.OnClickListener {
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    public int getOrderType() {
        return i_orderType;
    }

    public void setOrderType(int i_orderType) {
        this.i_orderType = i_orderType;
    }

    /**
     * my order type我的订单订单类型
     */
    private int i_orderType;

    public final int PROCESSING = 0;//processing 进行中
    public final int COMPLETED = 1;//completed 已完成

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            //company name 商户名称
            case R.id.ll_item_my_order_company_name:
                //start company information activity 启动商户信息页
                intent = new Intent(mContext, CompanyInfoActivity.class);
                mContext.startActivity(intent);
                break;
        }
    }

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
    public MyOrderAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_my_order, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        switch (i_orderType) {
            case PROCESSING:
                //wait for pay status button process 待支付状态按钮处理
                if (Cons.STRARR_ORDER_PROCESSING_STATUS[position].equals("待支付")) {
                    holder.bt_main.setText(mContext.getResources().getString(R.string.go_pay));
                    holder.bt_child1.setVisibility(View.GONE);
                    holder.bt_child2.setVisibility(View.GONE);
                    holder.bt_main.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(mContext, PayActivity.class);
                            //订单信息 从bean中get数据
                            in.putExtra(PayActivity.STR_ORDER_MONEY, 900);
                            in.putExtra(PayActivity.STR_ORDER_NUM, "6484859");
                            mContext.startActivity(in);
                        }
                    });
                }
                //company servicing status button process 商家服务中状态按钮处理
                else {
                    holder.bt_main.setText(mContext.getString(R.string.confirm_complete));
                }
                holder.tv_status.setText(Cons.STRARR_ORDER_PROCESSING_STATUS[position]);
                holder.bt_child1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //start check info activity 启动查看详情页
                        Intent intent = new Intent(mContext, CheckInfoActivity.class);
                        mContext.startActivity(intent);
                    }
                });
                break;
            case COMPLETED:
                //euvaluated status button process 已评论状态按钮处理
                if (Cons.INTARR_ORDER_EVALUATE_STATUS[position] == 0) {
                    holder.bt_main.setVisibility(View.GONE);
                    holder.bt_child1.setText(mContext.getString(R.string.evaluated));
                    holder.bt_child2.setText(mContext.getString(R.string.check_information));
                    holder.bt_main.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent in = new Intent(mContext, PayActivity.class);
//                            //订单信息 从bean中get数据
//                            in.putExtra(PayActivity.STR_ORDER_MONEY, 900);
//                            in.putExtra(PayActivity.STR_ORDER_NUM, "6484859");
//                            mContext.startActivity(in);
                        }
                    });
                }
                //unevaluated status button process 未评论状态按钮处理
                else {
                    holder.bt_main.setText(mContext.getString(R.string.evaluate));
                    holder.bt_child2.setVisibility(View.GONE);
                }
                holder.tv_status.setText("交易成功");
                holder.bt_child2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //start check info activity 启动查看详情页
                        Intent intent = new Intent(mContext, CheckInfoActivity.class);
                        mContext.startActivity(intent);
                    }
                });
                break;
        }

        //order attitude process 订单属性处理
        if (Cons.STRARR_INDEX_HOT_SERVICE_ATTRIBUTE[position].equals("普普通通")) {
            holder.tv_attitude.setVisibility(View.GONE);
        } else {
            holder.tv_attitude.setText(Cons.STRARR_INDEX_HOT_SERVICE_ATTRIBUTE[position]);
        }

        holder.iv_icon.setBackgroundDrawable(
                (Drawable) mContext.getResources().getDrawable(Cons.IDARR_INDEX_HOT_SERVICE_IMG[position]));
        holder.tv_title.setText(Cons.STRARR_INDEX_HOT_SERVICE_TITLE[position]);
        holder.tv_info1.setText(Cons.STRARR_INDEX_HOT_SERVICE_INFO1[position]);
        holder.tv_info2.setText(Cons.STRARR_INDEX_HOT_SERVICE_INFO2[position]);
        holder.tv_price1.setText(Cons.STRARR_INDEX_HOT_SERVICE_PRICE1[position]);
        holder.tv_price2.setText(Cons.STRARR_INDEX_HOT_SERVICE_PRICE2[position]);
        holder.tv_price2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_total.setText(Cons.STRARR_ORDER_TOTAL_PRICE[position]);
        holder.tv_company_name.setText(Cons.STRARR_ORDER_COMPANY_NAME[position]);

        holder.ll_company_name.setOnClickListener(this);

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
        return Cons.STRARR_ORDER_COMPANY_NAME.length;
    }

    class MyViewHolder extends ViewHolder {

        /**
         * company name  商户名称
         */
        TextView tv_company_name;

        /**
         * order status  订单状态
         */
        TextView tv_status;

        /**
         * order title  订单标题
         */
        TextView tv_title;

        /**
         * order info1  订单信息1
         */
        TextView tv_info1;

        /**
         * order info2  订单信息2
         */
        TextView tv_info2;

        /**
         * order price1  订单价格1
         */
        TextView tv_price1;

        /**
         * order price2  订单价格2
         */
        TextView tv_price2;

        /**
         * order attitude  订单属性
         */
        TextView tv_attitude;

        /**
         * child button2 订单子按钮2
         */
        Button bt_child2;

        /**
         * child button1 订单子按钮1
         */
        Button bt_child1;

        /**
         * order total 总价
         */
        TextView tv_total;

        /**
         * order main button 订单主按钮
         */
        Button bt_main;

        /**
         * order icon 订单图片
         */
        ImageView iv_icon;

        /**
         * layout of oder's company 订单隶属商户布局
         */
        LinearLayout ll_company_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_company_name = (TextView) itemView.findViewById(R.id.tv_item_my_order_company_name);
            tv_status = (TextView) itemView.findViewById(R.id.tv_item_my_order_status);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_item_my_order_icon);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_my_order_title);
            tv_info1 = (TextView) itemView.findViewById(R.id.tv_item_my_order_info1);
            tv_info2 = (TextView) itemView.findViewById(R.id.tv_item_my_order_info2);
            tv_price1 = (TextView) itemView.findViewById(R.id.tv_item_my_order_price1);
            tv_price2 = (TextView) itemView.findViewById(R.id.tv_item_my_order_price2);
            tv_attitude = (TextView) itemView.findViewById(R.id.tv_item_my_order_attribute);
            tv_total = (TextView) itemView.findViewById(R.id.tv_item_my_order_total_number);
            bt_main = (Button) itemView.findViewById(R.id.bt_item_my_order_main);
            bt_child1 = (Button) itemView.findViewById(R.id.bt_item_my_order_child1);
            bt_child2 = (Button) itemView.findViewById(R.id.bt_item_my_order_child2);
            ll_company_name = (LinearLayout) itemView.findViewById(R.id.ll_item_my_order_company_name);

        }


    }
}
