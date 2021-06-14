package com.imvence.myapp.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.imvence.myapp.R

class FriendsFragment : Fragment() {
    var friendsResult:FriendsData?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_friends, container, false)

        var friendsContent:RecyclerView = root.findViewById(R.id.friendsContent)
        friendsContent.layoutManager = LinearLayoutManager(context)

        this.initRequest()

        return root
    }

    private fun initRequest(){
        val friendsJson =
            "{\"datas\":[{\"groupIndex\":\"A\",\"friends\":[{\"id\":1,\"name\":\"阿源\"}]},{\"groupIndex\":\"B\",\"friends\":[{\"id\":2,\"name\":\"白小姐\"}]},{\"groupIndex\":\"C\",\"friends\":[{\"id\":3,\"name\":\"陈先生\"}]},{\"groupIndex\":\"D\",\"friends\":[{\"id\":4,\"name\":\"董小姐\"}]},{\"groupIndex\":\"E\",\"friends\":[{\"id\":5,\"name\":\"eason\"},{\"id\":6,\"name\":\"Easter\"}]},{\"groupIndex\":\"F\",\"friends\":[{\"id\":7,\"name\":\"冯先生\"},{\"id\":8,\"name\":\"冯小姐\"}]},{\"groupIndex\":\"G\",\"friends\":[{\"id\":9,\"name\":\"高先生\"},{\"id\":10,\"name\":\"高小姐\"}]},{\"groupIndex\":\"H\",\"friends\":[{\"id\":11,\"name\":\"洪先生\"},{\"id\":12,\"name\":\"洪小姐\"}]},{\"groupIndex\":\"I\",\"friends\":[{\"id\":13,\"name\":\"Icy\"}]},{\"groupIndex\":\"J\",\"friends\":[{\"id\":14,\"name\":\"Jack\"},{\"id\":15,\"name\":\"Jackie\"}]},{\"groupIndex\":\"K\",\"friends\":[{\"id\":16,\"name\":\"King\"}]}]}"

        friendsResult = Gson().fromJson(friendsJson, FriendsData::class.java)

        println(friendsResult!!.datas[0].friends)
    }
}