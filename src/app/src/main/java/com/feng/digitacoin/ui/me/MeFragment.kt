package com.feng.digitacoin.ui.me

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.feng.digitacoin.R
import com.feng.digitacoin.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_me.view.*

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/9--12:26 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
class MeFragment: BaseFragment(), CompoundButton.OnCheckedChangeListener {
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if(isChecked) {
            Log.e(TAG, "Checed")
        }else{
            Log.e(TAG, "Unchecked")
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_me, container, false)
        view.securitySwitch.setOnCheckedChangeListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "OnViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }


}