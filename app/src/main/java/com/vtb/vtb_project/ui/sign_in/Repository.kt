package com.vtb.vtb_project.ui.sign_in

interface Repository {
    suspend fun getEmail()
    suspend fun checkEmail()
}