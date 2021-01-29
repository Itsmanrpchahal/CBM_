package com.casebeaumonde.fragments.users

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myclosets.adapter.MyClosetsAdapter
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.constants.GridItemDecoration
import com.casebeaumonde.fragments.users.Response.UsersResponse
import com.casebeaumonde.fragments.users.adapter.UsersAdapter
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_notifications.*
import kotlinx.android.synthetic.main.fragment_users.*
import retrofit2.Response

class Users : BaseFrag(),Controller.UsersAPI {

    private lateinit var user_recyler : RecyclerView
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var search_ET: EditText
    private lateinit var filePath : String
    private lateinit var response : ArrayList<UsersResponse.Data.User>
    private lateinit var filterData : ArrayList<UsersResponse.Data.User>

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
        controller = Controller()
        controller.Controller(this)
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.GetUsers("Bearer "+getStringVal(Constants.TOKEN))
        }else{
            utility!!.relative_snackbar(parent_notifications!!, getString(R.string.nointernet), getString(R.string.close_up))
        }

        listeners()

        return view
    }

    private fun listeners() {
        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle(s.toString(),filePath)
                } else {
                    setFullData(response,filePath)
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle(s.toString(),filePath)
                } else {
                    user_recyler.visibility = View.VISIBLE
                    setFullData(response,filePath)
                }
            }

        })
    }

    private fun setFullData(response: ArrayList<UsersResponse.Data.User>,filePath : String) {
        user_recyler.layoutManager = GridLayoutManager(context,2)
        //user_recyler.addItemDecoration(GridItemDecoration(10, 2))
        val adapter = UsersAdapter(context!!,response,filePath)
        user_recyler.adapter = adapter
    }

    private fun searchByTitle(toString: String,filePath: String) {
        filterData = ArrayList()
        if (response.size > 0) {
            for (i in response!!.indices) {
                val closetModel = response!![i]
                if (closetModel.firstname!!.toLowerCase().contains(toString.toLowerCase()))
                    filterData!!.add(closetModel)

                if (filterData.size > 0) {
                    user_recyler.layoutManager = GridLayoutManager(context,2)
                    //user_recyler.addItemDecoration(GridItemDecoration(10, 2))
                    val adapter = UsersAdapter(context!!,filterData,filePath)
                    user_recyler.adapter = adapter
                }
            }
            if (filterData.size == 0) {
                user_recyler.visibility = View.GONE
            }
        }
    }


    private fun findIds(view: View) {
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        user_recyler = view.findViewById(R.id.users_recycler)
        search_ET = view.findViewById(R.id.search_ET)
    }

    override fun onUsersSuccess(usersResponse: Response<UsersResponse>) {

        pd.dismiss()
        if (usersResponse.isSuccessful)
        {
            filePath =  usersResponse.body()?.getData()?.filePath.toString()
            response = usersResponse.body()?.getData()?.users as ArrayList<UsersResponse.Data.User>
            setFullData(response,filePath)
        }else{
            utility!!.relative_snackbar(parent_users!!, usersResponse.message(), getString(R.string.close_up))
        }

    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(parent_users!!, error, getString(R.string.close_up))
    }
}