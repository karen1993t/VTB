package com.vtb.vtbproject.open_vtb_card_steps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {

    private  val _countryIndexLiveData = MutableLiveData<Int>()
    val countryIndexLiveData :LiveData<Int> = _countryIndexLiveData


    fun setCountryName(countryIndex:Int){
         _countryIndexLiveData.postValue(countryIndex)
    }
}