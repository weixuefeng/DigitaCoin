package com.feng.digitacoin.ui.me.adapter

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Rect
import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup

/**
 * @time: 2018/11/10--5:19 PM
 * @description
 * @version $Rev$
 * @author weixuefeng@lubangame.com
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
class WalletCartLayoutManager(private val context: Context?) : RecyclerView.LayoutManager(){

    private var mXoffset = 0

    // 所有view移动的宽度范围
    private var mTotalWidth = 0

    // 子view测量宽度
    private var childViewMeasureWidth = 0
    // 子view测量宽度
    private var childViewMeasureHeight = 0

    // 子view占据宽度 = 子view测量宽度 + 距离下一个view 的间距 childViewWidth + widthSpace
    private var childViewWidth = 0

    // 开始view距离左右两边的空间
    private var startWidthSpace = 0

    // 开始view距离上下的空间
    private var startHeightSpace = 0

    // 两个view之间的间距
    private var widthSpace = 0

    // 自动选中的动画时间
    private val mFixingAnimationDuration = 200L

    // 记录所有view 的偏移信息
    private val itemFrames = SparseArrayCompat<Rect>()

    // 第一条可见的item
    private var mFirstVisibleItemPos = 0

    // recycler
    private var mRecycler : RecyclerView.Recycler? = null


    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        mRecycler = recycler
        if(state!!.itemCount == 0) {
            removeAndRecycleAllViews(recycler)
            return
        }
        detachAndScrapAttachedViews(recycler)
        val childView = recycler!!.getViewForPosition(0)
        measureChildWithMargins(childView, 0, 0)
        // 每个子view的实际宽度
        childViewMeasureWidth = getDecoratedMeasuredWidth(childView)
        childViewMeasureHeight = getDecoratedMeasuredHeight(childView)

        startWidthSpace = (width - childViewMeasureWidth) / 2
        startHeightSpace = (height - childViewMeasureHeight) / 2

        widthSpace = startWidthSpace / 2

        childViewWidth = childViewMeasureWidth + widthSpace
        mTotalWidth = (itemCount - 1) * childViewWidth

        layoutChildView(recycler, state)
    }

    private fun layoutChildView(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        val needLayoutItems = getNeedLayoutItems(recycler)
        if(needLayoutItems.size() == 0 || state.itemCount == 0) {
            removeAndRecycleAllViews(recycler)
            return
        }
        onLayout(recycler, needLayoutItems)
        recycleChildren(recycler)
    }

    private fun onLayout(recycler: RecyclerView.Recycler?, needLayoutItems: SparseArray<View>) {

        for (i in 0 until needLayoutItems.size()) {
            // !! 非空断言 if false throw exception.   ?. 安全调用 if true else null.
            //val view = recycler!!.getViewForPosition(position)
            // 添加 view
            val position = needLayoutItems.keyAt(i)
            val view = recycler!!.getViewForPosition(position)
            addView(view)
            // measure view
            measureChildWithMargins(view, 0, 0)
            val left = startWidthSpace + position * childViewWidth + mXoffset
            val right = left + childViewMeasureWidth
            // layout view
            layoutDecoratedWithMargins(view, left, startHeightSpace, right, startHeightSpace + childViewMeasureHeight)
            val distance = if(left > startWidthSpace) {
                (left - startWidthSpace).toFloat()
            } else {
                (startWidthSpace - left).toFloat()
            }
            val scale = 1 - (0.5 * distance).toFloat() / childViewWidth
            view.scaleY = scale
            view.alpha = scale
        }
    }

    private fun getNeedLayoutItems(recycler: RecyclerView.Recycler?): SparseArray<View>{
        val needListViews = SparseArray<View>()
        var currentDistance = 0
        for(position in 0 until itemCount) {
            currentDistance = position * childViewWidth + startWidthSpace * 4 + mXoffset
            if(currentDistance >= 0) {
                mFirstVisibleItemPos = position
                break
            }
        }
        var endIndex = mFirstVisibleItemPos + 2
        if(endIndex > itemCount) endIndex = itemCount
        for(position in mFirstVisibleItemPos .. endIndex) {
            if(position >= itemCount) break
            needListViews.put(position, recycler!!.getViewForPosition(position))
        }
        return needListViews
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        detachAndScrapAttachedViews(recycler)
        var calDx: Int
        mXoffset -= dx
        calDx = dx
        if(mXoffset > 0) {
            mXoffset = 0
            calDx = 0
        }
        if(mXoffset < -mTotalWidth){
            mXoffset = - mTotalWidth
            calDx = 0
        }
        layoutChildView(recycler, state!!)
        return calDx
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        when(state){
            // 滑动停止
            RecyclerView.SCROLL_STATE_IDLE -> smoothScrollToPosition(findClosestPosition())
            // 手指拖动
            RecyclerView.SCROLL_STATE_DRAGGING -> Log.e("state", "Dragging")
            // 滑动中
            RecyclerView.SCROLL_STATE_SETTLING -> Log.e("state", "Settling")
        }
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
        super.smoothScrollToPosition(recyclerView, state, position)
        smoothScrollToPosition(position)
    }

    private fun smoothScrollToPosition(position: Int) {
        if (position > -1 && position < itemCount) {
            val viewForPosition = mRecycler!!.getViewForPosition(position)
            if(viewForPosition != null) {
                val dx = viewForPosition.left - startWidthSpace
                val prePosition = mXoffset
                val targetPosition = mXoffset - dx
                val animator = ValueAnimator.ofInt(prePosition, targetPosition)
                var mLastScrollOffset = 0
                animator.duration = mFixingAnimationDuration
                animator.addUpdateListener {
                    animation ->
                    run {
                        val currentValue = animation.animatedValue as Int
                        if (mLastScrollOffset != 0) {
                            val offset = currentValue - mLastScrollOffset
                            mXoffset += offset
                            requestLayout()
                        }
                        mLastScrollOffset = currentValue
                    }
                }
                animator.start()
            }else{
                Log.e(TAG, "position $position view is null")
            }
        }
    }

    private fun findClosestPosition() : Int{
        var pos = Math.abs(mXoffset) / childViewWidth
        val remainder = Math.abs(mXoffset) % childViewWidth
        if(remainder > childViewWidth / 2) pos += 1
        return pos
    }

    private fun recycleChildren(recycler: RecyclerView.Recycler?) {
        val scrapList = recycler!!.scrapList
        for (i in 0 until scrapList.size - 2) {
            val holder = scrapList[i]
            removeView(holder.itemView)
            recycler.recycleView(holder.itemView)
        }
    }

    companion object {
        const val TAG = "WalletCartLayoutManager"
    }
}