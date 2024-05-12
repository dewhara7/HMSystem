package com.example.it22905772.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.it22905772.MainActivityData
import com.example.it22905772.PatientsViewHolder
import com.example.it22905772.R
import com.example.it22905772.database.entities.Patient
import com.example.it22905772.database.repositories.PatientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PatientAdapter(
    details:List<Patient>,
    repository:PatientRepository,
    viewModel:MainActivityData
):RecyclerView.Adapter<PatientsViewHolder>() {

    var context:Context?=null
    val details=details
    val repository=repository
    val viewModel=viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_patients,parent,false)
        context=parent.context
        return PatientsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return details.size
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        holder.tvName.text=details.get(position).name
        holder.tvAge.text=details.get(position).age.toString()
        holder.tvDisease.text=details.get(position).disease
        holder.tvWard.text=details.get(position).wardNo.toString()
        holder.tvDate.text=details.get(position).admittedDate

        holder.ivDelete.setOnClickListener {
            val isChecked=holder.cbPatient.isChecked

            if(isChecked){
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(details.get(position))
                    val data=repository.getAllPatients()
                    withContext(Dispatchers.Main){
                        viewModel.setData(data)
                    }
                }
                Toast.makeText(context,"Patient Details Deleted!",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Select details to Delete!",Toast.LENGTH_LONG).show()
            }
        }
    }

}