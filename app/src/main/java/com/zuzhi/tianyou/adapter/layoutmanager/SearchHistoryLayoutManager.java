package com.zuzhi.tianyou.adapter.layoutmanager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.View.MeasureSpec;

import com.zuzhi.tianyou.utils.ViewSetUtils;


/**
 * Created by Administrator on 2015/12/20.
 */
public class SearchHistoryLayoutManager extends StaggeredGridLayoutManager {
    int mSpanCount;
    Context mContext;

    public SearchHistoryLayoutManager(Context context, int spanCount, int orientation) {
        super(spanCount, orientation);
        mSpanCount = spanCount;

        mContext = context;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, State state, int widthSpec, int heightSpec) {
        View view = recycler.getViewForPosition(0);
        if (view != null) {
            measureChild(view, widthSpec, heightSpec);
            int measuredWidth = MeasureSpec.getSize(widthSpec);
            int measuredHeight = view.getMeasuredHeight();
                setMeasuredDimension(measuredWidth, measuredHeight * mSpanCount);
        }
    }
}
