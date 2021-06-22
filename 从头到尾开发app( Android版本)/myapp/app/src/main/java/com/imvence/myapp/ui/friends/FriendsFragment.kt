package com.imvence.myapp.ui.friends

import android.os.Bundle
import android.os.Handler
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
    var dataAdapter:RecyclerView.Adapter<*>?=null
    lateinit var root:View
    lateinit var quickIndexView:FriendsQuickIndex

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_friends, container, false)

        var friendsContent:RecyclerView = root.findViewById(R.id.friendsContent)
        friendsContent.layoutManager = LinearLayoutManager(context)

        quickIndexView = root.findViewById<FriendsQuickIndex>(R.id.quickIndexView)
        quickIndexView.setOnIndexChangeListener(object: FriendsQuickIndex.OnIndexChangeListener{
            override fun onIndexChange(group: String?) {
                var count = 0

                for(i in 0 until friendsResult!!.datas.size){
                    if(group == friendsResult!!.datas[i].groupIndex){
                        (friendsContent.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(count+1, 0)

                        break
                    }else{
                        count+= 1+friendsResult!!.datas[i].friends.size
                    }
                }
            }
        })

        this.initRequest()
        dataAdapter = FriendsAdapter(friendsResult!!, root.context)
        friendsContent.adapter = dataAdapter

        return root
    }

    private fun initRequest(){
        val friendsJson =
            "{\"datas\":[{\"groupIndex\":\"A\",\"friends\":[{\"id\":1,\"name\":\"阿源\"}]},{\"groupIndex\":\"B\",\"friends\":[{\"id\":2,\"name\":\"白小姐\"}]},{\"groupIndex\":\"C\",\"friends\":[{\"id\":3,\"name\":\"陈先生\"}]},{\"groupIndex\":\"D\",\"friends\":[{\"id\":4,\"name\":\"董小姐\"}]},{\"groupIndex\":\"E\",\"friends\":[{\"id\":5,\"name\":\"eason\"},{\"id\":6,\"name\":\"Easter\"}]},{\"groupIndex\":\"F\",\"friends\":[{\"id\":7,\"name\":\"冯先生\"},{\"id\":8,\"name\":\"冯小姐\"}]},{\"groupIndex\":\"G\",\"friends\":[{\"id\":9,\"name\":\"高先生\"},{\"id\":10,\"name\":\"高小姐\"}]},{\"groupIndex\":\"H\",\"friends\":[{\"id\":11,\"name\":\"洪先生\"},{\"id\":12,\"name\":\"洪小姐\"}]},{\"groupIndex\":\"I\",\"friends\":[{\"id\":13,\"name\":\"Icy\"}]},{\"groupIndex\":\"J\",\"friends\":[{\"id\":14,\"name\":\"Jack\"},{\"id\":15,\"name\":\"Jackie\"}]},{\"groupIndex\":\"K\",\"friends\":[{\"id\":16,\"name\":\"King\"}]},{\"groupIndex\":\"L\",\"friends\":[{\"id\":17,\"name\":\"Linux\"},{\"id\":18,\"name\":\"梁先生\"}]},{\"groupIndex\":\"M\",\"friends\":[{\"id\":19,\"name\":\"马天才\"},{\"id\":20,\"name\":\"马小姐\"}]},{\"groupIndex\":\"N\",\"friends\":[{\"id\":21,\"name\":\"Nothing\"}]},{\"groupIndex\":\"O\",\"friends\":[{\"id\":22,\"name\":\"Owen\"},{\"id\":23,\"name\":\"欧文\"}]},{\"groupIndex\":\"P\",\"friends\":[{\"id\":24,\"name\":\"朋友A\"}]},{\"groupIndex\":\"Q\",\"friends\":[{\"id\":25,\"name\":\"七小姐\"}]},{\"groupIndex\":\"R\",\"friends\":[{\"id\":27,\"name\":\"容嚒嚒\"}]},{\"groupIndex\":\"S\",\"friends\":[{\"id\":28,\"name\":\"森林公园\"}]},{\"groupIndex\":\"T\",\"friends\":[{\"id\":29,\"name\":\"桃花\"},{\"id\":30,\"name\":\"彤彤\"}]},{\"groupIndex\":\"U\",\"friends\":[{\"id\":31,\"name\":\"UU\"}]},{\"groupIndex\":\"V\",\"friends\":[{\"id\":32,\"name\":\"Vence\"}]},{\"groupIndex\":\"W\",\"friends\":[{\"id\":33,\"name\":\"武剧\"}]},{\"groupIndex\":\"X\",\"friends\":[{\"id\":34,\"name\":\"Xman\"}]},{\"groupIndex\":\"Y\",\"friends\":[{\"id\":35,\"name\":\"YYan\"}]},{\"groupIndex\":\"Z\",\"friends\":[{\"id\":36,\"name\":\"ZZ\"}]}]}"

        friendsResult = Gson().fromJson(friendsJson, FriendsData::class.java)

        var groups = mutableMapOf<Int, String>()
        for(i in 0 until friendsResult!!.datas.size){
            groups[i] = friendsResult!!.datas[i].groupIndex
        }

        //val quickIndexView = root.findViewById<FriendsQuickIndex>(R.id.quickIndexView)

        Handler().postDelayed({
            quickIndexView.redraw(groups)
        },50)

        dataAdapter?.notifyDataSetChanged()

        //quickIndexView.redraw(data!!)
    }
}