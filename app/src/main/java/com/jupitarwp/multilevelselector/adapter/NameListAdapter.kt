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

/**
 * 顶部适配器
 */
class NameListAdapter : RecyclerView.Adapter<NameListAdapter.BaseViewHolder>() {

    public var list = ArrayList<BaseBean>()
    public var selectedPosition = 0
    public var iNameClicklistsner: INameClicklistener?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_name_list, parent, false)
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.name.text = list[position].name

        if (selectedPosition == position) {
            holder.view_bottom_line.visibility = View.VISIBLE
            holder.name.setTextColor(Color.RED)
        } else {
            holder.view_bottom_line.visibility = View.GONE
            holder.name.setTextColor(Color.BLACK)
        }
        holder.ll_root.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
            iNameClicklistsner?.click(position, list.get(position))
        }
    }


    fun updateBean(bean:BaseBean) {
        list[selectedPosition] = bean
    }

    fun updateLastPosition(bean:  BaseBean) {
        selectedPosition = list.size - 1
        list[list.size - 1] = bean
    }


    fun getSelectedBean():  BaseBean {
        return list[selectedPosition]
    }

    fun removeBeans() {
        list.removeAll(list.subList(selectedPosition + 1, list.size))

    }

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ll_root = itemView.findViewById<LinearLayout>(R.id.ll_root)
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val view_bottom_line = itemView.findViewById<View>(R.id.view_bottom_line)
    }

    interface INameClicklistener{
        fun click(position: Int, name:  BaseBean)
    }

}