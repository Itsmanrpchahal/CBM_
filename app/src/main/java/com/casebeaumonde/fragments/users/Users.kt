package com.casebeaumonde.fragments.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.constants.GridItemDecoration
import com.casebeaumonde.fragments.users.adapter.UsersAdapter

class Users : Fragment() {

    private lateinit var user_recyler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View
        view = inflater.inflate(R.layout.fragment_users, container, false)

        findIds(view)
        setAdapter()
        return view
    }

    private fun setAdapter() {
        user_recyler.layoutManager = GridLayoutManager(context,2)
        user_recyler.addItemDecoration(GridItemDecoration(10, 2))
        val usersList = ArrayList<String>()
        val adapter = UsersAdapter(usersList)
        user_recyler.adapter = adapter
    }

    private fun findIds(view: View) {
        user_recyler = view.findViewById(R.id.users_recycler)
    }
}