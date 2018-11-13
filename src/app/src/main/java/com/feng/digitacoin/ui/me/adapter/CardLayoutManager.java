package com.feng.digitacoin.ui.me.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/13--10:28 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
public class CardLayoutManager extends LayoutManager{

    private final String TAG = "CardLayoutManager";

    // x 方向的偏移距离
    private int mXoffset = 0;

    // 所有view的移动范围
    private int mTotalWidth = 0;

    // 子 view 的测量宽度
    private int childViewMeasureWidth = 0;

    // 子 view 的测量高度
    private int childViewMeasureHeight = 0;

    // 子view实际占据宽度（测量宽度+间隙）
    private int childViewWidth = 0;

    // 起始 view 距离左右单边的距离
    private int startWidthSpace = 0;

    // 起始 view 距离上下单边的距离
    private int startHeightSpace = 0;

    // 两个view之间的间隙
    private int widthSpace = 0;

    // 自动选中的动画移动时间
    private long mFixingAnimationDuration = 200L;

    // 屏幕中能显示的 view 的个数
    private int mVisibledItemCount = 2;

    // 第一条可见Item的位置
    private int mFirstVisibleItemPosition = 0;

    // 动画实例
    private Animator animator;

    // 全局回收器
    private RecyclerView.Recycler mRecycler;

    // 全局状态
    private RecyclerView.State mState;

    // recyclerView 宽度
    private int mWidth;

    // recyclerView 高度
    private int mHeight;

    // 最远距离的 X 缩放比例
    private float xScale = 0.8f;

    // 最远距离透明度
    private float mAlaph = 0.6f;

    // 当前选中的监听
    private OnItemSelectedListener onItemSelectedListener;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        mRecycler = recycler;
        mState = state;
        if(state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        if(state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);

        mWidth = getWidth();
        mHeight = getHeight();

        View childView = recycler.getViewForPosition(0);
        if(childView != null) {
            measureChildWithMargins(childView, 0, 0);
            childViewMeasureWidth = getDecoratedMeasuredWidth(childView);
            childViewMeasureHeight = getDecoratedMeasuredHeight(childView);
            startWidthSpace = (mWidth - childViewMeasureWidth) / 2;
            //startHeightSpace = (mHeight - childViewMeasureHeight) / 2;
            startHeightSpace = 0;
            widthSpace = startWidthSpace / 2;

            childViewWidth = childViewMeasureWidth + widthSpace;
            mTotalWidth = (getItemCount() - 1) * childViewWidth;
            layoutChildView(recycler, state);
        }

    }

    private void layoutChildView(RecyclerView.Recycler recycler, RecyclerView.State state) {
        SparseArray<View> needLayoutItems = getNeedLayoutItems();
        if(needLayoutItems.size() == 0 || state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        onLayout(recycler, needLayoutItems);
        recyclerChildren(recycler);
    }

    private void onLayout(RecyclerView.Recycler recycler, SparseArray<View> needLayoutItems) {
        for(int i = 0; i < needLayoutItems.size(); i++) {
            int position = needLayoutItems.keyAt(i);
            View childView = recycler.getViewForPosition(position);
            if(childView != null) {
                addView(childView);
                measureChildWithMargins(childView, 0 ,0);
                int left = startWidthSpace + position * childViewWidth + mXoffset;
                int right = left + childViewMeasureWidth;
                layoutDecoratedWithMargins(childView, left, startHeightSpace, right, startHeightSpace + childViewMeasureHeight);
                int distance = 0;
                if(left > startWidthSpace) {
                    distance = left - startWidthSpace;
                }else {
                    distance = startWidthSpace - left;
                }
                float scale = 1 - ((1 - xScale) * distance) / childViewWidth;
                childView.setScaleY(scale);
                //childView.setAlpha(scale);
            }
        }
    }


    private SparseArray<View> getNeedLayoutItems() {
        SparseArray<View> needLayoutItems = new SparseArray<>();
        float currentDistance;
        int totalCount = getItemCount();
        for(int position = 0; position < totalCount; position++) {
            currentDistance = (position) * childViewWidth + childViewMeasureWidth + startWidthSpace + mXoffset;
            if(currentDistance >= 0) {
                mFirstVisibleItemPosition = position;
                break;
            }
        }
        // todo:extension
        int endIndex = mFirstVisibleItemPosition + mVisibledItemCount;
        if(endIndex > totalCount) {
            endIndex = totalCount;
        }
        for(int position = mFirstVisibleItemPosition; position <= endIndex; position ++) {
            if(position >= totalCount) break;
            View childView = mRecycler.getViewForPosition(position);
            if(childView != null) {
                Log.e(TAG, "position:" + position);
                needLayoutItems.put(position, childView);
            }
        }
        return needLayoutItems;
    }

    private void recyclerChildren(RecyclerView.Recycler recycler) {
        List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();
        for(int i = 0; i < scrapList.size() -2; i++) {
            RecyclerView.ViewHolder viewHolder = scrapList.get(i);
            removeView(viewHolder.itemView);
            recycler.recycleView(viewHolder.itemView);
        }
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        mRecycler = recycler;
        mState = state;
        detachAndScrapAttachedViews(recycler);
        int tempDx = dx;
        mXoffset -= dx;
        if(mXoffset > 0) {
            mXoffset = 0;
            tempDx = 0;
        }
        if(mXoffset < -mTotalWidth) {
            mXoffset = -mTotalWidth;
            tempDx = 0;
        }
        layoutChildView(recycler, state);
        return tempDx;
    }

    @Override
    public void onScrollStateChanged(int state) {
        if(state == RecyclerView.SCROLL_STATE_IDLE) {
            int position = findClosestPosition();
            smoothScrollToPosition(position);
        }
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        //super.smoothScrollToPosition(recyclerView, state, position);
        smoothScrollToPosition(position);
    }

    private void smoothScrollToPosition(final int position) {
        if(position < 0 || position > getItemCount()) {
            throw new IllegalArgumentException(String.format("current position is %s, but total itemCount is %s", position, getItemCount()));
        }
        stopSmoothAnimation();
        View childView = mRecycler.getViewForPosition(position);
        if(childView != null) {
            int offset = childView.getLeft() - startWidthSpace;
            int prePosition = mXoffset;
            int targetPosition = mXoffset - offset;
            if(offset == 0) return;
            animator = ValueAnimator.ofInt(prePosition, targetPosition).setDuration(mFixingAnimationDuration);
            ((ValueAnimator) animator).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                private int mLastScrollOffset = 0;

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    int currentValue = (int) animation.getAnimatedValue();

                    if(mLastScrollOffset != 0) {
                        int dx = currentValue - mLastScrollOffset;
                        if(dx == 0) return;
                        mXoffset += dx;
                        requestLayout();
                    }
                    mLastScrollOffset = currentValue;
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                private boolean isCanceled;
                @Override
                public void onAnimationCancel(Animator animation) {
                    isCanceled = true;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!isCanceled) {
                        if (onItemSelectedListener != null) {
                            onItemSelectedListener.onSelected(position);
                            stopSmoothAnimation();
                        }
                    }
                }
            });
            animator.start();
        }else{
            Log.e(TAG, String.format("smoothScrollToPosition position %s childview is null", position));
        }
    }

    private void stopSmoothAnimation() {
        if(animator != null && animator.isRunning()) {
            animator.cancel();
        }
    }

    private int findClosestPosition() {
        int position = (int) (Math.abs(mXoffset) / childViewWidth);
        float remainder = Math.abs(mXoffset) % childViewWidth;
        if(remainder > childViewWidth / 2) {
            position = position + 1;
        }
        return position;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.onItemSelectedListener = listener;
    }

    public interface OnItemSelectedListener {
        void onSelected(int position);
    }
}