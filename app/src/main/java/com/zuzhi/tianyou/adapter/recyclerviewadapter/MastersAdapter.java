package com.zuzhi.tianyou.adapter.recyclerviewadapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
 * adpater of masters recyclerview 专家团适配器
 */
public class MastersAdapter extends RecyclerView.Adapter<MastersAdapter.MyViewHolder> {
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
    public MastersAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_masters, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.civ_head.setImageDrawable(mContext.getResources().getDrawable(Cons.IDARR_MASTERS_HEAD[position]));
        holder.tv_name.setText(Cons.STRARR_MASTERS_NAME[position]);
        holder.tv_exp.setText(Cons.STRARR_MASTERS_EXP[position]);
        holder.tv_good_at1.setText(Cons.STRARR_MASTERS_GODD_AT1[position]);
        holder.tv_good_at2.setText(Cons.STRARR_MASTERS_GODD_AT2[position]);
        holder.tv_good_evaluate_percent.setText(Cons.STRARR_MASTERS_GODD_EVALUATE_PERCENT[position]);
        holder.tv_complete_number.setText(Cons.STRARR_MASTERS_COMPLETE_NUMBER[position]);
        holder.tv_evaluate_number.setText(Cons.STRARR_MASTERS_EVALUATE_NUMBER[position]);

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
        return Cons.STRARR_MASTERS_NAME.length;
    }

    class MyViewHolder extends ViewHolder {

        /**
         * master head icon  专家头像
         */
        CircleImageView civ_head;

        /**
         * master name  专家名字
         */
        TextView tv_name;

        /**
         * master good at1   专家擅长领域1
         */
        TextView tv_good_at1;

        /**
         * master good at2   专家擅长领域2
         */
        TextView tv_good_at2;

        /**
         * master exp   专家经验
         */
        TextView tv_exp;

        /**
         * master good evaluate percent   专家好评率
         */
        TextView tv_good_evaluate_percent;


        /**
         * master complete order's number 专家完成单子数
         */
        TextView tv_complete_number;

        /**
         * master evaluate number 专家评价数
         */
        TextView tv_evaluate_number;


        public MyViewHolder(View itemView) {
            super(itemView);

            civ_head = (CircleImageView) itemView.findViewById(R.id.civ_item_masters_head);
            tv_name = (TextView) itemView.findViewById(R.id.tv_item_masters_name);
            tv_exp = (TextView) itemView.findViewById(R.id.tv_item_masters_exp);
            tv_good_at1 = (TextView) itemView.findViewById(R.id.tv_item_masters_good_at1);
            tv_good_at2 = (TextView) itemView.findViewById(R.id.tv_item_masters_good_at2);
            tv_good_evaluate_percent = (TextView) itemView.findViewById(R.id.tv_item_masters_good_evaluate_percent_number);
            tv_complete_number = (TextView) itemView.findViewById(R.id.tv_item_masters_good_complete_number);
            tv_evaluate_number = (TextView) itemView.findViewById(R.id.tv_item_masters_evaluate_number);

        }
    }
}
