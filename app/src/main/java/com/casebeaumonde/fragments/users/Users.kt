package com.casebeaumonde.fragments.users

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
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

        return view
    }


    private fun findIds(view: View) {
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        user_recyler = view.findViewById(R.id.users_recycler)
    }

    override fun onUsersSuccess(usersResponse: Response<UsersResponse>) {

        pd.dismiss()
        if (usersResponse.isSuccessful)
        {
            user_recyler.layoutManager = GridLayoutManager(context,2)
            user_recyler.addItemDecoration(GridItemDecoration(10, 2))
            val usersList = ArrayList<String>()

            val adapter = UsersAdapter(context!!,usersResponse.body()?.data?.users!!,usersResponse.body()?.data?.filePath.toString())
            user_recyler.adapter = adapter
        }else{
            utility!!.relative_snackbar(parent_users!!, usersResponse.message(), getString(R.string.close_up))
        }

    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(parent_users!!, error, getString(R.string.close_up))
    }
}