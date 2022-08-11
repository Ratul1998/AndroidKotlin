package com.example.myapp.util

import android.app.Activity
import android.content.Context

class SharedPreferenceUtil {

    companion object {

        fun getSharedPreference(key: String, activity: Activity): Int {
            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return -1
            return sharedPref.getInt(key, -1)
        }

        fun writeSharedPreference(key : String,activity: Activity,value:Int){
            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putInt(key, value)
                apply()
            }
        }

    }

}