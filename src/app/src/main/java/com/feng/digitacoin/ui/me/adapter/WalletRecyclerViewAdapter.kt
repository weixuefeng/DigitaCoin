package com.feng.digitacoin.ui.me.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.feng.digitacoin.R
import kotlinx.android.synthetic.main.item_wallet_card.view.*

/**
 * @time: 2018/11/10--4:17 PM
 * @description
 * @version $Rev$
 * @author weixuefeng@lubangame.com
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
class WalletRecyclerViewAdapter(private val dataSet: List<String>) : RecyclerView.Adapter<WalletRecyclerViewAdapter.ViewHolder>() {
    var index = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallet_card, parent, false)
        index ++
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }


    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

        fun bind(string: String) {
            v.text.text = string
        }
    }

    companion object {
        const val TAG = "RecyclerViewAdapter"
    }
}
