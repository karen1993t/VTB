package com.vtb.vtb_project.personal_area

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

}