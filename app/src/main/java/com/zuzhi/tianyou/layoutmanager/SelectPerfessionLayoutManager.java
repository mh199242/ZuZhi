package com.zuzhi.tianyou.layoutmanager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import android.view.View.MeasureSpec;

import com.zuzhi.tianyou.utils.ViewSetUtils;


/**
 * Created by Administrator on 2015/12/20.
 */
public class SelectPerfessionLayoutManager extends GridLayoutManager {
    int mSpanCount, mSize;
    Context mContext;

    public SelectPerfessionLayoutManager(Context context, int spanCount, int size) {
        super(context, spanCount);
        mSpanCount = spanCount;
        mSize = size;
        mContext = context;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, State state, int widthSpec, int heightSpec) {
        View view = recycler.getViewForPosition(0);
        if (view != null) {
            measureChild(view, widthSpec, heightSpec);
            int measuredWidth = MeasureSpec.getSize(widthSpec);
            int measuredHeight = view.getMeasuredHeight();
            if (mSize % mSpanCount != 0) {
                setMeasuredDimension(measuredWidth, measuredHeight * ((mSize / mSpanCount) + 1) + ViewSetUtils.dp2px(mContext, 15) * (mSize / mSpanCount) + ViewSetUtils.dp2px(mContext, 20));
            } else {
                setMeasuredDimension(measuredWidth, measuredHeight * (mSize / mSpanCount) + ViewSetUtils.dp2px(mContext, 15) * ((mSize / mSpanCount) - 1) + ViewSetUtils.dp2px(mContext, 20));
            }

        }
    }
}
