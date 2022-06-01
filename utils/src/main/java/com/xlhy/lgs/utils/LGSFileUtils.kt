package com.xlhy.lgs.utils

import android.text.TextUtils
import java.io.File
import java.io.FileFilter

object LGSFileUtils {
    /**
     * 获取所有文件(不含文件夹)
     */
    fun getAllFiles(path: String, list: ArrayList<File>) {
        getAllFilesByFilter(path, list, null)
    }
    /**
     * 获取所有文件并过滤(不含文件夹)
     */
    fun getAllFilesByFilter(path: String, list: ArrayList<File>, filter: FileFilter? = null) {
        if (TextUtils.isEmpty(path)) {
            return
        }
        try {
            val file = File(path)
            if (!file.exists()) {
                return
            }
            val files = file.listFiles()
            if (files.size <= 0) {
                return
            }
            for (childFile in files) {
                if (childFile.isDirectory) {
                    getAllFilesByFilter(childFile.absolutePath, list, filter)
                } else {
                    if (filter == null) {
                        list.add(childFile)
                    } else if (filter.accept(childFile)) {
                        list.add(childFile)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}