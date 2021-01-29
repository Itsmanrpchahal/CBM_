package com.casebeaumonde.fragments.cart.reponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CartItemsResponse {

    @SerializedName("code")
    @Expose
    private var code: String? = null

    @SerializedName("data")
    @Expose
    private var data: Data? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

    class Data {
        @SerializedName("cart_items")
        @Expose
        var cartItems: List<CartItem>? = null

        @SerializedName("closet")
        @Expose
        var closet: List<Closet>? = null

        inner class CartItem {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("price")
            @Expose
            var price: String? = null

            @SerializedName("msrp")
            @Expose
            var msrp: String? = null

            @SerializedName("name")
            @Expose
            var name: String? = null

            @SerializedName("description")
            @Expose
            var description: String? = null

            @SerializedName("discount_percent")
            @Expose
            var discountPercent: Int? = null

            @SerializedName("total_qty")
            @Expose
            var totalQty: String? = null

            @SerializedName("qty")
            @Expose
            var qty: Int? = null

            @SerializedName("thumb_path")
            @Expose
            var thumbPath: Any? = null

            @SerializedName("package_type")
            @Expose
            var packageType: String? = null

            @SerializedName("weight")
            @Expose
            var weight: String? = null

            @SerializedName("width")
            @Expose
            var width: Int? = null

            @SerializedName("height")
            @Expose
            var height: Int? = null

            @SerializedName("length")
            @Expose
            var length: Int? = null

            @SerializedName("taxable")
            @Expose
            var taxable: Int? = null

            @SerializedName("tax_percent")
            @Expose
            var taxPercent: Any? = null

            @SerializedName("sku")
            @Expose
            var sku: String? = null

            @SerializedName("options")
            @Expose
            var options: List<Any>? = null

            @SerializedName("image")
            @Expose
            var image: String? = null

            @SerializedName("color")
            @Expose
            var color: String? = null

            @SerializedName("brand")
            @Expose
            var brand: String? = null

            @SerializedName("size")
            @Expose
            var size: String? = null
        }

        inner class Closet {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("title")
            @Expose
            var title: String? = null

            @SerializedName("creator")
            @Expose
            var creator: Any? = null
        }
    }
}