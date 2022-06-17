package com.xlhy.lgs.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils

/**
 * 吐司
 */

/**
 * 如果这行报错就在application里写LGSContextUtils.install(this)
 */


fun Any?.show(context: Context? = LGSContextUtils.context) {
    if (this == null) {
        ToastUtils.showShort("null")
    }else{
        ToastUtils.showShort(toString())
    }
}

/**
 * 类扩展都写这里
 */

enum class Level {
    E,
    V,
    I,
    W,
    D,
}

/**
 * 日志打印
 */
fun Any?.log(tag: String = "", level: Level = Level.E) {
    if (this == null) {
        Log.e(tag, "null")
        return
    }
    if (isDebug(LGSContextUtils.context)) {
        when (level) {
            Level.E -> Log.e("LGSLog:${tag}", this.toString())
            Level.V -> Log.v("LGSLog:${tag}", this.toString())
            Level.I -> Log.i("LGSLog:${tag}", this.toString())
            Level.W -> Log.w("LGSLog:${tag}", this.toString())
            Level.D -> Log.d("LGSLog:${tag}", this.toString())
        }
    }
}


fun isDebug(context: Context?): Boolean {
    context?.let {
        return it.getApplicationInfo() != null &&
                it.getApplicationInfo().flags and ApplicationInfo.FLAG_DEBUGGABLE !== 0
    }
    return false

}

