package com.vtb.vtb_project.view_model


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ViewModelSignIn():ViewModel() {
    private val _pin1LiveData = MutableLiveData<String>()
    val pin1LiveData:LiveData<String> = _pin1LiveData

    fun setPin1(pin1:String){
        _pin1LiveData.postValue(pin1)
    }

    private val _closeSignInLiveData = MutableLiveData<Boolean>()
    val closeSignInLiveData:LiveData<Boolean> = _closeSignInLiveData

    fun closeSignIn(boolean: Boolean){
        _closeSignInLiveData.postValue(boolean)
    }

    private val _emailLiveData = MutableLiveData<String>()
    val emailLiveData:LiveData<String> = _emailLiveData

    fun emailLiveData(string: String){
        _emailLiveData.postValue(String())
    }

    private val _checkEmailLiveData = MutableLiveData<Boolean>()
    val checkEmailLiveData:LiveData<Boolean> = _checkEmailLiveData

    fun checkEmailLiveData(boolean: Boolean) {
        _checkEmailLiveData.postValue(boolean)

    }
}