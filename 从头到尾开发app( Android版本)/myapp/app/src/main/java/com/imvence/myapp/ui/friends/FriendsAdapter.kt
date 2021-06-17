package com.imvence.myapp.ui.friends

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imvence.myapp.R

class FriendsAdapter(private val friendsData:FriendsData, private val context:Context):RecyclerView.Adapter<FriendsAdapter.InnerHolder>() {
    private var fnames = mutableMapOf<Int, String>()

    open class InnerHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val friendName:TextView = itemView.findViewById(R.id.friendName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.friends_item, parent, false)

        return InnerHolder(view)
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.friendName.text = fnames[position]
    }

    override fun getItemCount(): Int {
        var nums = 0

        for(group in friendsData.datas){
            for(friend in group.friends){
                fnames[nums] = friend.name
                nums++
            }
        }

        return nums
    }
}