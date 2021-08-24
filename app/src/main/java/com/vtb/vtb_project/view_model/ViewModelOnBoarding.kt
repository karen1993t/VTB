package com.vtb.vtb_project.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelOnBoarding : ViewModel() {
    private val _getStartedLiveData = MutableLiveData<Boolean>()
    val getStartedLiveData: LiveData<Boolean> = _getStartedLiveData

    fun runFragmentSix(boolean: Boolean) {
        _getStartedLiveData.postValue(boolean)
    }

    private val _closeButtonLiveData = MutableLiveData<Boolean>()
    val closeButtonLiveData: LiveData<Boolean> = _closeButtonLiveData

    fun closeOnBoardingAbout(boolean: Boolean) {
        _closeButtonLiveData.postValue(boolean)
    }
}