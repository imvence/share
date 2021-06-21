package com.imvence.myapp.ui.friends

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imvence.myapp.R

class FriendsAdapter(private val friendsData:FriendsData, private val context:Context):RecyclerView.Adapter<FriendsAdapter.InnerHolder>() {
    private var fnames = mutableMapOf<Int, String>()
    private var vtypeIndex = mutableMapOf<Int, Int>()

    open class InnerHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        //val friendName:TextView = itemView.findViewById(R.id.friendName)
    }

    class EmptyHolder(itemView: View):InnerHolder(itemView){

    }

    class GroupHolder(itemView: View):InnerHolder(itemView){
        val groupName:TextView = itemView.findViewById(R.id.groupName)
    }

    class FriendHolder(itemView: View):InnerHolder(itemView){
        val friendName:TextView = itemView.findViewById(R.id.friendName)
    }

    override fun getItemViewType(position:Int):Int{
        return vtypeIndex[position]!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        //val view:View = LayoutInflater.from(context).inflate(R.layout.friends_item, parent, false)

        //return InnerHolder(view)

        return if(viewType==0){
            var view:View = View(context)

            return EmptyHolder(view)
        }else if(viewType==1){
            val view:View = LayoutInflater.from(context).inflate(R.layout.group_item, parent, false)

            return GroupHolder(view)
        }else{
            val view:View = LayoutInflater.from(context).inflate(R.layout.friends_item, parent, false)

            return FriendHolder(view)
        }
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        //holder.friendName.text = fnames[position]
        when(vtypeIndex[position]){
            0->{}
            1->{
                (holder as GroupHolder).groupName.text = fnames[position]
            }
            2->{
                (holder as FriendHolder).friendName.text = fnames[position]
            }
        }
    }

    override fun getItemCount(): Int {
        var nums = 0

        vtypeIndex[nums] = 0

        for(group in friendsData.datas){
            nums++
            fnames[nums] = group.groupIndex
            vtypeIndex[nums] = 1

            for(friend in group.friends){
                nums++
                fnames[nums] = friend.name
                vtypeIndex[nums] = 2
            }
        }

        return nums+1
    }
}