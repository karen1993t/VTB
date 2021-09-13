package com.vtb.vtb_project.view_model

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedCardStepsViewModel : ViewModel() {

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

    // fragment mobilePhone
    private val _mobileNumberLiveData = MutableLiveData<String>()
    val mobileNumberLiveData: LiveData<String> = _mobileNumberLiveData

    //fragment legalAddress
    private val _countryName = MutableLiveData<String>()
    val countryName: LiveData<String> = _countryName

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    private val _streetName = MutableLiveData<String>()
    val streetName: LiveData<String> = _streetName

    private val _houseNumber = MutableLiveData<String>()
    val houseNumber: LiveData<String> = _houseNumber

    private val _apartmentNumber = MutableLiveData<String>()
    val apartmentNumber: LiveData<String> = _apartmentNumber

    // fragment passport
    private val _passportNumber = MutableLiveData<String>()
    val passportNumber: LiveData<String> = _passportNumber

    private val _dateOfIssue = MutableLiveData<String>()
    val dateOfIssue: LiveData<String> = _dateOfIssue

    private val _dateOExpiry = MutableLiveData<String>()
    val dateOExpiry: LiveData<String> = _dateOExpiry

    private val _socialCardNumber = MutableLiveData<String>()
    val socialCardNumber: LiveData<String> = _socialCardNumber

    //fragment Communication

    private val _obtainingTheStatement = MutableLiveData<String>()
    val obtainingTheStatement: LiveData<String> = _obtainingTheStatement

    private val _communicationWithTheBank = MutableLiveData<String>()
    val communicationWithTheBank: LiveData<String> = _communicationWithTheBank

    // Uri video Detect
    private val _videoDetectUri = MutableLiveData<Uri>()
    val videoDetectUri: LiveData<Uri> = _videoDetectUri

    private val _passportPhotoDetectUri = MutableLiveData<Uri>()
    val passportPhotoDetectUri: LiveData<Uri> = _passportPhotoDetectUri


    fun setUserFirstName(firstName: String) {
        _userFirstNameLiveData.postValue(firstName)
    }

    fun setUserSurName(surName: String) {
        _userSurNameLiveData.postValue(surName)
    }

    fun setDateBirthName(dateBirth: String) {
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

    fun setGenderName(gender: String) {
        _userGenderLiveData.postValue(gender)
    }

    fun setMobileNumber(mobileNumber: String) {
        _mobileNumberLiveData.postValue(mobileNumber)
    }

    fun setCountry(country: String) {
        _countryName.postValue(country)
    }

    fun setCityName(city: String) {
        _cityName.postValue(city)
    }

    fun setStreetName(street: String) {
        _streetName.postValue(street)
    }

    fun setHouseNumber(house: String) {
        _houseNumber.postValue(house)
    }

    fun setApartment(apartment: String) {
        _apartmentNumber.postValue(apartment)
    }

    fun setPassportNumber(passport: String) {
        _passportNumber.postValue(passport)
    }

    fun setDateOfIssue(dateOfIssue: String) {
        _dateOfIssue.postValue(dateOfIssue)
    }

    fun setDateExpiry(dateExpiry: String) {
        _dateOExpiry.postValue(dateExpiry)
    }

    fun setSocialCardNumber(socialCardNumber: String) {
        _socialCardNumber.postValue(socialCardNumber)
    }

    fun setTypeObtainingStatement(statement: String) {
        _obtainingTheStatement.postValue(statement)

    }

    fun setCommunicationWithTheBank(typeCommunicationTheBank: String) {
        _communicationWithTheBank.postValue(typeCommunicationTheBank)
    }
    fun setUriVideoDetect(uri:Uri){
        _videoDetectUri.postValue(uri)
    }
    fun  setUriPassportPhoto(uri:Uri){
        _passportPhotoDetectUri.postValue(uri)
    }
}