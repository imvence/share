package com.imvence.myapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class SplashActivity: Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //初始化操作

        //初始化操作完成

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}