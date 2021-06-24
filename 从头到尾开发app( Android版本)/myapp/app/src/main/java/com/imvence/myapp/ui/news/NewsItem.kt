package com.imvence.myapp.ui.news

data class NewsItem(
    val avatar:String,
    val nickname:String,
    val content:String,
    val thumbs:MutableMap<Int, String>,
    val addtime:String
)
