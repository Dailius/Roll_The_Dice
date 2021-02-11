package com.dailiusprograming.rolldice.util

import android.app.Application

// add this file name in application tag of manifest file .

class AppPrefInit : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}