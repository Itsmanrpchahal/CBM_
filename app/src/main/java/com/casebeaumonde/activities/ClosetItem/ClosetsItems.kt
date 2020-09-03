package com.casebeaumonde.activities.ClosetItem

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.IF.ClosetItemID_IF
import com.casebeaumonde.activities.ClosetItem.response.AddToFavClosetItemResponse
import com.casebeaumonde.activities.ClosetItem.response.ClosetsItemsResponse
import com.casebeaumonde.activities.ClosetItm.adapter.ClosetsItemAdapter
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import kotlinx.android.synthetic.main.activity_closets_items.*
import kotlinx.android.synthetic.main.additemtoclosets.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class ClosetsItems : BaseClass(),Controller.ClosetItemsAPI, ClosetItemID_IF ,Controller.AddTofavClosetItemAPI{

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var closetID : String
    private lateinit var closetsItems_recycler : RecyclerView
    private lateinit var closetitems_back : ImageButton
    private lateinit var closetiems_add : ImageButton
    private lateinit var hearlist : ArrayList<ClosetsItemsResponse.Data.Closet.Item.Heart>
    private lateinit var closetResponse : ArrayList<ClosetsItemsResponse>
    private lateinit var dialog :Dialog
    private var path: String = ""
    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private lateinit var aditemtocloset_title : EditText
    private lateinit var aditemtocloset_decs : EditText
    private lateinit var additemclosets_categoryspinner : SearchableSpinner
    private lateinit var additemclosets__upload : Button
    private lateinit var additemclosets__uploadfilename : TextView
    private lateinit var additemclosets_Sizespinner : SearchableSpinner
    private lateinit var additemclosets_Colorpinner : SearchableSpinner
    private lateinit var additemclosets_Brandpinner : SearchableSpinner
    private lateinit var aditemtocloset_price : EditText
    private lateinit var aditemtocloset_add : Button
    private lateinit var aditemtocloset_cancel : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_closets_items)
        findIds()
        controller = Controller()
        controller.Controller(this,this)
        closetID = intent?.getStringExtra(Constants.CLOSETID).toString()
       setClosetAPI()
        closetitemidIf = this
        listeners()

    }

    private fun listeners() {
        closetitems_back.setOnClickListener {
            onBackPressed()
        }

        closetiems_add.setOnClickListener {

            addItemToCloset()
        }
    }

    private fun addItemToCloset() {
        dialog = Dialog(this!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(R.layout.additemtoclosets)
        findAddClosetItemIDs(dialog)
        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        AddClosetsLisetners(dialog)

        dialog.show()
    }

    private fun AddClosetsLisetners(dialog: Dialog) {
        dialog.additemclosets__upload.setOnClickListener {
            pictureSelectionDialog()
        }


        dialog.aditemtocloset_cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun findAddClosetItemIDs(dialog: Dialog) {
        aditemtocloset_title = dialog.findViewById(R.id.aditemtocloset_title)
        aditemtocloset_decs = dialog.findViewById(R.id.aditemtocloset_decs)
        additemclosets_categoryspinner = dialog.findViewById(R.id.additemclosets_categoryspinner)
        additemclosets__upload = dialog.findViewById(R.id.additemclosets__upload)
        additemclosets__uploadfilename = dialog.findViewById(R.id.additemclosets__uploadfilename)
        additemclosets_Sizespinner = dialog.findViewById(R.id.additemclosets_Sizespinner)
        additemclosets_Colorpinner = dialog.findViewById(R.id.additemclosets_Colorpinner)
        additemclosets_Brandpinner = dialog.findViewById(R.id.additemclosets_Brandpinner)
        aditemtocloset_price = dialog.findViewById(R.id.aditemtocloset_price)
        aditemtocloset_cancel = dialog.findViewById(R.id.aditemtocloset_cancel)
        aditemtocloset_add = dialog.findViewById(R.id.aditemtocloset_add)
    }

    private fun setClosetAPI() {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.ClosetItems("Bearer "+getStringVal(Constants.TOKEN),closetID)

        }else {
            utility.relative_snackbar(parent_closetsItems!!, "No Internet Connectivity", getString(R.string.close_up))
        }
    }

    companion object{
        var closetitemidIf : ClosetItemID_IF? = null
    }


    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        closetsItems_recycler = findViewById(R.id.closetsItems_recycler)
        closetitems_back = findViewById(R.id.closetitems_back)
        closetiems_add = findViewById(R.id.closetiems_add)
    }


    override fun onClosetItemSuccess(closetItemsResponse: Response<ClosetsItemsResponse>) {
        pd.dismiss()
        if (closetItemsResponse.isSuccessful)
        {
            closetsItems_recycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            val adapter = getStringVal(Constants.USERID)?.let {
                ClosetsItemAdapter(this, closetItemsResponse.body()?.data?.closet?.items!!,
                    it
                )
            }
            closetsItems_recycler.adapter = adapter

        }else{
            utility!!.relative_snackbar(parent_closetsItems!!, closetItemsResponse.message(), getString(R.string.close_up))
        }
    }

    override fun onAddToFavClosetItemSuccess(addToFavClosetItemResponse: Response<AddToFavClosetItemResponse>) {
       if (addToFavClosetItemResponse.isSuccessful)
       {
           pd.dismiss()
       }else{
           pd.dismiss()
           recreate()
           utility!!.relative_snackbar(parent_closetsItems!!, addToFavClosetItemResponse.message(), getString(R.string.close_up))
       }
    }


    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(parent_closetsItems!!, error, getString(R.string.close_up))
    }

    override fun getClosetID(id: String?) {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.AddToFavClosetItem("Bearer "+getStringVal(Constants.TOKEN),id,"closet_item")

        }else {
            utility.relative_snackbar(parent_closetsItems!!, "No Internet Connectivity", getString(R.string.close_up))
        }
    }

    private fun pictureSelectionDialog() {

        val camera: LinearLayout
        val gallery: LinearLayout
        val dialog = Dialog(this!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.imagepicker)
        camera = dialog.findViewById(R.id.linear_camera) as LinearLayout
        gallery = dialog.findViewById(R.id.linear_gallery) as LinearLayout

        camera.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .crop()         //User can only capture image using Camera
                .start()
            dialog.dismiss()
        }

        gallery.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .crop()     //User can only select image from Gallery
                .start()    //Default Request Code is ImagePicker.REQUEST_CODE
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data

            //You can get File object from intent
            var file: File? = ImagePicker.getFile(data)
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)

            path = filePath!!
            bitMap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
            part = Utility.sendImageFileToserver(filesDir, bitMap)
            additemclosets__uploadfilename.text = path.toString()

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                parent_main!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_main,
                "Task Cancelled",
                getString(R.string.close_up)
            )
        }
    }
}