package com.feng.digitacoin.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.feng.digitacoin.ui.BaseFragment


/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/9--12:36 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
class MainViewPagerAdapter(fragmentManager: FragmentManager, list: List<Pair<Int, BaseFragment>>): FragmentPagerAdapter(fragmentManager) {

    private val fragmentPair = list

    override fun getItem(position: Int): Fragment = fragmentPair[position].second

    override fun getCount(): Int = fragmentPair.size

}