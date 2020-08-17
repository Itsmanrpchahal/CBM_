package com.casebeaumonde.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import com.casebeaumonde.R
import com.toptoche.searchablespinnerlibrary.SearchableSpinner

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var add : Button
    private lateinit var spinner : SearchableSpinner
    private lateinit var data : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        findIds(view)
        add.setOnClickListener(this)
        return view
    }

    private fun findIds(view: View) {
        add = view.findViewById(R.id.add)!!
        spinner = view.findViewById(R.id.spinner)

        val users = listOf("Select Item","Veniam penatibus", "reprehenderit", "fusce", "Ullamcorper lacinia", "Etiam dis")

        val adapter = context?.let { ArrayAdapter<String>(it,android.R.layout.simple_spinner_dropdown_item,users) }
        spinner.adapter = adapter

        spinner.setTitle("Select Item")
        spinner.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(context,""+users.get(position),Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onClick(v: View?) {

    }
}