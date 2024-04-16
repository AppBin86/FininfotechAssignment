package com.appbin.fininfocomassignment.ui.login

import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appbin.fininfocomassignment.repository.Repository
import com.appbin.fininfocomassignment.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class LoginViewModel : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var isLogin = MutableLiveData<Boolean>()
    val isLoginLiveData : LiveData<Boolean>
        get() = isLogin

    var showToast = MutableLiveData(false)
    val showToastLiveData : LiveData<Boolean>
        get() = showToast


    val userName = ObservableField<String>()
    val password = ObservableField<String>()
    val toastMessage = MutableLiveData<String>("Please Enter userName and Password")

    fun loginClicked(){
        if (userName.get().isNullOrEmpty()&&password.get().isNullOrEmpty()){
            toastMessage.value = "Please Enter userName and Password"
            showToast.value = true
        }else if (userName.get().isNullOrEmpty()){
            toastMessage.value = "Please Enter userName"
            showToast.value = true
        }else if (password.get().isNullOrEmpty()){
            toastMessage.value = "Please Enter Password"
            showToast.value = true
        }else if (userName.get()?.trim()!=Constants.USER_NAME&&password.get()?.trim()!=Constants.PASSWORD){
            toastMessage.value = "UserName is invalid"
            showToast.value = true
        }else if (password.get()?.trim()!=Constants.PASSWORD){
            toastMessage.value = "Password is invalid"
            showToast.value = true
        }else if (userName.get()?.trim()!=Constants.USER_NAME){
            toastMessage.value = "UserName & Password is invalid"
            showToast.value = true
        }else{
            isLogin.value = true
        }
    }

    fun loginSuccessfully(){
        isLogin.value = false
    }

    fun toastDone(){
        isLogin.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}