package com.zuzhi.tianyou.adapter.recyclerviewadapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.utils.Cons;

/**
 * adpater of coupon recyclerview 优惠券适配器
 */
public class MyCouponAdapter extends RecyclerView.Adapter<MyCouponAdapter.CouponViewHolder> implements View.OnClickListener {
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;
    /**是否可以使用的优惠券*/
    private boolean isCanUser;
    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            //company name 商户名称
//            case R.id.ll_item_my_processing_order_company_name:
//                //start company information activity 启动商户信息页
//                Intent intent = new Intent(mContext, CompanyInfoActivity.class);
//                mContext.startActivity(intent);
//                break;
//        }
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
     * @param isCanUser 是否可以使用的优惠券
     */
    public MyCouponAdapter(Context context, boolean isCanUser) {
        mContext = context;
        this.isCanUser = isCanUser;
    }

    @Override
    public CouponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CouponViewHolder holder = new CouponViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_coupon, parent, false));
        return holder;
    }


    @TargetApi(23)
    @Override
    public void onBindViewHolder(final CouponViewHolder holder, int position) {
        if(isCanUser){
            holder.tv_item_coupon_sign.setTextColor(mContext.getResources().getColor(R.color.color_bg_button_main_normal));
            holder.tv_item_coupon_money.setTextColor(mContext.getResources().getColor(R.color.color_bg_button_main_normal));
            holder.tv_item_coupon_use_info.setTextColor(mContext.getResources().getColor(R.color.color_bg_button_main_normal));
            holder.tv_item_coupon_title.setTextColor(mContext.getResources().getColor(R.color.color_bg_button_main_normal));
            holder.tv_item_coupon_title_num.setTextColor(mContext.getResources().getColor(R.color.color_bg_button_main_normal));
        } else {
            holder.tv_item_coupon_sign.setTextColor(mContext.getResources().getColor(R.color.color_text_hint));
            holder.tv_item_coupon_money.setTextColor(mContext.getResources().getColor(R.color.color_text_hint));
            holder.tv_item_coupon_use_info.setTextColor(mContext.getResources().getColor(R.color.color_text_hint));
            holder.tv_item_coupon_title.setTextColor(mContext.getResources().getColor(R.color.color_text_hint));
            holder.tv_item_coupon_title_num.setTextColor(mContext.getResources().getColor(R.color.color_text_hint));
        }

    }

    @Override
    public int getItemCount() {
        return Cons.STRARR_ORDER_COMPANY_NAME.length;
    }

    class CouponViewHolder extends ViewHolder {

        /**
         * coupon_sign  金额符号
         */
        TextView tv_item_coupon_sign;

        /**
         * coupon_money  优惠金额
         */
        TextView tv_item_coupon_money;

        /**
         * use_info  优惠券使用条件
         */
        TextView tv_item_coupon_use_info;

        /**
         * coupon_title  优惠码（文字）
         */
        TextView tv_item_coupon_title;


        /**
         * coupon_title_num  优惠券编号
         */
        TextView tv_item_coupon_title_num;

        /**
         * coupon_info  优惠券使用条件
         */
        TextView tv_item_coupon_info;

        /**
         * coupon_date  优惠券有效期
         */
        TextView tv_item_coupon_date;



        public CouponViewHolder(View itemView) {
            super(itemView);
            tv_item_coupon_sign = (TextView) itemView.findViewById(R.id.tv_item_coupon_sign);
            tv_item_coupon_title = (TextView) itemView.findViewById(R.id.tv_item_coupon_title);
            tv_item_coupon_money = (TextView) itemView.findViewById(R.id.tv_item_coupon_money);
            tv_item_coupon_use_info = (TextView) itemView.findViewById(R.id.tv_item_coupon_use_info);
            tv_item_coupon_title_num = (TextView) itemView.findViewById(R.id.tv_item_coupon_title_num);
            tv_item_coupon_info = (TextView) itemView.findViewById(R.id.tv_item_coupon_info);
            tv_item_coupon_date = (TextView) itemView.findViewById(R.id.tv_item_coupon_date);

        }


    }
}
