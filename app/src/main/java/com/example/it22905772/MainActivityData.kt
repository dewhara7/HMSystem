package com.example.it22905772

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.it22905772.database.entities.Patient

class MainActivityData:ViewModel() {
    private val _data= MutableLiveData<List<Patient>>()
    val data:LiveData<List<Patient>> =_data

    fun setData(data:List<Patient>){
        _data.value=data
    }

}