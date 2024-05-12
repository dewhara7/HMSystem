package com.example.it22905772

sealed class ValidationResults{
    data class Empty(val errorMessage:String):ValidationResults()
    data class Invalid(val errorMessage: String):ValidationResults()
    object Valid:ValidationResults()
}
