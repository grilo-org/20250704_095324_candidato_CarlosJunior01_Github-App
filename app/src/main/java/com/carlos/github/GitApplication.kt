package com.carlos.github

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object {
        lateinit var mInstance: GitApplication
        fun getContext(): Context? {
            return mInstance.applicationContext
        }
    }
}