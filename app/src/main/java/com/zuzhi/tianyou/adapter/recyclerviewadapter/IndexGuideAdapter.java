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

import com.bigkoo.alertview.OnItemClickListener;
import com.google.android.gms.plus.model.people.Person;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.bean.IndexBean;
import com.zuzhi.tianyou.utils.Cons;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * adpater of index guide recyclerview 首页导航栏适配器
 */
public class IndexGuideAdapter extends RecyclerView.Adapter<IndexGuideAdapter.MyViewHolder> {
    private IndexBean.ValueEntity mValueEntity;
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
     * @param valueEntity    数据源
     */
    public IndexGuideAdapter(Context context, IndexBean.ValueEntity valueEntity) {
        mValueEntity = valueEntity;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_index_guide, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_guide.setText(mValueEntity.getCategory().get(position).getName());
        ImageLoader.getInstance().displayImage(
                Cons.IMG_HOST + mValueEntity.getCategory().get(position).getImgUrl(),
                holder.iv_guide,
                MyApplication.dis_ImgOptions);
        //set on item click listener 设置item点击监听
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
        return mValueEntity.getCategory().size();
    }

    class MyViewHolder extends ViewHolder {
        /**
         * text of index guide 首页导航栏文本
         */
        TextView tv_guide;

        /**
         * image of index guide 首页导航栏图片
         */
        ImageView iv_guide;

        private OnItemClickListener mListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_guide = (TextView) itemView.findViewById(R.id.tv_item_index_guide);
            iv_guide = (ImageView) itemView.findViewById(R.id.iv_item_index_guide);
        }


    }
}
