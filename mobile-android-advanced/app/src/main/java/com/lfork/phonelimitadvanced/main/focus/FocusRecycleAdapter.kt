package com.lfork.phonelimitadvanced.main.focus

import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.lfork.phonelimitadvanced.R
import com.lfork.phonelimitadvanced.data.appinfo.AppInfo
import com.lfork.phonelimitadvanced.utils.startActivity
import com.lfork.phonelimitadvanced.whitename.WhiteNameEditActivity
import kotlinx.android.synthetic.main.main_focus_recycle_item.view.*


/**
 *
 * Created by 98620 on 2018/11/8.
 */
class FocusRecycleAdapter : RecyclerView.Adapter<FocusRecycleAdapter.NormalHolder>() {

    private val tailItem = "sad"

    private val items = ArrayList<AppInfo>(0);

    private val TYPE_NORMAL = 0
    private val TYPE_TAIL = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalHolder =
        when (viewType) {

            TYPE_TAIL -> {

                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_focus_edit_recycle_item, parent, false)
                val holder = NormalHolder(view)
                holder
            }

            //TYPE_NORMAL
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_focus_recycle_item, parent, false)
                val holder = NormalHolder(view)
                holder
            }
        }

    override fun getItemViewType(position: Int): Int {
        return if (position < itemCount - 1) {
            TYPE_NORMAL
        } else {
            TYPE_TAIL
        }
    }

    override fun getItemCount(): Int {
        //normal + tail
        return items.size + 1
    }

    override fun onBindViewHolder(holder: NormalHolder, p1: Int) {

        if (p1 != itemCount - 1) {
            holder.textView.text = items[p1].name
            holder.textView.setOnClickListener {
                deleteItem(holder.adapterPosition)
            }
            holder.imageView.setImageDrawable(items[p1].icon)
        } else {
            holder.item.setOnClickListener {
               //跳转到编辑界面
                it.context.startActivity<WhiteNameEditActivity>()
            }
        }
    }

    @Synchronized
    private fun deleteItem(position: Int) {
        //防止动画未消失再次点击到View ，此时Index为-1
        //ViewHolder是会复用的
        //减一是剪掉tail item
        if (position !in (0 until items.size - 1)) {
            return
        }
        items.remove(items[position])
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size - position)
    }

    inner class NormalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView
        val textView: TextView = itemView.text_recycle
        val imageView: ImageView = itemView.icon
    }

    fun setItems(itemList: ArrayList<AppInfo>) {
        items.clear()
        items.addAll(itemList)
    }

}