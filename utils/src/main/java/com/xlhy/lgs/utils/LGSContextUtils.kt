package com.xlhy.lgs.utils

import android.content.Context

/**
 * @Description 初始化全局context
 * @Author linguangshuo
 * @Date 05-12-2022 周四 9:01
 */
object LGSContextUtils {
    internal var context: Context? = null
    fun install(context: Context) {
        this.context = context
    }
}