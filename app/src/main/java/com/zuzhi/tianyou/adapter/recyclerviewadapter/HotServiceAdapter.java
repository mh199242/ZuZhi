package com.zuzhi.tianyou.adapter.recyclerviewadapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.entity.ItemListEntity;
import com.zuzhi.tianyou.utils.Cons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * adpater of hot service recyclerview 热门服务适配器
 */
public class HotServiceAdapter extends RecyclerView.Adapter<HotServiceAdapter.MyViewHolder> {
    private List<ItemListEntity> mItemList;
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
     * @param context  上下文
     * @param itemList 数据源
     */
    public HotServiceAdapter(Context context, List<ItemListEntity> itemList) {
        mItemList = itemList;
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
        ImageLoader.getInstance().displayImage(
                Cons.IMG_HOST + mItemList.get(position).getItemThumbImg(),
                holder.iv_service_img,
                MyApplication.dis_ImgOptions);
        holder.tv_service_title.setText(mItemList.get(position).getName());
        holder.tv_service_info1.setText("由" + mItemList.get(position).getExpertName() + "提供服务");
        holder.tv_service_info2.setText(mItemList.get(position).getExpertWorkingHours() + "年经验");
        holder.tv_service_price2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_service_attribute.setText("限时");
        if (mItemList.get(position).getItemMarketPrice() != 0) {
            holder.tv_service_price2.setText("￥"
                    + mItemList.get(position).getItemMarketPrice());
        } else {
            holder.tv_service_price2.setText("");
        }
        if (mItemList.get(position).isItemPromote()) {
            holder.tv_service_price1.setText("￥" +
                    mItemList.get(position).getItemPromotePrice());
        } else {
            holder.tv_service_price1.setText("￥" +
                    mItemList.get(position).getItemShopPrice());
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
        return mItemList.size();
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
