package com.feng.digitacoin.ui.me

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.feng.digitacoin.R
import com.feng.digitacoin.entity.DateBean
import com.feng.digitacoin.ui.BaseFragment
import com.feng.digitacoin.ui.me.adapter.CardLayoutManager
import com.feng.digitacoin.ui.me.adapter.WalletRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/9--12:26 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
class HomeFragment: BaseFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "OnCreateView")
        return LayoutInflater.from(context).inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "OnViewCreated")
        super.onViewCreated(view, savedInstanceState)

        val list = listOf<String>(
                "2000-1-1",
                "2000-1-2",
                "2000-1-3",
                "2000-1-4",
                "2000-1-5",
                "2000-1-6",
                "2000-1-7",
                "2000-1-8",
                "2000-1-9",
                "2000-1-10",
                "2000-2-10",
                "2001-2-1",
                "2001-3-2",
                "2001-3-3",
                "2001-3-4",
                "2001-3-5",
                "2001-3-6",
                "2001-3-7",
                "2002-5-8",
                "2002-5-10",
                "2002-6-1",
                "2002-6-5",
                "2002-7-1",
                "2002-9-14"
        )

        val dateBean = DateBean(list.toTypedArray())
        val yearDate = dateBean.yearSet
        val monthDate = dateBean.monthMap
        val dayDate = dateBean.dayMap

        var defaultYear = yearDate[0]
        var defaultMonth = monthDate[defaultYear]
        Log.e(TAG, "default year:" + defaultYear)
        Log.e(TAG, "default month:" + defaultMonth!![0])
        var key = defaultYear + "-" + defaultMonth[0]
        Log.e(TAG, "key:" + key)
        var defaultDay = dayDate[key]

        val yearLayoutManager = CardLayoutManager(LinearLayout.VERTICAL)
        val monthLayoutManager = CardLayoutManager(LinearLayout.VERTICAL)
        val dayLayoutManager = CardLayoutManager(LinearLayout.VERTICAL)

        val yearAdapter = WalletRecyclerViewAdapter(yearDate)
        val monthAdapter = WalletRecyclerViewAdapter(defaultMonth)
        val dayAdapter = WalletRecyclerViewAdapter(defaultDay)

        view.yearRecycleView.layoutManager = yearLayoutManager
        view.monthRecycleView.layoutManager = monthLayoutManager
        view.dayRecycleView.layoutManager = dayLayoutManager

        view.yearRecycleView.adapter = yearAdapter
        view.monthRecycleView.adapter = monthAdapter
        view.dayRecycleView.adapter = dayAdapter

        var currentYear = ""
        var currentMonth = ""

        yearLayoutManager.setOnItemSelectedListener { position -> Log.e(TAG, "selected position is : $position") }
        yearLayoutManager.setOnItemSelectedListener {
            currentYear = yearDate[it]
            defaultMonth = monthDate[currentYear]
            monthAdapter.setDate(defaultMonth)
            monthLayoutManager.smoothScrollToPosition(null, null,0)
            currentMonth = defaultMonth!![0]
            defaultDay = dayDate["$currentYear-$currentMonth"]
            dayAdapter.setDate(defaultDay)
            dayLayoutManager.smoothScrollToPosition(null, null, 0)
        }

        monthLayoutManager.setOnItemSelectedListener { position -> Log.e(TAG, "selected position is : $position") }
        monthLayoutManager.setOnItemSelectedListener {
            currentMonth = defaultMonth!![it]
            defaultDay = dayDate["$currentYear-$currentMonth"]
            dayAdapter.setDate(defaultDay)
            dayLayoutManager.smoothScrollToPosition(null, null, 0)
        }

        dayLayoutManager.setOnItemSelectedListener { position -> Log.e(TAG, "selected position is : $position") }

    }
}