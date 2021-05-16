package com.casebeaumonde.activities.myoutfitsdetail

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myoutfits.IF.OutfitID_IF
import com.casebeaumonde.activities.myoutfitsdetail.IF.OutfitFavID_IF
import com.casebeaumonde.activities.myoutfitsdetail.adapter.MyOutfitItemsAdapter
import com.casebeaumonde.activities.myoutfitsdetail.response.DeleteOutfitItemResponse
import com.casebeaumonde.activities.myoutfitsdetail.response.FavOutfitResponse
import com.casebeaumonde.activities.myoutfitsdetail.response.MyOutfitsDetailResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_my_outfits.*
import kotlinx.android.synthetic.main.activity_my_outfits_items.*
import retrofit2.Response

class MyOutfitsItems : BaseClass(), Controller.MyOutfitsItemsAPI, OutfitID_IF,
    Controller.DeleteOutfitItemAPI,OutfitFavID_IF ,Controller.FavOutfitAPI{

    private lateinit var userID: String
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var outfitsitems_back: ImageButton
    private lateinit var closetiems_add : ImageButton
    private lateinit var outfitItems_recycler: RecyclerView
    private lateinit var outfits: ArrayList<MyOutfitsDetailResponse.Data.Outfit.Item>
    private var outfitID: String = ""
    private var creatorID: String = ""
    private lateinit var deleteDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_outfits_items)

        findIds()
        listeners()

    }

    private fun listeners() {
        outfitsitems_back.setOnClickListener { onBackPressed() }
        closetiems_add.setOnClickListener {
            startActivity(Intent(this,AddNewOutfitItem::class.java).putExtra("outfitID",outfitID))
        }
    }

    override fun onResume() {
        super.onResume()
        if (utility.isConnectingToInternet(this))
        {
            pd!!.show()
            pd!!.setContentView(R.layout.loading)
            controller.MyOutfitsItems("Bearer " + getStringVal(Constants.TOKEN), outfitID)
        } else {
            utility!!.relative_snackbar(
                parent_outfitsitems,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    private fun findIds() {
        outfitidIf = this
        outfitID = intent.getStringExtra(Constants.OUTFITID).toString()
        userID = intent.getStringExtra("userID").toString()
        creatorID = intent.getStringExtra("creatorID").toString()
        outfitsitems_back = findViewById(R.id.outfitsitems_back)
        outfitItems_recycler = findViewById(R.id.outfitItems_recycler)
        closetiems_add = findViewById(R.id.closetiems_add)
        controller = Controller()
        controller.Controller(this, this,this)

        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)


        outfitfavidIf  =  this

        if(userID.equals(getStringVal(Constants.USERID)))
        {
            closetiems_add.visibility = View.VISIBLE
        }

    }

    override fun onMyOutfitItemSuccess(success: Response<MyOutfitsDetailResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            outfits =
                success.body()?.data?.outfit?.items as ArrayList<MyOutfitsDetailResponse.Data.Outfit.Item>
            setFullData(outfits)
        } else {
            utility!!.relative_snackbar(
                parent_outfitsitems,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    private fun setFullData(outfits: java.util.ArrayList<MyOutfitsDetailResponse.Data.Outfit.Item>) {
        outfitItems_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = MyOutfitItemsAdapter(
            this, outfits, userID
        )
        outfitItems_recycler.adapter = adapter
    }

    override fun onFavOutfitSuccess(success: Response<FavOutfitResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
           // controller.MyOutfitsItems("Bearer " + getStringVal(Constants.TOKEN), outfitID)
        } else {
            utility!!.relative_snackbar(
                parent_outfitsitems,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }


    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_outfitsitems,
            error,
            getString(R.string.close_up)
        )
    }

    companion object {
        var outfitidIf: OutfitID_IF? = null
        var outfitfavidIf : OutfitFavID_IF? = null
    }

    override fun getClosetID(ID: String?, pos: String?) {

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
        val text: TextView

        no = deleteDialog.findViewById(R.id.logout_no)
        yes = deleteDialog.findViewById(R.id.logout_yes)
        text = deleteDialog.findViewById(R.id.logout_tv)
        text.text = "Are you sure you want to delete it?"

        no.setOnClickListener {
            deleteDialog.dismiss()
        }

        yes.setOnClickListener {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.DeleteOutfitItem("Bearer "+getStringVal(Constants.TOKEN),ID)
        }
        deleteDialog.show()


    }

    override fun onDeleteOnfitItemSuccess(success: Response<DeleteOutfitItemResponse>) {
        deleteDialog.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {
                controller.MyOutfitsItems("Bearer " + getStringVal(Constants.TOKEN), outfitID)
            } else {
                utility!!.relative_snackbar(
                    parent_outfitsitems,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility!!.relative_snackbar(
                parent_outfitsitems,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun getOutfitId(id: String?) {
       // Toast.makeText(this,""+id,Toast.LENGTH_SHORT).show()
        controller.FavOutFit("Bearer "+getStringVal(Constants.TOKEN),id,"outfit_item")
    }
}