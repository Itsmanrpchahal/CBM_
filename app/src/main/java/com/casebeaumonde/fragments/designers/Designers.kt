package com.casebeaumonde.fragments.designers

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
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
import com.casebeaumonde.fragments.designers.Response.DesignersResponse
import com.casebeaumonde.fragments.designers.adapter.DesignerAdapter
import com.casebeaumonde.fragments.users.adapter.UsersAdapter
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_notifications.*
import kotlinx.android.synthetic.main.activity_notifications.parent_notifications
import kotlinx.android.synthetic.main.fragment_designers.*
import retrofit2.Response

class Designers : BaseFrag(), Controller.DesignersAPI {

    private lateinit var designer_recyler: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View
        view = inflater.inflate(R.layout.fragment_designers, container, false)

        findIds(view)
        controller = Controller()
        controller.Controller(this)
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.GetDesigners("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                parent_designer!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
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
        designer_recyler = view.findViewById(R.id.designer_recyler)
    }

    override fun onDesignersSuccess(designerResponse: Response<DesignersResponse>) {
        pd!!.dismiss()
        if (designerResponse.isSuccessful)
        {
            designer_recyler.layoutManager = GridLayoutManager(context, 2)
            designer_recyler.addItemDecoration(GridItemDecoration(10, 2))

            val adapter = DesignerAdapter(context!!,designerResponse?.body()?.data?.users!!)
            designer_recyler.adapter = adapter
        }else{
            utility!!.relative_snackbar(
                parent_designer!!,
                designerResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_designer!!,
            error,
            getString(R.string.close_up)
        )
    }
}