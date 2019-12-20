package com.surhoo.sh.common.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class LinnearLayoutItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private Drawable mDrawable;
    //
    private int mTotalItems;//总Item数

    public LinnearLayoutItemDecoration(Context context, int drawableId) {
        this.mContext = context;
        this.mDrawable = ContextCompat.getDrawable(this.mContext, drawableId);
    }

    /**
     * @param outRect 用于规定分割线的范围
     * @param view    进行分割线操作的子view
     * @param parent  父view
     * @param state   (这里暂时不使用)
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (0 == mTotalItems)
            mTotalItems = parent.getAdapter().getItemCount();
        //在源码中有一个过时的方法，里面有获取当前ItemPosition
        int currentItemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (!isLastRow(currentItemPosition, mTotalItems))
            outRect.bottom = mDrawable.getIntrinsicWidth();
        else
            outRect.bottom = 0;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            drawHorizontalDecoration(c, parent.getChildAt(i));
        }
    }

    private void drawHorizontalDecoration(Canvas canvas, View childView) {
        int currentItemPosition = ((RecyclerView.LayoutParams) childView.getLayoutParams()).getViewLayoutPosition();
        if (isLastRow(currentItemPosition, mTotalItems)) {
            return;
        }
        //
        Rect rect = new Rect(0, 0, 0, 0);
        rect.top = childView.getBottom();
        rect.bottom = rect.top + mDrawable.getIntrinsicWidth();
        rect.left = childView.getLeft();
        rect.right = childView.getRight();
        mDrawable.setBounds(rect);
        mDrawable.draw(canvas);
    }

    private boolean isLastRow(int currentItemPosition, int totalItems) {
        boolean result = false;
        if (currentItemPosition + 1 >= totalItems)
            result = true;
        return result;
    }

}
