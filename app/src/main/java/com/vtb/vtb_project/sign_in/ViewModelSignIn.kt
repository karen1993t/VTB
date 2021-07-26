package com.vtb.vtb_project.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelSignIn:ViewModel() {
    private val _pin1LiveData = MutableLiveData<String>()
    val pin1LiveData:LiveData<String> = _pin1LiveData
    fun setPin1(pin1:String){
        _pin1LiveData.postValue(pin1)
    }
}