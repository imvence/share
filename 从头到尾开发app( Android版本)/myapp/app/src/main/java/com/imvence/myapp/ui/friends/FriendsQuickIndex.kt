package com.imvence.myapp.ui.friends

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class FriendsQuickIndex(context: Context, attrs: AttributeSet, defStyleAttr:Int): View(context, attrs, defStyleAttr) {
    private var scanvas:Canvas?=null
    private var paint = Paint()
    private var groups = mutableMapOf<Int, String>()
    private var cellWidth = 0
    private var cellHeight = 0

    constructor(context: Context, attrs: AttributeSet):this(context,attrs,0){}

    override fun onDraw(canvas: Canvas){
        scanvas = canvas

        for(i in 0 until groups.size){
            val bound = Rect()
            paint.getTextBounds(groups[i], 0, 1, bound)
            val x = (cellWidth-bound.width())/2
            val y = i*cellHeight + (cellWidth+bound.width())/2
            scanvas!!.drawText(groups[i]!!, x.toFloat(), y.toFloat(), paint)
        }
    }

    init{
        paint.isAntiAlias = true
        paint.textSize = 26F
    }

    fun redraw(datas:MutableMap<Int, String>){
        groups = datas
        cellWidth = measuredWidth
        cellHeight = measuredHeight/datas.size
        draw(scanvas)
    }
}