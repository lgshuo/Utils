package com.xlhy.lgs.utils

import android.app.Application
import androidx.core.content.FileProvider
import com.blankj.utilcode.util.Utils

/**
 * @Description TODO
 * @Author linguangshuo
 * @Date 06-21-2022 周二 11:20
 */
class LgsUtilProvider : FileProvider() {
    override fun onCreate(): Boolean {
        LGSContextUtils.install(context!!.applicationContext as Application)
        return true
    }
}