package com.imvence.myapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.imvence.myapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val msgList: MutableList<MsgItem> = ArrayList() //列表内容容器
    private var msgAdapter: RecyclerView.Adapter<*>? = null
    private lateinit var homeRefresh:SwipeRefreshLayout
    private var page = 1

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        //val textView: TextView = root.findViewById(R.id.text_home)
        //homeViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        //})

        //val contentView: ConstraintLayout = root.findViewById(R.id.HomeFragment)

        //contentView.addView(inflater.inflate(R.layout.msg_item, container, false))

        val messageContent: RecyclerView = root.findViewById(R.id.messageContent)
        messageContent.layoutManager = LinearLayoutManager(root.context)

        msgAdapter = MsgAdapter(msgList, root.context)

        messageContent.adapter = msgAdapter

        homeRefresh = root.findViewById(R.id.msgRefresh)
        homeRefresh.setOnRefreshListener {
            this.page = 1
            this.msgList.clear()
            this.initRequest()

        }

        messageContent.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(!messageContent.canScrollVertically(0)){
                    page += 1
                    initRequest()
                }
            }
        })

        this.initRequest()  //假设从这里开始加载数据

        return root
    }

    private fun initRequest(){
        var start = (page-1)*10
        var end   = page*10

        for (i in start..end){
            var rand = (0..10).random()

            msgList.add(i, MsgItem(
                    i.toString(),
                    "关注程序阿源，带你从头到尾开发APP",
                    "$i 小时前",
                    "程序阿源$i",
                    "https://asm.api.qt750.com/Uploads/image/tt/$rand.jpg"
            ))
        }

        msgAdapter?.notifyDataSetChanged()

        if(homeRefresh.isRefreshing){
            homeRefresh.isRefreshing = false
        }
    }
}