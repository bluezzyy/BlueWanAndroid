package com.bluelzy.bluewanandroid.utils

import android.content.Context
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/2
 *   @desc      解析Json工具类
 */
object LocalJsonUtils {

    fun getJsonString(context: Context, fileName: String): String {
        val sb = StringBuilder()
        val assertManager = context.assets

        try {
            val bufferedReader =
                BufferedReader(InputStreamReader(assertManager.open(fileName), "utf-8"))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                sb.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return sb.toString()
    }

    fun <T> jsonToObject(json: String, type: Class<T>): T = Gson().fromJson(json, type)

}