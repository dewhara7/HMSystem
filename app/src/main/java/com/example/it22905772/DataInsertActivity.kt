package com.example.it22905772

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.it22905772.database.PatientDatabase
import com.example.it22905772.database.entities.Patient
import com.example.it22905772.database.repositories.PatientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataInsertActivity : AppCompatActivity() {

    private lateinit var viewModel:MainActivityData
    private lateinit var repository: PatientRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_insert)

        viewModel = ViewModelProvider(this).get(MainActivityData::class.java)
        repository = PatientRepository(PatientDatabase.getInstance(this))

        val edtName:EditText=findViewById(R.id.edtName)
        val edtAge:EditText=findViewById(R.id.edtAge)
        val edtDisease:EditText=findViewById(R.id.edtDisease)
        val edtWard:EditText=findViewById(R.id.edtWard)
        val edtDate:EditText=findViewById(R.id.edtDate)
        val btnInsert:Button=findViewById(R.id.btnInsert)
        val btnBack:Button=findViewById(R.id.btnBack)

        btnInsert.setOnClickListener {
            val name=edtName.text.toString()
            val age=edtAge.text.toString()
            val disease=edtDisease.text.toString()
            val ward=edtWard.text.toString()
            val date=edtDate.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                Log.d("Intert values", "values $name $age $disease $ward $date")
                repository.insert(Patient(name,age.toInt(),disease,ward.toInt(),date))
                val data= repository.getAllPatients()
                runOnUiThread {
                    viewModel.setData(data)
                }
            }
            Toast.makeText(this,"Patient inserted successfully!", Toast.LENGTH_LONG).show()
            val i= Intent(this,MainActivity::class.java)
            startActivity(i)
        }

        btnBack.setOnClickListener {
            val i= Intent(this,MainActivity::class.java)
            startActivity(i)
        }

    }
}