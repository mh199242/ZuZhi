package com.zuzhi.tianyou.adapter.recyclerviewadapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.zuzhi.tianyou.R;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * adpater of hot evaluate recyclerview 用户评价适配器
 */
public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.MyViewHolder> {
    private ArrayList<HashMap<String, Object>> mData;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    //title view type 评价标题类型布局
    private final int TYPE_TITLE = 0;
    //content view type 评价内容类型布局
    private final int TYPE_DETAILS = 1;

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
    public EvaluateAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else {
            return TYPE_DETAILS;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = null;
        switch (viewType) {
            case TYPE_TITLE:
                holder = new MyViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_evaluate_title, parent, false));
                holder.initTitle(holder.itemView);
                break;

            case TYPE_DETAILS:
                holder = new MyViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_evaluate_details, parent, false));
                holder.initDetails(holder.itemView);
                break;
        }

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                holder.tv_good_evaluate_percent.setText((String) mData.get(position).get("percent"));
                holder.tv_evaluate_number.setText((String) mData.get(position).get("number") + "人已评价");
                break;
            case TYPE_DETAILS:
                holder.civ_head.setImageDrawable((Drawable) mData.get(position).get("head"));
                holder.rbr_rating.setRating((float) mData.get(position).get("rating"));
                holder.tv_date.setText((String) mData.get(position).get("date"));
                holder.tv_content.setText((String) mData.get(position).get("content"));
                holder.tv_name.setText((String) mData.get(position).get("name"));
                break;
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
         * good evaluate percent number 好评率百分比
         */
        TextView tv_good_evaluate_percent;

        /**
         * evaluate number  评价数
         */
        TextView tv_evaluate_number;

        /**
         * user head icon 头像
         */
        CircleImageView civ_head;

        /**
         * user name  姓名
         */
        TextView tv_name;

        /**
         * rating 评分
         */
        RatingBar rbr_rating;

        /**
         * evaluate date 评论日期
         */
        TextView tv_date;

        /**
         * evaluate content 评论内容
         */
        TextView tv_content;

        public MyViewHolder(View itemView) {
            super(itemView);

        }

        //find title views
        private void initTitle(View itemView) {
            tv_good_evaluate_percent =
                    (TextView) itemView.findViewById(R.id.tv_item_evaluate_title_good_evaluate_percent_number);
            tv_evaluate_number =
                    (TextView) itemView.findViewById(R.id.tv_item_evaluate_title_good_evaluate_number);
        }

        //find details views
        private void initDetails(View itemView) {
            civ_head =
                    (CircleImageView) itemView.findViewById(R.id.civ_item_evaluate_details_head);
            tv_name =
                    (TextView) itemView.findViewById(R.id.tv_item_evaluate_details_name);
            rbr_rating =
                    (RatingBar) itemView.findViewById(R.id.rbr_item_evaluate_details_rating);
            tv_date =
                    (TextView) itemView.findViewById(R.id.tv_item_evaluate_details_date);
            tv_content =
                    (TextView) itemView.findViewById(R.id.tv_item_evaluate_details_content);
        }
    }
}
