package com.feng.digitacoin.ui.me

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.feng.digitacoin.R
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

        val years : Array<String> = Array()
        val month : Array<Array<String>>
        val day : Array<Array<Array<String>>>

        list.forEach { val time = it.split("-")
            years[it.indices.first] = time[0]
        }


        val adapter = CardLayoutManager(LinearLayout.VERTICAL)
        view.dayRecycleView.layoutManager = adapter
        adapter.setOnItemSelectedListener { position -> Log.e(TAG, "selected position is : $position") }
        var source = arrayListOf<String>()
        for (i in 2000..2018) {
            source.add(i.toString())
        }
        view.dayRecycleView.adapter = WalletRecyclerViewAdapter(source)


        val adapter1 = CardLayoutManager(LinearLayout.VERTICAL)
        view.monthRecycleView.layoutManager = adapter1
        adapter1.setOnItemSelectedListener { position -> Log.e(TAG, "selected position is : $position") }
        var source2 = arrayListOf<String>()
        for (i in 1..12) {
            source2.add(i.toString())
        }
        view.monthRecycleView.adapter = WalletRecyclerViewAdapter(source2)


        val adapter2 = CardLayoutManager(LinearLayout.VERTICAL)
        view.yearRecycleView.layoutManager = adapter2
        adapter2.setOnItemSelectedListener { position -> Log.e(TAG, "selected position is : $position") }
        var source1 = arrayListOf<String>()
        for (i in 1..31) {
            source1.add(i.toString())
        }
        view.yearRecycleView.adapter = WalletRecyclerViewAdapter(source1)
    }
}