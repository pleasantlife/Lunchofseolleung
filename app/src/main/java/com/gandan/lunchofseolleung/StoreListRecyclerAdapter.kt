package com.gandan.lunchofseolleung

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView



class StoreListRecyclerAdapter(var context : Context, var list : ArrayList<DataModels.StoreInfo>) : RecyclerView.Adapter<StoreListRecyclerAdapter.StoreListRecyclerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreListRecyclerHolder {
        return StoreListRecyclerHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_store_list, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StoreListRecyclerHolder, position: Int) {
        holder.storeItemTitleTxtView.text = list[position].storeName
        holder.storeItemMenuTxtView.text = list[position].menu.replace("/", "\n")
        holder.storeItemLinear.setOnClickListener {
            holder.bind(position)
        }
    }

    inner class StoreListRecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var storeItemLinear = itemView.findViewById<LinearLayout>(R.id.storeItemLinear)!!
        var storeItemImageView = itemView.findViewById<ImageView>(R.id.storeItemImageView)!!
        var storeItemTitleTxtView = itemView.findViewById<TextView>(R.id.storeItemTitleTxtView)!!
        var storeItemMenuTxtView = itemView.findViewById<TextView>(R.id.storeItemMenuTxtView)!!


        fun bind(position: kotlin.Int) {

            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(context as MainActivity, storeItemImageView, storeItemImageView.transitionName)
            context.startActivity(Intent(context, StoreDetailActivity::class.java).putExtra("storeName", list[position].storeName), option.toBundle())
        }
    }
}