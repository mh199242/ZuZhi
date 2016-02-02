package com.bigkoo.alertview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Sai on 15/8/9.
 */
public class AlertViewAdapter extends BaseAdapter {
    private List<String> mDatas;
    private List<String> mDestructive;
    private List<Drawable> mDrawables;
    private List<Integer> list_textLength;
    private Context mContext;

    public AlertViewAdapter(List<String> datas, List<String> destructive, @Nullable List<Drawable> drawables, Context context) {
        this.mDatas = datas;
        this.mDestructive = destructive;
        this.mDrawables = drawables;
        list_textLength = new ArrayList<>();
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String data = mDatas.get(position);
        Holder holder = null;
        View view = convertView;
        if (AlertView.style == AlertView.Style.ActionSheet) {
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(R.layout.item_alertbutton_sheet, null);
                holder = creatHolder(view);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
        } else if (AlertView.style == AlertView.Style.Alert) {
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(R.layout.item_alertbutton, null);

                holder = creatHolder(view);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
        }

        holder.UpdateUI(parent.getContext(), data, position, view);
        return view;
    }

    public Holder creatHolder(View view) {
        return new Holder(view);
    }

    class Holder {
        private TextView tvAlert;
        private ImageView ivAlert;

        public Holder(View view) {
            if (AlertView.style == AlertView.Style.Alert) {
                tvAlert = (TextView) view.findViewById(R.id.tvAlert);
            } else if (AlertView.style == AlertView.Style.ActionSheet) {
                tvAlert = (TextView) view.findViewById(R.id.tvAlert);
                ivAlert = (ImageView) view.findViewById(R.id.ivAlert);
            }


        }

        public void UpdateUI(Context context, String data, int position, View view) {
            if (AlertView.style == AlertView.Style.ActionSheet) {
                tvAlert.setText(data);
                ivAlert.setBackgroundDrawable(mDrawables.get(position));
                for (int i = 0; i < mDatas.size(); i++) {

                    int textLength = mDatas.get(i).length() * ViewSetUtils.dp2px(mContext, 14) + tvAlert.getPaddingLeft();
                    list_textLength.add(textLength);
                    Log.i("麻辣隔壁" , "" + textLength);
                }

                int temp = Collections.max(list_textLength);
                Log.i("麻辣隔壁" , "最大的是" + temp);
                tvAlert.setWidth(temp);


            } else if (AlertView.style == AlertView.Style.Alert) {
                tvAlert.setText(data);
            }

        }

    }
}