package com.feng.digitacoin.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.feng.digitacoin.R
import com.feng.digitacoin.ui.adapter.MainViewPagerAdapter
import com.feng.digitacoin.ui.me.DappFragment
import com.feng.digitacoin.ui.me.HomeFragment
import com.feng.digitacoin.ui.me.MeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    val TAG = "MainActivity"

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        when(position){
            1 -> navigation.selectedItemId = R.id.tab_dapp
            2 -> navigation.selectedItemId = R.id.tab_me
            0 -> navigation.selectedItemId = R.id.tab_home
        }

    }

    private val mOnNavigationItemReselectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
        when(item.itemId) {
            R.id.tab_home -> {
                Log.d(TAG,"fragment_home")
                viewpager.setCurrentItem(0, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_dapp-> {
                Log.d(TAG,"dapp")
                viewpager.setCurrentItem(1, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_me -> {
                Log.d(TAG, "me")
                viewpager.setCurrentItem(2, true)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemReselectedListener)

        val homeFragment = HomeFragment()
        val meFragment = MeFragment()
        val dappFragment = DappFragment()
        val homePair = Pair<Int, BaseFragment>(1, homeFragment)
        val dappPair = Pair(2, dappFragment)
        val mePair = Pair(3, meFragment)
        val list = listOf(homePair, dappPair, mePair)
        viewpager.adapter = MainViewPagerAdapter(supportFragmentManager, list)
        viewpager.addOnPageChangeListener(this)
        viewpager.offscreenPageLimit = 3
    }
}
