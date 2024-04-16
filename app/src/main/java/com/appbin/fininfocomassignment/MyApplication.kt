package com.appbin.fininfocomassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val  config: RealmConfiguration = RealmConfiguration.Builder().name("FinInfoComDB") .schemaVersion(1)
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)

    }
}