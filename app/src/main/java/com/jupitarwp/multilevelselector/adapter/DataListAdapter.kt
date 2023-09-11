package com.jupitarwp.multilevelselector.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jupitarwp.multilevelselector.R
import com.jupitarwp.multilevelselector.bean.BaseBean
import com.jupitarwp.multilevelselector.widget.CustomRecyclerView

/**
 * 底部数据适配器
 */
class DataListAdapter : RecyclerView.Adapter<DataListAdapter.BaseViewHolder>() {

    public var list = ArrayList< BaseBean>()
    public var selectedPosition = 0
    public var iDataClicklistsner: IDataClicklistsner? = null
    public var customRecyclerView: CustomRecyclerView? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_list, parent,
            false)
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.name.text = list[position].name
        if (selectedPosition == position) {
            holder.name.setTextColor(Color.RED)
            holder.img_selected.visibility = View.VISIBLE
        } else {
            holder.name.setTextColor(Color.BLACK)
            holder.img_selected.visibility = View.GONE
        }

        holder.ll_root.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
            iDataClicklistsner?.click(position, list.get(position))
        }

    }

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val ll_root = itemView.findViewById<LinearLayout>(R.id.ll_root)
        val img_selected = itemView.findViewById<View>(R.id.img_selected)
    }

    interface IDataClicklistsner {
        fun click(position: Int, bean: BaseBean)
    }


}