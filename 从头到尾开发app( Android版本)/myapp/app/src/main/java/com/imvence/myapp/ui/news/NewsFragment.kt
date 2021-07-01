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
    private var newsAdapter:RecyclerView.Adapter<*>?=null
    private lateinit var newsRefresh:SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_news, container, false)

        val newsContent:RecyclerView = root.findViewById(R.id.newsContent)
        newsContent.layoutManager = LinearLayoutManager(root.context)

        newsAdapter = NewsAdapter(newsList, root.context)
        newsContent.adapter = newsAdapter

        newsRefresh = root.findViewById(R.id.newsRefresh)
        newsRefresh.setOnRefreshListener {
            newsList.clear()
            page = 1
            initRequest()
        }

        initRequest()

        return root
    }

    private fun initRequest(){
        val conts = mutableMapOf<Int,String>()
        conts[0] = "你的第一款APP，Interface实现索引与好友列表的关联，难得的教程"
        conts[1] = "小伙做程序员近10年，还要继续做下去，究竟为了什么？"
        conts[2] = "实现你的第一款APP，为好友列表界面添加快速导航"
        conts[3] = "让你的小程序更炫，2种方法快速添加动画效果，哪种性能更好？"
        conts[4] = "非虚拟机实现一台mac或者win同时开多个微信开发者工具"
        conts[5] = "让小程序加载流畅度提高1倍以上，简单高效快收藏起来"
        conts[6] = "程序员天天在改bug，看看今天是什么问题，居然1分钟就搞定了"
        conts[7] = "APP的首屏秒开怎么做到的？"
        conts[8] = "自动生成不同分辨率的LOGO"
        conts[9] = "很常用但很多人都不知道的技巧，让超过范围的内容自动变成省略号"
        conts[10] = "RecycleView实现消息列表"

        var imgs = mutableMapOf<Int,String>()
        imgs[0] = "https://feed-image.baidu.com/0/pic/7a45214ddac830c6098548d7a5689454.jpg"
        imgs[1] = "https://pics1.baidu.com/feed/14ce36d3d539b600d5fcffa504e38f22c65cb72b.jpeg?token=bb6c51af717fd1d97f99feec4c08297a"
        imgs[2] = "https://pics7.baidu.com/feed/ca1349540923dd54c12bfe353fba02d69d82484b.jpeg?token=067b51d787129e6520caa17d5e562dc4"
        imgs[3] = "https://pics6.baidu.com/feed/faf2b2119313b07eedbbf25de1d22a2b97dd8c6b.jpeg?token=4baa904b810c82c7f4fea500ccf0eb8f"
        imgs[4] = "https://pics3.baidu.com/feed/9f510fb30f2442a7bfc612523c461643d013020f.jpeg?token=e715aeeff84e457d375e49b7995ec8bf"
        imgs[5] = "https://pics2.baidu.com/feed/5bafa40f4bfbfbed808d2c2ea6934c3eaec31f72.jpeg?token=c8f28ca855ab25de9a2b0cf21b2947e7"
        imgs[6] = "https://pics7.baidu.com/feed/f703738da97739129dc1f15b267a3d10377ae295.jpeg?token=38a7cbedad3a310d45bdd0306014bb38"
        imgs[7] = "https://pics1.baidu.com/feed/d50735fae6cd7b8994171819074bf9afd8330ea3.jpeg?token=c1f9f94af60cf0957670ddcf4a7d91e1"
        imgs[8] = "https://pics6.baidu.com/feed/6a600c338744ebf8320b3e34209f6c226059a736.jpeg?token=48eb47f3fb9cd8a0957cde62ef125f71"
        imgs[9] = "https://pics5.baidu.com/feed/574e9258d109b3de0072ad8c18f2d689820a4cdf.jpeg?token=63983f7b48e8270a3894358cce933ec2"
        imgs[10] = "https://pics4.baidu.com/feed/962bd40735fae6cd7e439aea2ffeb52c43a70f1e.jpeg?token=713ff55134ca3d4fa76d1cca4ce9b797"

        var start = (page-1)*10
        var end = page*10

        for(i in start..end){
            val rand = (0..10).random()

            var thumbs = mutableMapOf<Int, String>()
            var content = ""

            when(rand){
                1->{
                    val rand2 = (0..10).random()
                    thumbs[0] = imgs[rand2]!!
                }
                2->{
                    val rand2 = (0..10).random()
                    thumbs[0] = imgs[rand2]!!
                }
                3->{
                    val rand2 = (0..10).random()
                    thumbs[0] = imgs[rand2]!!

                    content = conts[rand]!!
                }
                4->{
                    val rand2 = (0..10).random()
                    thumbs[0] = imgs[rand2]!!

                    content = conts[rand]!!
                }
                else->{
                    content = conts[rand]!!
                }
            }

            newsList.add(i, NewsItem(
                "https://asm.api.qt750.com/Uploads/image/tt/$rand.jpg",
                "程序阿源$i",
                content,
                thumbs,
                "$i 小时前"
                )
            )
        }

        newsAdapter?.notifyDataSetChanged()

        if(newsRefresh.isRefreshing){
            newsRefresh.isRefreshing = false
        }
    }
}