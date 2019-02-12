package com.lfork.phonelimitadvanced.limit.task

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class RecentlyReceiver : BroadcastReceiver() {

    var systemKeyListener :SystemKeyListener?=null

    override fun onReceive(context: Context, intent: Intent) {
        val reason = intent.getStringExtra("reason")
        if (reason != null) {
            when (reason) {
                "homekey"//Home键
                -> {
                    Log.d("反应速度测试", "homekey")
                }
                "recentapps"//最近任务键
                -> {
                    systemKeyListener?.onRecentAppsClicked()
                    Log.d("反应速度测试", "recentapps")
                }
                "assist"//长按Home键
                -> Log.d("反应速度测试", "assist")
                else -> {
                }
            }
        }
    }

    interface SystemKeyListener{
        fun onRecentAppsClicked()
    }

    fun registerKeyListener(listener :SystemKeyListener){
        systemKeyListener = listener
    }

    fun unregisterKeyListener(){
        systemKeyListener = null
    }
}