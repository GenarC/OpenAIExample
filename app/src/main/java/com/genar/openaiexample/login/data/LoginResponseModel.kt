package com.genar.openaiexample.login.data

data class LoginResponseModel(
    val token: String
){
    //Create empty object
    companion object {
        var EMPTY = LoginResponseModel("")
    }

}