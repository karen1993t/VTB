package com.vtb.vtb_project.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelAuthorization:ViewModel() {

   private val  _authorizationLiveData = MutableLiveData<Boolean>()
    val authorizationLiveData :LiveData<Boolean> = _authorizationLiveData

    fun closeAuthorization(boolean: Boolean){
        _authorizationLiveData.postValue(boolean)
    }
}