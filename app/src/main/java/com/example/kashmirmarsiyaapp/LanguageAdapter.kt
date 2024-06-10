package com.example.kashmirmarsiyaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LanguageAdapter(
    private var mList: List<LanguageData>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val logo: ImageView = itemView.findViewById(R.id.imageView21)
        val titleTv: TextView = itemView.findViewById(R.id.textView17)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    fun setFilteredList(mList: List<LanguageData>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val item = mList[position]
        holder.titleTv.text = item.title
        if (isValidResourceId(holder.itemView, item.logo)) {
            holder.logo.setImageResource(item.logo)
        } else {
            holder.logo.setImageResource(R.drawable.marsiyloganother) // set a default image
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private fun isValidResourceId(view: View, resId: Int): Boolean {
        return try {
            view.resources.getResourceName(resId)
            true
        } catch (e: Exception) {
            false
        }
    }
}
