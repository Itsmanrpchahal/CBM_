package com.casebeaumonde.activities.myoutfits

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myclosets.adapter.MyClosetsAdapter
import com.casebeaumonde.activities.myoutfits.IF.DeleteID_IF
import com.casebeaumonde.activities.myoutfits.IF.OutfitID_IF
import com.casebeaumonde.activities.myoutfits.adapter.MOutfitsAdapter
import com.casebeaumonde.activities.myoutfits.response.DeleteOutfitResponse
import com.casebeaumonde.activities.myoutfits.response.EditOutfitResponse
import com.casebeaumonde.activities.myoutfits.response.MyOutfitsResponse
import com.casebeaumonde.activities.myoutfits.response.NewOutfitResponse
import com.casebeaumonde.activities.myoutfitsdetail.IF.OutfitFavID_IF
import com.casebeaumonde.activities.myoutfitsdetail.response.FavOutfitResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_my_closets.*
import kotlinx.android.synthetic.main.activity_my_closets.parent_myclosets
import kotlinx.android.synthetic.main.activity_my_outfits.*
import retrofit2.Response

class MyOutfits : BaseClass(),
    Controller.MyOutfitsAPI, OutfitID_IF, DeleteID_IF,Controller.CreateOutfitAPI, Controller.EditOutFitAPI,Controller.DeleteOutFitAPI ,OutfitFavID_IF,Controller.FavOutfitAPI{

    private lateinit var userID: String
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var myclosets_back: ImageButton
    private lateinit var myoutfits_recyler: RecyclerView
    private lateinit var outfits: ArrayList<MyOutfitsResponse.Data.Outfit>
    private lateinit var create_outfit: ImageButton
    private lateinit var addnewoutfitDialog: Dialog
    private lateinit var outfitname: EditText
    private lateinit var deleteDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_outfits)

        findIDs()
        listeners()
    }

    private fun listeners() {
        myclosets_back.setOnClickListener {
            onBackPressed()
        }

        create_outfit.setOnClickListener {
            AddNewOutfit("new","","")
        }
    }

    private fun AddNewOutfit(type:String,id:String,pos: String) {
        addnewoutfitDialog = Dialog(this)
        addnewoutfitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addnewoutfitDialog.setCancelable(false)
        addnewoutfitDialog.setContentView(R.layout.createnewoutfitdialog)
        val window = addnewoutfitDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val cancel: Button
        val create: Button
        val outfitname: EditText
        val outfit_decs: EditText

        cancel = addnewoutfitDialog.findViewById(R.id.cancel_bt)
        create = addnewoutfitDialog.findViewById(R.id.create_bt)
        outfitname = addnewoutfitDialog.findViewById(R.id.outfitname)
        outfit_decs = addnewoutfitDialog.findViewById(R.id.outfit_decs)

        if (type.equals("edit"))
        {
            outfitname.setText(outfits.get(pos.toInt()).title)
            outfit_decs.setText(""+outfits.get(pos.toInt()).description)
            create.setText("Update Outfit")
        }

        cancel.setOnClickListener {
            addnewoutfitDialog.dismiss()
        }

        create.setOnClickListener {

            when {
                outfitname.text.isEmpty() -> {
                    outfitname.requestFocus()
                    outfitname.setError("Enter Outfit name")
                }

                outfit_decs.text.isEmpty() -> {
                    outfit_decs.requestFocus()
                    outfit_decs.setError("Enter outfit description")
                }
                else -> {

                    if (type.equals("edit"))
                    {
                        editOutfit(outfitname.text.toString(),outfit_decs.text.toString(),id)
                    } else {
                        createOutFit(outfitname.text.toString(), outfit_decs.text.toString())
                    }

                }
            }
        }

        addnewoutfitDialog.show()
    }

    private fun editOutfit(title: String, decs: String,id: String) {
        pd.show()
        pd.setContentView(R.layout.loading)
        controller.EditOutFit("Bearer "+getStringVal(Constants.TOKEN),id,title,decs)
    }

    private fun createOutFit(name: String, decs: String) {
        controller.CreateNewOutfit("Bearer " + getStringVal(Constants.TOKEN), name, decs)
        pd.show()
        pd.setContentView(R.layout.loading)
    }

    private fun findIDs() {
        controller = Controller()
        controller.Controller(this, this, this,this,this)
        myclosets_back = findViewById(R.id.myclosets_back)
        myoutfits_recyler = findViewById(R.id.myoutfits_recyler)
        create_outfit = findViewById(R.id.create_outfit)
        userID = intent.getStringExtra("userID").toString()
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)


        outfitidIf = this
        deleteidIf = this
        outfitfavidIf = this
        controller.MyOutfits("Bearer " + getStringVal(Constants.TOKEN))
        pd!!.show()
        pd!!.setContentView(R.layout.loading)

    }

    override fun onMyOutfitsSuccess(success: Response<MyOutfitsResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            outfits = success.body()?.data?.outfits as ArrayList<MyOutfitsResponse.Data.Outfit>
            setFullData(outfits)
        } else {
            utility!!.relative_snackbar(
                parent_outfits,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    private fun setFullData(outfits: java.util.ArrayList<MyOutfitsResponse.Data.Outfit>) {
        myoutfits_recyler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = MOutfitsAdapter(
            this, outfits!!, userID,
            getStringVal(Constants.USERID).toString()
        )
        myoutfits_recyler.adapter = adapter
    }

    companion object {
        var outfitidIf: OutfitID_IF? = null
        var deleteidIf: DeleteID_IF? = null
        var outfitfavidIf: OutfitFavID_IF? = null
    }

    override fun onCreateOutfitSuccess(success: Response<NewOutfitResponse>) {

        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {


                addnewoutfitDialog.dismiss()
                controller.MyOutfits("Bearer " + getStringVal(Constants.TOKEN))
            } else {
                pd.dismiss()
                utility!!.relative_snackbar(
                    parent_outfits,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_outfits,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onFavOutfitSuccess(success: Response<FavOutfitResponse>) {
        pd.dismiss()
    }


    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_outfits,
            error,
            getString(R.string.close_up)
        )
    }

    override fun getClosetID(ID: String?,pos:String) {
        AddNewOutfit("edit", ID.toString(),pos)
    }

    override fun onEditOutfitSuccess(success: Response<EditOutfitResponse>) {
        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {


                addnewoutfitDialog.dismiss()
                controller.MyOutfits("Bearer " + getStringVal(Constants.TOKEN))
            } else {
                pd.dismiss()
                utility!!.relative_snackbar(
                    parent_outfits,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_outfits,
                success.message(),
                getString(R.string.close_up)
            )
        }

    }

    override fun getID(pos: String?, id: String?) {

        deleteDialog = Dialog(this)
        deleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        deleteDialog.setCancelable(false)
        deleteDialog.setContentView(R.layout.logout_dialog)
        val window = deleteDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val no: Button
        val yes: Button
        val logout_tv : TextView

        no = deleteDialog.findViewById(R.id.logout_no)
        yes = deleteDialog.findViewById(R.id.logout_yes)
        logout_tv = deleteDialog.findViewById(R.id.logout_tv)
        logout_tv.setText("ARE YOU SURE YOU WANT TO DELETE?")

        no.setOnClickListener {
            deleteDialog.dismiss()
        }

        yes.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {

                if (utility.isConnectingToInternet(this)) {
                    pd.show()
                    pd.setContentView(R.layout.loading)
                    controller.DeleteOutfit("Bearer "+getStringVal(Constants.TOKEN),id)
                } else {
                    utility!!.relative_snackbar(
                        parent_myclosets,
                        R.string.nointernet.toString(),
                        getString(R.string.close_up)
                    )
                }
            } else {
                utility.relative_snackbar(
                    parent_myclosets!!,
                    "No Internet Connectivity",
                    getString(R.string.close_up)
                )
            }
        }

        deleteDialog.show()

    }

    override fun onDeleteOutfitSuccess(success: Response<DeleteOutfitResponse>) {
        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {

                controller.MyOutfits("Bearer " + getStringVal(Constants.TOKEN))
            } else {
                pd.dismiss()
                utility!!.relative_snackbar(
                    parent_outfits,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_outfits,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun getOutfitId(id: String?) {
        controller.FavOutFit("Bearer "+getStringVal(Constants.TOKEN),id,"outfit")
    }
}
