package com.bluelzy.bluewanandroid.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import org.koin.core.component.KoinComponent

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/8
 *   @desc
 */
@SuppressLint("StaticFieldLeak")
object SharedPreferencesRepository : KoinComponent {

    // Default SharedPreferences File Name
    private const val PREFERENCES_NAME = "blue_wanandroid"
    private const val PREFERENCES_KEY_KNOWLEDGE_UPDATE_TIME = "knowledge_update_time"
    private const val PREFERENCES_KEY_KNOWLEDGE_JSON = "knowledge_json"

    lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

    private fun getDefaultPreferences(): SharedPreferences =
        this.context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setKnowledgeUpdateTime(time: Long) = getDefaultPreferences().edit()
        .apply {
            putLong(PREFERENCES_KEY_KNOWLEDGE_UPDATE_TIME, time)
        }
        .apply()

    fun getKnowledgeUpdateTime(): Long =
        getDefaultPreferences().getLong(PREFERENCES_KEY_KNOWLEDGE_UPDATE_TIME, 0L)

    fun setKnowledgeJsonData(json: String) = getDefaultPreferences().edit()
        .apply {
            putString(PREFERENCES_KEY_KNOWLEDGE_JSON, json)
        }
        .apply()

    fun getKnowledgeJsonData(): String {
        val result = getDefaultPreferences().getString(PREFERENCES_KEY_KNOWLEDGE_JSON, "") ?: ""
        return if (result == "{}") {
            ""
        } else {
            result
        }
    }

}