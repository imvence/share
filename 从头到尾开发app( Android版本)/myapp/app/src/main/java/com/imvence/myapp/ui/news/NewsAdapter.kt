package com.imvence.myapp.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.imvence.myapp.R

class NewsAdapter(private val datas:List<NewsItem>, private val context:Context):
    RecyclerView.Adapter<NewsAdapter.InnerHolder>()
{
    private var vtypeIndex = mutableMapOf<Int, Int>()
    private val TYPE_TXT = 1
    private val TYPE_PIC = 2
    private val TYPE_TXT_PIC = 3

    open class InnerHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val news_avatar: SimpleDraweeView = itemView.findViewById(R.id.news_avatar)
        val news_nickname: TextView = itemView.findViewById(R.id.news_nickname)
        val news_addtime:TextView = itemView.findViewById(R.id.news_addtime)
    }

    class TxtHolder(itemView:View):InnerHolder(itemView){
        val news_txt:TextView = itemView.findViewById(R.id.news_txt)
    }

    class PicHolder(itemView:View):InnerHolder(itemView){
        val news_thumb:SimpleDraweeView = itemView.findViewById(R.id.news_thumb)
    }

    class TxtPicHolder(itemView:View):InnerHolder(itemView){
        val news_thumb:SimpleDraweeView = itemView.findViewById(R.id.news_thumb)
        val news_txt:TextView = itemView.findViewById(R.id.news_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {

        return when(viewType){
            TYPE_PIC->{
                val itemView:View = LayoutInflater.from(context).inflate(R.layout.news_pic_item, parent, false)

                return PicHolder(itemView)
            }
            TYPE_TXT_PIC->{
                val itemView:View = LayoutInflater.from(context).inflate(R.layout.news_txtpic_item, parent, false)

                return TxtPicHolder(itemView)
            }
            else->{
                val itemView:View = LayoutInflater.from(context).inflate(R.layout.news_txt_item, parent, false)

                return TxtHolder(itemView)
            }
        }

    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.news_nickname.text = datas[position].nickname
        holder.news_addtime.text = datas[position].addtime
        holder.news_avatar.setImageURI(datas[position].avatar)

        when(vtypeIndex[position]){
            TYPE_PIC->{
                (holder as PicHolder).news_thumb.setImageURI(datas[position].thumbs[0])
            }
            TYPE_TXT_PIC->{
                (holder as TxtPicHolder).news_txt.text = datas[position].content
                (holder as TxtPicHolder).news_thumb.setImageURI(datas[position].thumbs[0])
            }
            TYPE_TXT->{
                (holder as TxtHolder).news_txt.text = datas[position].content
            }
        }


    }

    override fun getItemViewType(position: Int): Int {
        return vtypeIndex[position]!!
    }

    override fun getItemCount(): Int {
        for(i in datas.indices){
            if(datas[i].content.isEmpty()){
                vtypeIndex[i] = TYPE_PIC
            }else{
                if(datas[i].thumbs.isEmpty()){
                    vtypeIndex[i] = TYPE_TXT
                }else{
                    vtypeIndex[i] = TYPE_TXT_PIC
                }
            }
        }

        return datas.size
    }
}