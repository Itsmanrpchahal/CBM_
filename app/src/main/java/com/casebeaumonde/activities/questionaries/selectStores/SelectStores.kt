package com.casebeaumonde.activities.questionaries.selectStores

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.MainActivity
import com.casebeaumonde.R
import com.casebeaumonde.activities.questionaries.BasicQuestionariesResponse
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.constants.Data
import com.casebeaumonde.utilities.Utility
import fastscroll.app.fastscrollalphabetindex.AlphabetIndexFastScrollRecyclerView
import kotlinx.android.synthetic.main.activity_describe_yourself.*
import kotlinx.android.synthetic.main.activity_select_brands.*
import kotlinx.android.synthetic.main.activity_select_stores.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Response
import java.io.*
import java.net.URL
import java.net.URLConnection
import java.util.*
import kotlin.collections.ArrayList

class SelectStores : BaseClass() ,SelectedStores_IF, Controller.QuestionariesAPI,Controller.SendBasicQuestionariesAPI{
    private lateinit var dataList : ArrayList<Data>
    private lateinit var recyclerView : AlphabetIndexFastScrollRecyclerView
    private lateinit var pd : ProgressDialog
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var search_ET: EditText
    private lateinit var back : ImageButton
    private lateinit var stores : ArrayList<String>
    private lateinit var filterStores : ArrayList<String>
    private lateinit var selectedStores : ArrayList<String>
    private lateinit var continue_bt : LinearLayout
    private var name: String = ""
    private var city: String = ""
    private var state: String = ""
    private var country: String = ""
    private var mobile: String = ""
    private var age: String = ""
    private var month: String = ""
    private var date: String = ""
    private var year: String = ""
    private var astrologicalSign: String = ""
    private var bodyType : String = ""
    private lateinit var colorscode : ArrayList<String>
    private var bodyType_text : String = ""
    private var height : String =""
    private var eycolor : String =""
    private var haircolor : String =""
    private lateinit var brandsID : ArrayList<String>
    private lateinit var images : ArrayList<String>
    private lateinit var yourself : ArrayList<String>
    private lateinit var imageData : ArrayList<String>
    var part : ArrayList<okhttp3.MultipartBody.Part> = ArrayList()
    private lateinit var bitMap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_stores)

        dataList = java.util.ArrayList()
        selectedStores = ArrayList()
        findIds()
        selectedstoresIf = this
        listeners()
        Collections.sort(dataList, Data.titleNameComparator)
        recyclerView.layoutManager = GridLayoutManager(this,2)
//        val adapter =
//            DataAdapter(
//                dataList,
//                this
//            )
//        recyclerView.adapter = adapter
//        recyclerView.setIndexTextSize(12)
//        recyclerView.setIndexBarTextColor("#FFFFFF")
//        recyclerView.setIndexBarColor("#cdced2")
//        recyclerView.setIndexbarHighLateTextColor("#FF4081")
//        recyclerView.setIndexBarHighLateTextVisibility(true)
//        recyclerView.setIndexBarTransparentValue(1.0.toFloat())
    }

    private fun listeners() {
        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle(s.toString())
                } else {
                    setFullData(stores)
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle(s.toString())
                } else {
                    recyclerView.visibility = View.VISIBLE
                    setFullData(filterStores)
                }


            }

        })

        back.setOnClickListener {
            onBackPressed()
        }

        continue_bt.setOnClickListener {
            if (utility.isConnectingToInternet(this))
            {
                pd.show()
                pd.setContentView(R.layout.loading)
                controller.BasicQuestions("Bearer "+getStringVal(Constants.TOKEN),name,city,state,country,mobile,age,month,date,year,astrologicalSign,colorscode,yourself,bodyType_text,bodyType,part,height,eycolor,haircolor,brandsID,"1",
                    getStringVal(Constants.USERID).toString()
                )
            } else {
                utility.relative_snackbar(
                    parent_store!!,
                    "No Internet Connectivity",
                    getString(R.string.close_up)
                )
            }
        }
    }

    companion object {
        lateinit var selectedstoresIf: SelectedStores_IF
    }

    private fun setFullData(closets: ArrayList<String>) {
        recyclerView.layoutManager = GridLayoutManager(this,3)
        //user_recyler.addItemDecoration(GridItemDecoration(10, 2))
        val adapter = DataAdapter1(closets,this)
        recyclerView.adapter = adapter
    }

    fun searchByTitle(s: String) {

        filterStores = ArrayList()
        //filterBrand = ArrayList()
        if (stores.size > 0) {
            for (i in stores!!.indices) {
                val closetModel = stores!![i]
                if (closetModel!!.toLowerCase().contains(s.toLowerCase()))
                    filterStores!!.add(closetModel)

                if (stores.size > 0) {
                    recyclerView.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    //closets =  response
                    recyclerView.layoutManager = GridLayoutManager(this, 2)
                    val adapter =
                        DataAdapter1(
                            filterStores,
                            this
                        )
                    recyclerView.adapter = adapter
                }
            }
            if (stores.size == 0) {
                recyclerView.visibility = View.GONE
            }
        }
    }

    private fun findIds() {
        recyclerView = findViewById(R.id.recyclerView)
        search_ET = findViewById(R.id.search_ET)
        back = findViewById(R.id.back)
        continue_bt = findViewById(R.id.continue_bt)
        utility = Utility()
        controller = Controller()
        controller.Controller(this,this)
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        if (utility.isConnectingToInternet(this))
        {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.Questionaries("Bearer "+getStringVal(Constants.TOKEN))
        } else {
            utility.relative_snackbar(
                parent_store!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }

        name = intent.getStringExtra("name").toString()
        city = intent.getStringExtra("city").toString()
        state = intent.getStringExtra("state").toString()
        country = intent.getStringExtra("country").toString()
        mobile = intent.getStringExtra("mobile").toString()
        age = intent.getStringExtra("age").toString()
        month = intent.getStringExtra("month").toString()
        date = intent.getStringExtra("date").toString()
        year = intent.getStringExtra("year").toString()
        astrologicalSign = intent.getStringExtra("astrologicalSign").toString()
        colorscode = intent.getStringArrayListExtra("colorscode")!!
        bodyType = intent.getStringExtra("bodyType").toString()
        bodyType_text = intent.getStringExtra("bodyType_text").toString()
        brandsID = intent.getStringArrayListExtra("brandsID")!!
        images = intent.getStringArrayListExtra("images")!!
        yourself= intent.getStringArrayListExtra("yourself")!!
        imageData = intent.getStringArrayListExtra("imageData")!!

        for (i in 0 until imageData.size)
        {

            bitMap = MediaStore.Images.Media.getBitmap(this.contentResolver, Uri.parse(imageData.get(i)))
            part.add(sendImageFileToserver(bitMap))
        }

        Log.d("images",""+part)
        Log.d("questionariesdata",name+"\n"+city+"\n"+state+"\n"+country+"\n"+mobile+"\n"+age+"\n"+month+"\n"+date+"\n"+year+"\n"+astrologicalSign+"\n"+colorscode+"\n"+bodyType+"\n"+bodyType_text+"\n"+brandsID+"\n"+images+"\n"+yourself)
    }



    override fun onQuestionariesSuccess(questionaries: Response<QuestionariesDataResponse>) {
        pd.dismiss()
        stores = ArrayList()
        if (questionaries.isSuccessful)
        {
            for (i in 0 until questionaries.body()?.data?.customer?.stores?.size!!) {
                val title = questionaries.body()?.data?.customer?.stores?.get(i)
                stores.add(title.toString())
            }
            setFullData(stores)
        } else {
            utility.relative_snackbar(
                parent_store!!,
                questionaries.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onBasicQuestionariesAPI(basicQuestionaries: Response<BasicQuestionariesResponse>) {
        pd.dismiss()
        if (basicQuestionaries.isSuccessful)
        {
            if (basicQuestionaries.body()?.code.equals("200"))
            {
                setStringVal(Constants.QUESTIONARIES_STATUS,"1")
            }
        } else {
            utility.relative_snackbar(
                parent_store!!,
                basicQuestionaries.message(),
                getString(R.string.close_up)
            )
        }
        setStringVal(Constants.QUESTIONARIES_STATUS,"1")
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_store!!,
            error,
            getString(R.string.close_up)
        )
    }

    override fun getID(id: String?, s: String?) {
        if (s.equals("0")) {
            if (selectedStores.contains(id)) {
                selectedStores.remove(id)
            }
        } else {
            selectedStores.add(id.toString())
            Log.d("selectStores", "" + selectedStores)
        }
    }

    @Throws(IOException::class)
    fun sendImageFileToserver(bitMap: Bitmap): okhttp3.MultipartBody.Part {
        val filesDir = applicationContext.filesDir
        val file = File(filesDir, "image" + ".png" + System.currentTimeMillis())
        val bos = ByteArrayOutputStream()
        bitMap.compress(Bitmap.CompressFormat.PNG, 50, bos)
        val bitmapdata: ByteArray = bos.toByteArray()
        val fos = FileOutputStream(file)
        fos.write(bitmapdata)
        fos.flush()
        fos.close()
        val requestFile: RequestBody =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), saveBitmapToFile(file)!!)
        return okhttp3.MultipartBody.Part.createFormData(
            "photo",
            saveBitmapToFile(file)?.getName(),
            requestFile
        )
    }

    fun saveBitmapToFile(file: File): File? {
        return try {

            // BitmapFactory options to downsize the image
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            o.inSampleSize = 6
            // factor of downsizing the image
            var inputStream = FileInputStream(file)
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o)
            inputStream.close()

            // The new size we want to scale to
            val REQUIRED_SIZE = 50

            // Find the correct scale value. It should be the power of 2.
            var scale = 1
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                o.outHeight / scale / 2 >= REQUIRED_SIZE
            ) {
                scale *= 2
            }
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            inputStream = FileInputStream(file)
            val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
            inputStream.close()

            // here i override the original image file
            file.createNewFile()
            val outputStream = FileOutputStream(file)
            selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
            file
        } catch (e: Exception) {
            null
        }
    }

    fun loadBitmap(url: String?): Bitmap? {
        var bm: Bitmap? = null
        var `is`: InputStream? = null
        var bis: BufferedInputStream? = null
        try {
            val conn: URLConnection = URL(url).openConnection()
            conn.connect()
            `is` = conn.getInputStream()
            bis = BufferedInputStream(`is`, 8192)
            bm = BitmapFactory.decodeStream(bis)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            if (bis != null) {
                try {
                    bis.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (`is` != null) {
                try {
                    `is`.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return bm
    }
}