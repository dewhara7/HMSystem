package com.example.it22905772.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.it22905772.database.daos.PatientDao
import com.example.it22905772.database.entities.Patient

@Database(entities = [Patient::class], version = 1)
abstract class PatientDatabase:RoomDatabase() {

    abstract fun getPatientDao():PatientDao

    companion object{
        @Volatile
        private var INSTANCE:PatientDatabase?=null

        fun getInstance(context:Context):PatientDatabase{
            synchronized(this){
                return INSTANCE?:Room.databaseBuilder(
                    context.applicationContext,
                    PatientDatabase::class.java,
                    "patient_db"
                ).build().also {
                    INSTANCE=it
                }
            }
        }
    }

}