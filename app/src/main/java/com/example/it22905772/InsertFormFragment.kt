package com.example.it22905772

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [InsertFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InsertFormFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel:MainActivityData
    private lateinit var repository:PatientRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MainActivityData::class.java)
        repository = PatientRepository(PatientDatabase.getInstance(requireContext()))
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_insert_form, container, false)

        val edtName:EditText=rootView.findViewById(R.id.edtName)
        val edtAge:EditText=rootView.findViewById(R.id.edtAge)
        val edtDisease:EditText=rootView.findViewById(R.id.edtDisease)
        val edtWard:EditText=rootView.findViewById(R.id.edtWard)
        val edtDate:EditText=rootView.findViewById(R.id.edtDate)
        val btnInsert:Button=rootView.findViewById(R.id.btnInsert)
        val btnBack:Button=rootView.findViewById(R.id.btnBack)

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
                viewModel.setData(data)
            }
            Toast.makeText(requireActivity(),"Patient inserted successfully!",Toast.LENGTH_LONG).show()
            val i=Intent(requireActivity(),MainActivity::class.java)
            startActivity(i)
        }

        btnBack.setOnClickListener {
            val i=Intent(requireActivity(),MainActivity::class.java)
            startActivity(i)
        }

        return rootView
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InsertFormFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InsertFormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

