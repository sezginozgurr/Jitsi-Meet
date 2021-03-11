package com.example.jeetsemeet.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class LocalDataManager {
    fun putString(context: Context, key: String?, value: String?) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(Constant.KEY_PREFENRENCE_NAME, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.putString(key, value)
        edit.apply()
    }
    fun putBoolean(context: Context, key: String?, value: Boolean?) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(Constant.KEY_PREFENRENCE_NAME, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.putBoolean(key, value!!)
        edit.apply()
    }

    fun getString(context: Context, key: String?, defaultValue: String?): String {
        return context.getSharedPreferences(Constant.KEY_PREFENRENCE_NAME, Context.MODE_PRIVATE).getString(key, defaultValue)!!
    }

    fun getBoolean(context: Context, key: String?): Boolean {
        return context.getSharedPreferences(Constant.KEY_PREFENRENCE_NAME, Context.MODE_PRIVATE).getBoolean(key,false)
    }

    fun clearSharedPreference(context: Context) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.clear()
        edit.apply()
    }

    fun removeSharedPreference(context: Context, key: String?) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.remove(key)
        edit.apply()
    }



    companion object {
        val instance = LocalDataManager()
    }
}
