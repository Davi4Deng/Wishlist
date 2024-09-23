package com.example.wishlist

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val items: MutableList<WishlistItem>) : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    class WishlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemname)
        val itemPrice: TextView = itemView.findViewById(R.id.price)
        val itemUrl: TextView = itemView.findViewById(R.id.URL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
        return WishlistViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val currentItem = items[position]
        holder.itemName.text = currentItem.name
        holder.itemPrice.text = currentItem.price
        holder.itemUrl.text = currentItem.url

        // 为URL TextView添加点击事件，打开网页
        holder.itemUrl.setOnClickListener {
            val url = currentItem.url
            if (url.isNotEmpty()) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                holder.itemView.context.startActivity(browserIntent)
            }
        }

        // 为itemname添加点击事件，删除该条目
        holder.itemName.setOnClickListener {
            // 从列表中移除该条目
            items.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: WishlistItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}
