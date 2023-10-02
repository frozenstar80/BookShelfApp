package com.example.bookshelfapp.util

import android.content.Context
import android.content.SharedPreferences
import com.example.bookshelfapp.util.Constants.SHARED_PREF_NAME

class SavedPrefManager(context: Context?) {

    private var sharedPreferences: SharedPreferences? =
        context!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor? = sharedPreferences?.edit()

    fun isLogin(): Boolean?{
        return sharedPreferences?.getBoolean(Constants.IS_LOGIN, false)
    }
    fun putLogin(isLogin : Boolean) {
        editor?.putBoolean(Constants.IS_LOGIN,isLogin)
        editor?.apply()
    }



}