package com.casebeaumonde.fragments.HireExpert

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.IF.ClosetItemID_IF
import com.casebeaumonde.activities.myclosets.IF.ViewClosetID_IF
import com.casebeaumonde.activities.myclosets.adapter.MyClosetsAdapter
import com.casebeaumonde.activities.myclosets.response.MyClosetsResponse
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.constants.GridItemDecoration
import com.casebeaumonde.fragments.HireExpert.IF.HireExpertIF
import com.casebeaumonde.fragments.HireExpert.adapter.HireAnExpertAdapter
import com.casebeaumonde.fragments.HireExpert.response.HireAnExpertResponse
import com.casebeaumonde.fragments.HireExpert.response.SendInvitationResponse
import com.casebeaumonde.fragments.designers.adapter.DesignerAdapter
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_hire_an_expert.*
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.fragment_users.parent_users
import retrofit2.Response
import java.lang.Exception

class HireAnExpertFragment : BaseFrag(), Controller.HireAnExpertAPI, HireExpertIF,
    Controller.SendInvitationAPI {

    private lateinit var hireanexpertRecycler: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var search_ET: EditText
    private lateinit var dialog: Dialog
    private lateinit var response: ArrayList<HireAnExpertResponse.Data.Gig>
    private lateinit var expert: ArrayList<HireAnExpertResponse.Data.Gig>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hire_an_expert, container, false)
        hireExpertIF = this
        findIds(view)
        listeners()
        return view
    }

    private fun listeners() {
        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle(s.toString())
                } else {
                    setFullData(expert)
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle(s.toString())
                } else {
                    hireanexpertRecycler.visibility = View.VISIBLE
                    setFullData(expert)
                }
            }

        })

        search_ET.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    search_ET.setText("")
                }
                return false
            }
        })
    }

    fun setFullData(expert : ArrayList<HireAnExpertResponse.Data.Gig>)
    {
        response = expert
        hireanexpertRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = HireAnExpertAdapter(context!!, expert!!)
        hireanexpertRecycler.adapter = adapter
    }

    fun searchByTitle(s: String) {

        response = ArrayList()
        if (expert.size > 0) {
            for (i in expert!!.indices) {
                val closetModel = expert!![i]
                if (closetModel.title!!.toLowerCase().contains(s.toLowerCase()))
                    response!!.add(closetModel)

                if (response.size > 0) {
                      hireanexpertRecycler.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    val adapter = HireAnExpertAdapter(context!!, response)
                    hireanexpertRecycler.adapter = adapter
                }
            }
            if (response.size == 0) {
                hireanexpertRecycler.visibility = View.GONE
            }
        }
    }

    private fun findIds(view: View) {
        hireanexpertRecycler = view.findViewById(R.id.hireanexpertRecycler)
        search_ET = view.findViewById(R.id.search_ET)
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        pd.show()
        pd.setContentView(R.layout.loading)
        controller = Controller()
        controller.Controller(this, this)
        controller.HireAnExpert("Bearer " + getStringVal(Constants.TOKEN))
    }

    override fun onHireErxpertSuccess(hireAnExpertResponse: Response<HireAnExpertResponse>) {
        pd.dismiss()
        if (hireAnExpertResponse.isSuccessful) {
            expert = hireAnExpertResponse.body()?.getData()?.gigs as ArrayList<HireAnExpertResponse.Data.Gig>
            setFullData(expert)
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(parent_hireexpert!!, error, getString(R.string.close_up))
    }

    companion object {
        var hireExpertIF: HireExpertIF? = null
    }

    override fun getID(id: Int) {
        dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.sendinvitaion)
        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
        val sendinviationtv: TextView
        val sendinviation_messageet: EditText
        val sendinviation_budget: EditText
        val sendinviation_sendInvitation: Button
        val sendinviation_cancel: Button
        sendinviationtv = dialog.findViewById(R.id.sendinviationtv)
        sendinviation_messageet = dialog.findViewById(R.id.sendinviation_messageet)
        sendinviation_budget = dialog.findViewById(R.id.sendinviation_budget)
        sendinviation_sendInvitation = dialog.findViewById(R.id.sendinviation_sendInvitation)
        sendinviation_cancel = dialog.findViewById(R.id.sendinviation_cancel)
        sendinviationtv.text = "Send invitation to " + response.get(id).user?.firstname


        sendinviation_cancel.setOnClickListener {
            dialog.dismiss()
        }

        sendinviation_sendInvitation.setOnClickListener {
            when {
                sendinviation_messageet.text.isEmpty() -> {
                    sendinviation_messageet.error = "Enter Message"
                    sendinviation_messageet.requestFocus()
                }

                sendinviation_budget.text.isEmpty() -> {
                    sendinviation_budget.error = "Enter Budget"
                    sendinviation_budget.requestFocus()
                }
                else -> {
                    var des = sendinviation_messageet.text.toString()
                    var budget = sendinviation_budget.text.toString()
                    pd.show()
                    controller.SendInvitation(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        response.get(id).user?.id.toString(),
                        des,
                        response.get(id).id.toString(),
                        budget
                    )
                }

            }
        }
    }

    override fun onSendInvitationSuccess(sendInvitationResponse: Response<SendInvitationResponse>) {
        pd.dismiss()
        dialog.dismiss()
        if (sendInvitationResponse.isSuccessful) {

            utility!!.relative_snackbar(
                parent_hireexpert,
                "Invitation Sent",
                getString(R.string.close_up)
            )

        }
    }
}