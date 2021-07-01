package com.imvence.myapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.itemIconTintList = null //去掉图标的默认背景色

        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //val appBarConfiguration = AppBarConfiguration(setOf(
        // R.id.navigation_home, R.id.navigation_friends, R.id.navigation_news, R.id.navigation_mine))
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        this.changeStatusBarColor(true);

        toolbar.inflateMenu(R.menu.home_friends_menu)


        Fresco.initialize(this);
    }

    //监听tabbar切换状态
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        /*
        val titleMap = mutableMapOf<Int, String>(
            R.id.navigation_home to this.resources.getString(R.string.tabbar_home_title),
            R.id.navigation_friends to this.resources.getString(R.string.tabbar_friends_title),
            R.id.navigation_news to this.resources.getString(R.string.tabbar_news_title),
            R.id.navigation_mine to this.resources.getString(R.string.tabbar_mine_title)
        )
        */

        //toolbar.title = titleMap[item.itemId]

        val menuMap = mutableMapOf<Int, Int>(
            R.id.navigation_home to R.menu.home_friends_menu,
            R.id.navigation_friends to R.menu.home_friends_menu,
            R.id.navigation_news to R.menu.news_mine_menu,
            R.id.navigation_mine to R.menu.news_mine_menu
        )

        toolbar.menu?.clear()
        menuMap[item.itemId]?.let { toolbar.inflateMenu(it) }

        toolbar.title = item.title

        when(item.itemId){
            R.id.navigation_home->navController.navigate(R.id.navigation_home)
            R.id.navigation_friends->navController.navigate(R.id.navigation_friends)
            R.id.navigation_news->navController.navigate(R.id.navigation_news)
            R.id.navigation_mine->navController.navigate(R.id.navigation_mine)
        }

        return@OnNavigationItemSelectedListener true

    }

    //设置状态栏字体颜色
    private fun changeStatusBarColor(setDark: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView: View = window.decorView
            if (decorView != null) {
                var vis: Int = decorView.systemUiVisibility
                vis = if (setDark) {
                    vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    vis and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
                decorView.systemUiVisibility = vis
            }
        }
    }

}
