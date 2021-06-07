package com.casebeaumonde.activities.questionaries.uploadBodyType.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.casebeaumonde.R
import com.casebeaumonde.fragments.productManagement.addproduct.AddNewProduct
import kotlinx.android.synthetic.main.customphoto.view.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.*
import java.net.URL
import java.net.URLConnection

class AddImagesAdapter(var context: Context, var list: ArrayList<String?>,var type:String) :
    RecyclerView.Adapter<AddImagesAdapter.ViewHolder>() {

    lateinit var part: ArrayList<MultipartBody.Part>
    lateinit var bitmap: Bitmap
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddImagesAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.customphoto, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AddImagesAdapter.ViewHolder, position: Int) {
        part = ArrayList()
        Glide.with(context).asBitmap().load(list.get(position))
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                ) {

                    holder.itemView.add_image.setImageBitmap(resource)
                    Log.d("image", "" + resource)
                    //createcloset_uploadfilename.text = resource.toString()
                    bitmap = resource
                    part.add(sendImageFileToserver(bitmap)!!)
                    Log.d("bitmap", "" + part.size)

                    AddNewProduct.getImagesIF?.getImages(part)


                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    Log.d("loadcleared", "" + placeholder)
                }
            })

        // Glide.with(context).load(list.get(position)).placeholder(R.drawable.login_banner1).into(holder.itemView.add_image)

        if (type.equals("edit"))
        {
            holder.itemView.delete_image.visibility = View.GONE
        }
        holder.itemView.delete_image.setOnClickListener {
            val position: Int = holder.getAdapterPosition()
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, list.size)
        }
    }

    @Throws(IOException::class)
    fun sendImageFileToserver(bitMap: Bitmap): MultipartBody.Part? {
        val filesDir = context.filesDir
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
        return MultipartBody.Part.createFormData(
            "product_images[]",
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


    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var add_image: ImageView
        lateinit var delete_image: ImageView
        fun bindItems() {
            add_image = itemView.findViewById(R.id.add_image)
            delete_image = itemView.findViewById(R.id.delete_image)
        }
    }
}