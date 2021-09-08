package com.vtb.vtb_project.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelPersonalArea : ViewModel() {
    private val _addCardLiveData = MutableLiveData<Boolean>()
    val addCardLiveData: LiveData<Boolean> = _addCardLiveData

    fun selectDoneBtn(boolean: Boolean) {
        _addCardLiveData.postValue(boolean)
    }

    private val _getNumberLiveData = MutableLiveData<String>()
    val getNumberLiveData:LiveData<String> = _getNumberLiveData
    fun setNumberCard(string:String){
        _getNumberLiveData.postValue(string)
    }

    private val _getNameLiveData = MutableLiveData<String>()
    val getNameLiveData:LiveData<String> = _getNameLiveData
    fun setNameCard(string:String){
        _getNameLiveData.postValue(string)
    }

//    private val _statusPersonalAreaFragmentLiveData = MutableLiveData<Boolean>()
//    val statusPersonalAreaFragmentLiveData:LiveData<Boolean> = _statusPersonalAreaFragmentLiveData
//    fun statusChecker(boolean: Boolean){
//        _statusPersonalAreaFragmentLiveData.postValue(boolean)
//    }

    private val _saveCardLiveData = MutableLiveData<Boolean>()
    val saveCardLiveData:LiveData<Boolean> = _saveCardLiveData
    fun switchChecker(boolean: Boolean){
        _saveCardLiveData.postValue(boolean)
    }

}