package com.imvence.myapp.utils

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.idtk.ikninephotoview.IKNinePhotoViewAdapter
import com.idtk.ikninephotoview.IKNinePhotoViewHolder
import com.imvence.myapp.R


class NinePhotoApapter(private val thumbs: MutableMap<Int, String>, private val context: Context): IKNinePhotoViewAdapter<NinePhotoApapter.InnerHolder>() {

    open class InnerHolder(itemView: View) : IKNinePhotoViewHolder(itemView) {
        val mImageView: ImageView = itemView.findViewById(R.id.nine_pic)
    }

    override fun getItemCount():Int{
        return thumbs.size
    }

    override fun createView(parent: ViewGroup?): InnerHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.ninephoto_item, parent, false)

        return InnerHolder(view)
    }

    override fun displayView(holder: InnerHolder, position: Int, height: Int) {
        holder.mImageView.layoutParams = LinearLayout.LayoutParams(height, height)
        holder.mImageView.setImageURI(Uri.parse(thumbs[position]))
    }
}