package com.imvence.myapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imvence.myapp.R

class MsgAdapter(private val datas: List<MsgItem>, private val context: Context) :RecyclerView.Adapter<MsgAdapter.InnerHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MsgAdapter.InnerHolder {
        var itemView: View = LayoutInflater.from(context).inflate(R.layout.msg_item, p0, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int = datas.size

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val msg_avatar: ImageView = itemView.findViewById(R.id.msg_avatar)
        val msg_nickname: TextView = itemView.findViewById(R.id.msg_nickname)
        val msg_day: TextView = itemView.findViewById(R.id.msg_day)
        val msg_content: TextView = itemView.findViewById(R.id.msg_content)
    }

    override fun onBindViewHolder(p0: MsgAdapter.InnerHolder, p1: Int) {
        p0.msg_nickname.text = datas[p1].nickname
        p0.msg_day.text = datas[p1].day
        p0.msg_content.text = datas[p1].content
    }

}