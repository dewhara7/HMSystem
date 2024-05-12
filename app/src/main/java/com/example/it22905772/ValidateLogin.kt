package com.example.it22905772

class ValidateLogin(
    private var username:String,
    private var password:String
) {

    var loginObject:LoginCredentials=LoginCredentials

    fun usernameValidate():ValidationResults{
        return if(username.isEmpty()){
            ValidationResults.Empty("Username is empty")
        }else if(username!=loginObject.username){
            ValidationResults.Invalid("Username is invalid")
        }else{
            ValidationResults.Valid
        }
    }

    fun passwordValidate():ValidationResults{
        return if(password.isEmpty()){
            ValidationResults.Empty("Password is empty")
        }else if(password!=loginObject.getPassword()){
            ValidationResults.Invalid("Password is invalid")
        }else{
            ValidationResults.Valid
        }
    }

}