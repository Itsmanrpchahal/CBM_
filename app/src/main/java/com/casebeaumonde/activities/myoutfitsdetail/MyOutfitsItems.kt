package com.casebeaumonde.activities.myoutfitsdetail

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myoutfits.IF.OutfitID_IF
import com.casebeaumonde.activities.myoutfitsdetail.adapter.MyOutfitItemsAdapter
import com.casebeaumonde.activities.myoutfitsdetail.response.DeleteOutfitItemResponse
import com.casebeaumonde.activities.myoutfitsdetail.response.MyOutfitsDetailResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_my_outfits.*
import kotlinx.android.synthetic.main.activity_my_outfits_items.*
import retrofit2.Response

class MyOutfitsItems : BaseClass(), Controller.MyOutfitsItemsAPI, OutfitID_IF,
    Controller.DeleteOutfitItemAPI {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_outfits_items)

        findIds()
        listeners()
    }

    private fun listeners() {
        outfitsitems_back.setOnClickListener { onBackPressed() }
        closetiems_add.setOnClickListener {
            startActivity(Intent(this,AddNewOutfitItem::class.java))
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
        controller.Controller(this, this)
        controller.MyOutfitsItems("Bearer " + getStringVal(Constants.TOKEN), outfitID)
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        pd!!.show()
        pd!!.setContentView(R.layout.loading)

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
    }

    override fun getClosetID(ID: String?, pos: String?) {
        pd.show()
        pd.setContentView(R.layout.loading)
        controller.DeleteOutfitItem("Bearer "+getStringVal(Constants.TOKEN),ID)
    }

    override fun onDeleteOnfitItemSuccess(success: Response<DeleteOutfitItemResponse>) {
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
}