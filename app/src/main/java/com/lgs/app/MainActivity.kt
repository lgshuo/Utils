package com.lgs.app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xlhy.lgs.utils.LGSContextUtils
import com.xlhy.lgs.utils.LGSHttpUtils
import com.xlhy.lgs.utils.show

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LGSContextUtils.install(applicationContext)

        findViewById<View>(R.id.hello).setOnClickListener {
            "sdasdads".show()
        }

//        val params = mapOf(
//            "username" to "1001",
//            "password" to "123456"
//        )
//        LGSHttpUtils.post(
//            "http://cloud.zhejiangfantian.com/index/huihu/ajaxGetAccountLine",
//            params = params,
//            block = {
//                it.show(
//                    applicationContext
//                )
//            },
//            errorBlock = {
//                it.show(applicationContext) })
        val makeText = Toast.makeText(null, "", Toast.LENGTH_SHORT)
        makeText.show()
        "bbbb".show()


    }
}