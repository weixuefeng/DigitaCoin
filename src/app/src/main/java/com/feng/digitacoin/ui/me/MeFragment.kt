package com.feng.digitacoin.ui.me

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.feng.digitacoin.R
import com.feng.digitacoin.extension.inflate
import com.feng.digitacoin.extension.toast
import com.feng.digitacoin.ui.BaseFragment
import com.feng.digitacoin.ui.example.ChooseDateActivity
import kotlinx.android.synthetic.main.fragment_me.view.*

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/9--12:26 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
class MeFragment: BaseFragment() {

    lateinit var rootView: View

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //rootView = LayoutInflater.from(context).inflate(R.layout.fragment_me, container, false)
        rootView = container!!.inflate(R.layout.fragment_me)
        rootView.securitySwitch.setOnCheckedChangeListener { buttonView, isChecked ->   onCheckedChanged(buttonView, isChecked)}
        rootView.dateLinearLayout.setOnClickListener { startChooseDate() }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "OnViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    private fun startChooseDate() {
        val intent = Intent(context, ChooseDateActivity::class.java)
        startActivity(intent)
    }

    private fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if(isChecked) {
            context!!.toast("checked")
        }else{
            context!!.toast("unchecked")
        }
    }
}