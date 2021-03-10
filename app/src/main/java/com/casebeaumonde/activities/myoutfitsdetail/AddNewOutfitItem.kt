package com.casebeaumonde.activities.myoutfitsdetail

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.addItemtoCLoset.response.AddClosetItemResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_add_item_to_closet.*
import kotlinx.android.synthetic.main.activity_add_item_to_closet.parent_additemtocloset
import kotlinx.android.synthetic.main.activity_add_new_outfit_item.*
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class AddNewOutfitItem : BaseClass(), Controller.AddClosetItemListAPI {
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private lateinit var aditemtocloset_title: EditText
    private lateinit var aditemtocloset_decs: EditText
    private lateinit var additemclosets_categoryspinner: Spinner
    private lateinit var additemclosets__upload: Button
    private lateinit var additemclosets__uploadfilename: TextView
    private lateinit var additemclosets_Sizespinner: Spinner
    private lateinit var additemclosets_Colorpinner: Spinner
    private lateinit var additemclosets_Brandpinner: Spinner
    private lateinit var aditemtocloset_price: EditText
    private lateinit var aditemtocloset_add: Button
    private lateinit var aditemtocloset_cancel: Button
    private lateinit var additemclosets_category: TextView
    private lateinit var additemclosets_brands: TextView
    private lateinit var additemclosets_size: TextView
    private lateinit var additemclosets_color: TextView
    private lateinit var texttitle: TextView
    private lateinit var backon_additemstocloset: ImageButton
    private var path: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_outfit_item)

        findIds()
        listeners()
    }

    private fun listeners() {
        pictureSelectionDialog()
    }

    private fun findIds() {
        controller = Controller()
        controller.Controller(this      )
        controller.AddClosetItemList("Bearer " + getStringVal(Constants.TOKEN))
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        additemclosets__upload = findViewById(R.id.additemclosets__upload)
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
            part = Utility.sendImageFileToserver(filesDir, bitMap, "picture")
            additemclosets__uploadfilename.text = path.toString()

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                parent_addoutfititem!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_addoutfititem,
                "Task Cancelled",
                getString(R.string.close_up)
            )
        }
    }

    override fun onAddClosetItemListSuccess(addClosetItemList: Response<AddClosetItemResponse>) {

    }

    override fun error(error: String?) {

    }
}