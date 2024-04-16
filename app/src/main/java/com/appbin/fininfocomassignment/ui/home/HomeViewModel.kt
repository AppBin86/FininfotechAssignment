package com.appbin.fininfocomassignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appbin.fininfocomassignment.database.UserInfo
import com.appbin.fininfocomassignment.repository.Repository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.RealmResults
import javax.inject.Inject


class HomeViewModel: ViewModel() {

    var userInfo : List<UserInfo> = emptyList()

    var isSortClicked = MutableLiveData<Boolean>()
    val isSortClickedLiveData : LiveData<Boolean>
        get() = isSortClicked


    fun getAllData(){
        userInfo = Repository().getAllData()
    }

    fun sortClicked(){
        isSortClicked.value = true
    }

    fun sortClickedDone(){
        isSortClicked.value = false
    }


}