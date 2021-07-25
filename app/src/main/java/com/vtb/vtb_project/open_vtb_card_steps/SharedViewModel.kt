package com.vtb.vtb_project.open_vtb_card_steps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _userFirstNameLiveData = MutableLiveData<String>()
    val userFirstNameLiveData: LiveData<String> = _userFirstNameLiveData

    private val _userSurNameLiveData = MutableLiveData<String>()
    val userSurNameLiveData: LiveData<String> = _userSurNameLiveData

    private val _userDateBirthLiveData = MutableLiveData<String>()
    val userDateBirthLiveData: LiveData<String> = _userDateBirthLiveData

    private val _countryIndexLiveData = MutableLiveData<Int>()
    val countryIndexLiveData: LiveData<Int> = _countryIndexLiveData

    private val _citizenShipIndexLiveData = MutableLiveData<Int>()
    val citizenShipIndexLiveData: LiveData<Int> = _citizenShipIndexLiveData

    private val _userGenderLiveData = MutableLiveData<String>()
    val userGenderLiveData: LiveData<String> = _userGenderLiveData


    private val _isCloseActivityLiveData = MutableLiveData<Boolean>()
    val isCloseActivityLiveData: LiveData<Boolean> = _isCloseActivityLiveData


    fun inputUserFirstName(firstName: String) {
        _userFirstNameLiveData.postValue(firstName)
    }

    fun inputUserSurName(surName: String) {
        _userSurNameLiveData.postValue(surName)
    }

    fun inputDateBirthName(dateBirth: String) {
        _userDateBirthLiveData.postValue(dateBirth)
    }

    fun setCountryName(countryIndex: Int) {
        _countryIndexLiveData.postValue(countryIndex)
    }

    fun setCitizenShipName(citizenShipIndex: Int) {
        _citizenShipIndexLiveData.postValue(citizenShipIndex)
    }

    fun closeActivity(inputArgumentForCloseActivity: Boolean) {
        _isCloseActivityLiveData.postValue(inputArgumentForCloseActivity)
    }
    fun setGenderName(gender:String){
        _userGenderLiveData.postValue(gender)
    }
}