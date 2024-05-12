package com.example.it22905772

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PatientsViewHolder(v:View):ViewHolder(v) {

    val cbPatient:CheckBox
    val ivDelete:ImageView
    val tvName:TextView
    val tvAge:TextView
    val tvDisease:TextView
    val tvWard:TextView
    val tvDate:TextView

    init {
        cbPatient=v.findViewById(R.id.cbPatient)
        ivDelete=v.findViewById(R.id.ivDelete)
        tvName=v.findViewById(R.id.tvName)
        tvAge=v.findViewById(R.id.tvAge)
        tvDisease=v.findViewById(R.id.tvDisease)
        tvWard=v.findViewById(R.id.tvWard)
        tvDate=v.findViewById(R.id.tvDate)
    }

}