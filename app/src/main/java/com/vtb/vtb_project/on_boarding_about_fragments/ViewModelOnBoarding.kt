package com.vtb.vtb_project.on_boarding_about_fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelOnBoarding:ViewModel() {
    private val _onBoardingLiveData=MutableLiveData<Boolean>()
    val onBoardingLiveData:LiveData<Boolean> = _onBoardingLiveData
    fun runFragmentSix(boolean: Boolean){
        _onBoardingLiveData.postValue(boolean)
    }
}