package com.lgs.app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.xlhy.lgs.utils.LGSContextUtils
import com.xlhy.lgs.utils.LGSHttpUtils
import com.xlhy.lgs.utils.show
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LGSContextUtils.install(applicationContext)
        val okHttpClient = OkGo.getInstance().getOkHttpClient();
        val longOkHttpClient = okHttpClient.newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS).build();
        OkGo.getInstance().setOkHttpClient(longOkHttpClient)
            .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE).init(application)
        findViewById<View>(R.id.hello).setOnClickListener {
            val params = mapOf(
                "username" to "1001",
                "password" to "123456"
            )
            LGSHttpUtils.post(
                "http://cloud.zhejiangfantian.com/index/huihu/ajaxGetAccountLine",
                params = params,
                block = {
                    it.show(
                        applicationContext
                    )
                },
                errorBlock = {
                    it.show(applicationContext) })
        }

    }
}