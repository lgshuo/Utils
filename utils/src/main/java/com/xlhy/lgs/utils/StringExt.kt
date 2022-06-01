package com.xlhy.lgs.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast

/**
 * 吐司
 */

/**
 * 如果这行报错就在application里写LGSContextUtils.install(this)
 */
var toast = Toast.makeText(LGSContextUtils.context, "", Toast.LENGTH_LONG)
var mHandler = Handler(Looper.getMainLooper())
fun Any?.show(context: Context? = LGSContextUtils.context, during: Int = Toast.LENGTH_LONG) {
    context?.let {
        this?.let {
            mHandler.post {
                toast.setText(this.toString())
                toast!!.show()
                mHandler.postDelayed({
                   toast.cancel()
                }, 3000)
            }

            return
        }
        Toast.makeText(context, "null", during).show()
        return
    }
    throw Exception("context is null,you should use LGSUtils.install(context) in Application or use param context")
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

