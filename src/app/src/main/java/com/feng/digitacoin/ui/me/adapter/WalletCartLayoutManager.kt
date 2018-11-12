package com.feng.digitacoin.ui.me.adapter

import android.animation.ValueAnimator
import android.content.ContentValues.TAG
import android.content.Context
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

    val MAX_SHOW_COUNT = 4

    val SCALE_GAP = 0.05F

    var mXoffset = 0

    // 所有view移动的宽度范围
    var mTotalWidth = 0

    // 子view测量宽度
    var childViewMeasureWidth = 0
    // 子view测量宽度
    var childViewMeasureHeight = 0

    // 子view占据宽度 = 子view测量宽度 + 距离下一个view 的间距 childViewWidth + widthSpace
    var childViewWidth = 0

    // 开始view距离左右两边的空间
    var startWidthSpace = 0

    // 开始view距离上下的空间
    var startHeightSpace = 0

    // 两个view之间的间距
    var widthSpace = 0

    // 自动选中的动画时间
    val mFixingAnimationDuration = 200L

    private val TRANS_Y_GAP : Int = (context!!.resources.displayMetrics.density * 20).toInt()

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
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
            Log.e("LayoutChildView", "IS NULL")
            removeAndRecycleAllViews(recycler)
            return
        }
        Log.e("LayoutChildView", "needLayoutItems:${needLayoutItems.size()}")
        onLayout(recycler, needLayoutItems)

        //recycleChildren(recycler)
    }

    private fun onLayout(recycler: RecyclerView.Recycler?, needLayoutItems: SparseArray<View>) {

        for (i in 0 until needLayoutItems.size()) {
            // !! 非空断言 if false throw exception.   ?. 安全调用 if true else null.
            //val view = recycler!!.getViewForPosition(position)
            // 添加 view
            val position = needLayoutItems.keyAt(i)
            val view = recycler!!.getViewForPosition(position)
            Log.e("onlayout", position.toString())
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
        val needLayoutItems = findClosestPosition()
        for(position in needLayoutItems - 2 .. needLayoutItems + 2) {
            if (position < 0) continue
            val childAt = recycler!!.getViewForPosition(position)
            if(childAt != null) {
                needListViews.put(position, childAt)
            }
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
        Log.e(TAG, "SmoothScroll: $position")
        if (position > -1 && position < itemCount) {
            if(getChildAt(position) != null) {
                val dx = getChildAt(position).left - startWidthSpace
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
        for (i in scrapList.indices - 1) {
            val holder = scrapList[i]
            removeView(holder.itemView)
            recycler.recycleView(holder.itemView)
        }
    }

    private fun recycleChildren(recycler: RecyclerView.Recycler?, startIndex: Int, endIndex: Int) {
        if(startIndex == endIndex) return
        if(endIndex > startIndex) {
            for(position in startIndex .. endIndex) {
                if(getChildAt(position) != null){
                    removeAndRecycleViewAt(position, recycler)
                }else{
                    break
                }
            }
        }else {
            for(position in endIndex .. startIndex) {
                if(getChildAt(position) != null){
                    removeAndRecycleViewAt(position, recycler)
                }else{
                    break
                }
            }
        }
    }
}