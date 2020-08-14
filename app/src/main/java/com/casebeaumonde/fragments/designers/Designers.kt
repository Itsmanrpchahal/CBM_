package com.casebeaumonde.fragments.designers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.constants.GridItemDecoration
import com.casebeaumonde.fragments.designers.adapter.DesignerAdapter
import com.casebeaumonde.fragments.users.adapter.UsersAdapter

class Designers : Fragment() {

    private lateinit var designer_recyler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View
        view = inflater.inflate(R.layout.fragment_designers, container, false)

        findIds(view)
        setAdapter()
        return view
    }

    private fun setAdapter() {
        designer_recyler.layoutManager = GridLayoutManager(context,2)
        designer_recyler.addItemDecoration(GridItemDecoration(10, 2))
        val usersList = ArrayList<String>()
        val adapter = DesignerAdapter(usersList)
        designer_recyler.adapter = adapter
    }

    private fun findIds(view: View) {
        designer_recyler = view.findViewById(R.id.designer_recyler)
    }
}