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
 * adpater of hot commodity info's certificate recyclerview 商品详情证书适配器
 */
public class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.MyViewHolder> {
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
    public CertificateAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_certificate, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.tv_certificate.setText((String) mData.get(position).get("certificate"));

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
         * certificate icon  证书图标
         */
        ImageView iv_certificate;

        /**
         * certificate text  证书文本
         */
        TextView tv_certificate;


        public MyViewHolder(View itemView) {
            super(itemView);
            iv_certificate = (ImageView) itemView.findViewById(R.id.iv_certificate);
            tv_certificate = (TextView) itemView.findViewById(R.id.tv_certificate);

        }
    }
}
