package com.casebeaumonde.activities.b_questionaries.b_colorPicker1

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.MainActivity
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.SecondQuestionnaireResponse
import com.casebeaumonde.activities.questionaries.SelectBodyTalk
import com.casebeaumonde.activities.questionaries.colorScreen.ColorAdapter
import com.casebeaumonde.activities.questionaries.colorScreen.SelectColor_IF
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_b__color_picker.*
import kotlinx.android.synthetic.main.activity_select_stores.*
import kotlinx.android.synthetic.main.activity_select_stores.parent_store
import retrofit2.Response

class B_ColorPicker1 : BaseClass(), B_SelectColor_IF1,Controller.SecondQuestonariesAPI {

    var mColors = arrayOf(
        "FFEBEE", "FFCDD2", "EF9A9A", "E57373", "EF5350", "F44336", "E53935",  //reds
        "D32F2F", "C62828", "B71C1C", "FF8A80", "FF5252", "FF1744", "D50000",
        "FCE4EC", "F8BBD0", "F48FB1", "F06292", "EC407A", "E91E63", "D81B60",  //pinks
        "C2185B", "AD1457", "880E4F", "FF80AB", "FF4081", "F50057", "C51162",
        "F3E5F5", "E1BEE7", "CE93D8", "BA68C8", "AB47BC", "9C27B0", "8E24AA",  //purples
        "7B1FA2", "6A1B9A", "4A148C", "EA80FC", "E040FB", "D500F9", "AA00FF",
        "EDE7F6", "D1C4E9", "B39DDB", "9575CD", "7E57C2", "673AB7", "5E35B1",  //deep purples
        "512DA8", "4527A0", "311B92", "B388FF", "7C4DFF", "651FFF", "6200EA",
        "E8EAF6", "C5CAE9", "9FA8DA", "7986CB", "5C6BC0", "3F51B5", "3949AB",  //indigo
        "303F9F", "283593", "1A237E", "8C9EFF", "536DFE", "3D5AFE", "304FFE",
        "E3F2FD", "BBDEFB", "90CAF9", "64B5F6", "42A5F5", "2196F3", "1E88E5",  //blue
        "1976D2", "1565C0", "0D47A1", "82B1FF", "448AFF", "2979FF", "2962FF",
        "E1F5FE", "B3E5FC", "81D4fA", "4fC3F7", "29B6FC", "03A9F4", "039BE5",  //light blue
        "0288D1", "0277BD", "01579B", "80D8FF", "40C4FF", "00B0FF", "0091EA",
        "E0F7FA", "B2EBF2", "80DEEA", "4DD0E1", "26C6DA", "00BCD4", "00ACC1",  //cyan
        "0097A7", "00838F", "006064", "84FFFF", "18FFFF", "00E5FF", "00B8D4",
        "E0F2F1", "B2DFDB", "80CBC4", "4DB6AC", "26A69A", "009688", "00897B",  //teal
        "00796B", "00695C", "004D40", "A7FFEB", "64FFDA", "1DE9B6", "00BFA5",
        "E8F5E9", "C8E6C9", "A5D6A7", "81C784", "66BB6A", "4CAF50", "43A047",  //green
        "388E3C", "2E7D32", "1B5E20", "B9F6CA", "69F0AE", "00E676", "00C853",
        "F1F8E9", "DCEDC8", "C5E1A5", "AED581", "9CCC65", "8BC34A", "7CB342",  //light green
        "689F38", "558B2F", "33691E", "CCFF90", "B2FF59", "76FF03", "64DD17",
        "F9FBE7", "F0F4C3", "E6EE9C", "DCE775", "D4E157", "CDDC39", "C0CA33",  //lime
        "A4B42B", "9E9D24", "827717", "F4FF81", "EEFF41", "C6FF00", "AEEA00",
        "FFFDE7", "FFF9C4", "FFF590", "FFF176", "FFEE58", "FFEB3B", "FDD835",  //yellow
        "FBC02D", "F9A825", "F57F17", "FFFF82", "FFFF00", "FFEA00", "FFD600",
        "FFF8E1", "FFECB3", "FFE082", "FFD54F", "FFCA28", "FFC107", "FFB300",  //amber
        "FFA000", "FF8F00", "FF6F00", "FFE57F", "FFD740", "FFC400", "FFAB00",
        "FFF3E0", "FFE0B2", "FFCC80", "FFB74D", "FFA726", "FF9800", "FB8C00",  //orange
        "F57C00", "EF6C00", "E65100", "FFD180", "FFAB40", "FF9100", "FF6D00",
        "FBE9A7", "FFCCBC", "FFAB91", "FF8A65", "FF7043", "FF5722", "F4511E",  //deep orange
        "E64A19", "D84315", "BF360C", "FF9E80", "FF6E40", "FF3D00", "DD2600",
        "EFEBE9", "D7CCC8", "BCAAA4", "A1887F", "8D6E63", "795548", "6D4C41",  //brown
        "5D4037", "4E342E", "3E2723",
        "FAFAFA", "F5F5F5", "EEEEEE", "E0E0E0", "BDBDBD", "9E9E9E", "757575",  //grey
        "616161", "424242", "212121",
        "ECEFF1", "CFD8DC", "B0BBC5", "90A4AE", "78909C", "607D8B", "546E7A",  //blue grey
        "455A64", "37474F", "263238"
    )
    private lateinit var continue_bt: LinearLayout
    private lateinit var back: ImageButton
    private lateinit var colorsRecycler: RecyclerView
    private lateinit var colorscode: ArrayList<String>

    private lateinit var b_address: String
    private lateinit var b_address1: String
    private lateinit var b_city: String
    private lateinit var state: String
    private lateinit var country: String
    private lateinit var brandID: ArrayList<String>
    private lateinit var basic1: String
    private lateinit var basic2: String
    private lateinit var basic3: String
    private lateinit var basic4: String
    private lateinit var basic5: String
    private lateinit var basic6: String
    private lateinit var basic7: String
    private lateinit var basic8: String
    private lateinit var elusivefashion:String
    private lateinit var fashionsense:String
    private lateinit var personalshopper : String
    private lateinit var yourexp : String
    private lateinit var meaningful : String
    private lateinit var spend:String
    private lateinit var impulseshopper: String
    private lateinit var lovetoshop: String
    private lateinit var drivetoshop: String
    private lateinit var selectedfashion : ArrayList<String>
    private lateinit var likecbm : ArrayList<String>
    private lateinit var favcolors : ArrayList<String>


    private lateinit var pd : ProgressDialog
    private lateinit var controller: Controller
    private lateinit var utility: Utility

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b__color_picker)

        selectcolorIf = this
        colorscode = ArrayList()
        findIDs()
        listeners()
    }

    companion object {
        lateinit var selectcolorIf: B_SelectColor_IF1
    }

    private fun listeners() {


        continue_bt.setOnClickListener {
            if (colorscode.size == 0) {
                Toast.makeText(this, "Select atleast one color", Toast.LENGTH_SHORT).show()
            } else {
                   Log.d("testB",b_address+"\n"+b_address1+"\n"+b_city+"\n"+state+"\n"+country+"\n"+brandID+"\n"+basic1+"\n"+basic2+"\n"+basic3+"\n"+basic4+"\n"+basic5+"\n"+basic6+"\n"+basic7+"\n"+basic8+
                   "\n"+elusivefashion+"\n"+fashionsense+"\n"+personalshopper+"\n"+yourexp+"\n"+meaningful+"\n"+spend+"\n"+impulseshopper+"\n"+lovetoshop+"\n"+drivetoshop+"\n"+selectedfashion+"\n"+likecbm+"\n"+favcolors+"\n"+colorscode)

                if (utility.isConnectingToInternet(this))
                {
                    pd.show()
                    pd.setContentView(R.layout.loading)
                    controller.SecondQuestonaries("Bearer "+getStringVal(Constants.TOKEN),brandID,b_address,
                    b_address1,
                    b_city,
                    state,
                    country,
                    basic1,
                    basic2,basic3,
                    basic4,
                        basic5,
                    basic6,
                    basic7,
                    basic8,elusivefashion,
                    fashionsense,
                    personalshopper,
                    yourexp,
                    meaningful,
                    spend,
                    impulseshopper,
                    lovetoshop,
                    drivetoshop,
                    selectedfashion,
                    likecbm,
                    favcolors,
                    colorscode,
                        getStringVal(Constants.USERID)!!
                    )
                } else {
                    utility.relative_snackbar(
                        parent_second!!,
                        "No Internet Connectivity",
                        getString(R.string.close_up)
                    )
                }
            }

        }

        back.setOnClickListener { onBackPressed() }
    }

    private fun findIDs() {
        continue_bt = findViewById(R.id.b_continue_bt)
        back = findViewById(R.id.back)
        colorsRecycler = findViewById(R.id.colorsRecycler)

        b_address = intent.getStringExtra("b_address").toString()
        b_address1 = intent.getStringExtra("b_address1").toString()
        b_city = intent.getStringExtra("b_city").toString()
        state = intent.getStringExtra("state").toString()
        country = intent.getStringExtra("country").toString()
        brandID = intent.getStringArrayListExtra("brandID")!!
        basic1 = intent.getStringExtra("basic1").toString()
        basic2 = intent.getStringExtra("basic2").toString()
        basic3 = intent.getStringExtra("basic3").toString()
        basic4 = intent.getStringExtra("basic4").toString()
        basic5 = intent.getStringExtra("basic5").toString()
        basic6 = intent.getStringExtra("basic6").toString()
        basic7 = intent.getStringExtra("basic7").toString()
        basic8 = intent.getStringExtra("basic8").toString()
        elusivefashion = intent.getStringExtra("elusivefashion").toString()
        fashionsense = intent.getStringExtra("fashionsense").toString()
        personalshopper = intent.getStringExtra("personalshopper").toString()
        yourexp = intent.getStringExtra("yourexp").toString()
        meaningful = intent.getStringExtra("meaningful").toString()
        spend = intent.getStringExtra("spend").toString()
        impulseshopper = intent.getStringExtra("impulseshopper").toString()
        lovetoshop = intent.getStringExtra("lovetoshop").toString()
        drivetoshop = intent.getStringExtra("drivetoshop").toString()
        selectedfashion = intent.getStringArrayListExtra("fashionevents")!!
        likecbm = intent.getStringArrayListExtra("likecbm")!!
        favcolors = intent.getStringArrayListExtra("favcolors")!!

        utility = Utility()
        controller = Controller()
        controller.Controller(this)
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)


        colorsRecycler.layoutManager = GridLayoutManager(this, 5)
        val adapter = B_ColorAdapter1(this, mColors)
        colorsRecycler.adapter = adapter
    }

    override fun getID(id: String?, s: String?) {
        if (s.equals("0")) {
            if (colorscode.contains(id)) {
                colorscode.remove(id)
            }
        } else {
            colorscode.add(id.toString())
            Log.d("yourcolors", "" + colorscode)
        }
    }

    override fun onSecondQuestionaries(success: Response<SecondQuestionnaireResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.body()?.code.equals("200"))
            {
                startActivity(Intent(this,MainActivity::class.java))
                setStringVal(Constants.QUESTIONARIES_STATUS,"1")
            } else {
                utility.relative_snackbar(
                    parent_second!!,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        }else {
            utility.relative_snackbar(
                parent_second!!,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_second!!,
            error,
            getString(R.string.close_up)
        )
    }
}