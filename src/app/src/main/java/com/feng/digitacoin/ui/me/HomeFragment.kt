package com.feng.digitacoin.ui.me

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.feng.digitacoin.R
import com.feng.digitacoin.entity.DateBean
import com.feng.digitacoin.ui.BaseFragment
import com.feng.digitacoin.ui.components.DateChoosePopuwindow
import com.feng.digitacoin.ui.me.adapter.CardLayoutManager
import com.feng.digitacoin.ui.me.adapter.WalletRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*

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
        view.text.setOnClickListener { showDateChoosePopuwindow() }
    }

    private fun showDateChoosePopuwindow() {
        val list = ArrayList<String>()
        val random = Random()
        for(i in 1..10000) {
            val year = random.nextInt(4) + 2000
            val month = random.nextInt(12)
            val day = random.nextInt(31)
            val time = "$year-$month-$day"
            list.add(time)
        }
        val pop = DateChoosePopuwindow(context, list)
        pop.showAtLocation(view!!.rootView, Gravity.BOTTOM, 0, 0)
    }
}