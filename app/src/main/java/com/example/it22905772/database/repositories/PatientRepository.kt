package com.example.it22905772.database.repositories

import com.example.it22905772.database.PatientDatabase
import com.example.it22905772.database.entities.Patient

class PatientRepository(
    private val db:PatientDatabase
) {
    suspend fun insert(patient:Patient)=db.getPatientDao().insertPatient(patient)
    suspend fun delete(patient: Patient)=db.getPatientDao().deletePatient(patient)
    fun getAllPatients():List<Patient> =db.getPatientDao().getAllPatients()
}