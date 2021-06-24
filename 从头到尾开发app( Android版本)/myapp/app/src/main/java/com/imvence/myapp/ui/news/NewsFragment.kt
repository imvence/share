package com.imvence.myapp.ui.news

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.imvence.myapp.R

class NewsFragment : Fragment() {
    private var newsList:MutableList<NewsItem> = ArrayList()
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_news, container, false)

        val newsContent:RecyclerView = root.findViewById(R.id.newsContent)
        newsContent.layoutManager = LinearLayoutManager(root.context)

        val newsRefresh:SwipeRefreshLayout = root.findViewById(R.id.newsRefresh)
        newsRefresh.setOnRefreshListener {
            newsList.clear()
            page = 1
            initRequest()
        }

        initRequest()

        return root
    }

    private fun initRequest(){

    }
}