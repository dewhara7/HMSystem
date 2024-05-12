package com.example.it22905772.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(
    var name:String?,
    var age:Int?,
    var disease:String?,
    var wardNo:Int?,
    var admittedDate:String?
){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
