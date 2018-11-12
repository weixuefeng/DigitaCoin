package com.feng.digitacoin.ui.me

import android.app.ActionBar
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.feng.digitacoin.R
import com.feng.digitacoin.ui.BaseFragment
import com.feng.digitacoin.ui.me.adapter.WalletCartLayoutManager
import com.feng.digitacoin.ui.me.adapter.WalletRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.ArrayList

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
        view.walletRecycleView.layoutManager = WalletCartLayoutManager(context)
        var source = arrayListOf<String>()
        for (i in 1..10000) {
            source.add(i.toString())
        }
        view.walletRecycleView.adapter = WalletRecyclerViewAdapter(source)
    }


}