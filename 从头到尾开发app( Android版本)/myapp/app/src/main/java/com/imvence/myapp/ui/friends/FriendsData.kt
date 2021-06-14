package com.imvence.myapp.ui.friends

data class FriendsData(
    val datas:MutableList<GroupData>
)

data class GroupData(
    val groupIndex:String,
    val friends:MutableList<FriendsItemData>
)

data class FriendsItemData(
    val id:Int,
    val name:String
)