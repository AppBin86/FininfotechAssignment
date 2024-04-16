package com.appbin.fininfocomassignment.repository

import android.util.Log
import com.appbin.fininfocomassignment.database.UserInfo
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

class Repository  {

    val database = Realm.getDefaultInstance()

    fun insertData(userInfo: UserInfo){
        database.executeTransaction {
            database.insertOrUpdate(userInfo)
        }
    }

    fun getAllData() : List<UserInfo>{
        val data = database.where(UserInfo::class.java).findAll()
        return database.copyFromRealm(data)
    }
}