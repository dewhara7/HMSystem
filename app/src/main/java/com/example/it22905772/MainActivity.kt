package com.example.it22905772

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.it22905772.adapters.PatientAdapter
import com.example.it22905772.database.PatientDatabase
import com.example.it22905772.database.repositories.PatientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter:PatientAdapter
    private lateinit var viewModel:MainActivityData
    private val insertFormFragment=InsertFormFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository=PatientRepository(PatientDatabase.getInstance(this))
        val recyclerView:RecyclerView=findViewById(R.id.rvPatientsList)
        viewModel=ViewModelProvider(this)[MainActivityData::class.java]

        viewModel.data.observe(this){
            adapter= PatientAdapter(it,repository,viewModel)
            recyclerView.adapter=adapter
            recyclerView.layoutManager=LinearLayoutManager(this)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data=repository.getAllPatients()
            runOnUiThread {
                viewModel.setData(data)
            }
        }

        val btnAddPatient:Button=findViewById(R.id.btnAdd)

        btnAddPatient.setOnClickListener {
            //loadInsertForm()
            val i=Intent(this,DataInsertActivity::class.java)
            startActivity(i)
        }

        val btnLogout:Button=findViewById(R.id.btnLogout)

        btnLogout.setOnClickListener {
            val i=Intent(this,LoginActivity::class.java)
            startActivity(i)
        }

    }

    private fun loadInsertForm(){
        val fragment=supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        Log.d("FragmentLoad", "before if $fragment")

        if(fragment==null){
            Log.d("FragmentLoad", "Fragment is null, adding insertFormFragment")
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,insertFormFragment).commit()
        }else{
            Log.d("FragmentLoad", "Replacing existing fragment with insertFormFragment")
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,insertFormFragment).commit()
        }
    }

}