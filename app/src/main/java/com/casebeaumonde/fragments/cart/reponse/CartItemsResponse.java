package com.casebeaumonde.fragments.cart.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartItemsResponse {

@SerializedName("code")
@Expose
private String code;
@SerializedName("data")
@Expose
private Data data;

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

    public class Data {

        @SerializedName("cart_items")
        @Expose
        private List<CartItem> cartItems = null;
        @SerializedName("closet")
        @Expose
        private List<Closet> closet = null;

        public List<CartItem> getCartItems() {
            return cartItems;
        }

        public void setCartItems(List<CartItem> cartItems) {
            this.cartItems = cartItems;
        }

        public List<Closet> getCloset() {
            return closet;
        }

        public void setCloset(List<Closet> closet) {
            this.closet = closet;
        }

        public class CartItem {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("price")
            @Expose
            private String price;
            @SerializedName("msrp")
            @Expose
            private String msrp;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("discount_percent")
            @Expose
            private Integer discountPercent;
            @SerializedName("total_qty")
            @Expose
            private String totalQty;
            @SerializedName("qty")
            @Expose
            private Integer qty;
            @SerializedName("thumb_path")
            @Expose
            private Object thumbPath;
            @SerializedName("package_type")
            @Expose
            private String packageType;
            @SerializedName("weight")
            @Expose
            private String weight;
            @SerializedName("width")
            @Expose
            private Integer width;
            @SerializedName("height")
            @Expose
            private Integer height;
            @SerializedName("length")
            @Expose
            private Integer length;
            @SerializedName("taxable")
            @Expose
            private Integer taxable;
            @SerializedName("tax_percent")
            @Expose
            private Object taxPercent;
            @SerializedName("sku")
            @Expose
            private String sku;
            @SerializedName("options")
            @Expose
            private List<Object> options = null;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("color")
            @Expose
            private String color;
            @SerializedName("brand")
            @Expose
            private String brand;
            @SerializedName("size")
            @Expose
            private String size;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getMsrp() {
                return msrp;
            }

            public void setMsrp(String msrp) {
                this.msrp = msrp;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Integer getDiscountPercent() {
                return discountPercent;
            }

            public void setDiscountPercent(Integer discountPercent) {
                this.discountPercent = discountPercent;
            }

            public String getTotalQty() {
                return totalQty;
            }

            public void setTotalQty(String totalQty) {
                this.totalQty = totalQty;
            }

            public Integer getQty() {
                return qty;
            }

            public void setQty(Integer qty) {
                this.qty = qty;
            }

            public Object getThumbPath() {
                return thumbPath;
            }

            public void setThumbPath(Object thumbPath) {
                this.thumbPath = thumbPath;
            }

            public String getPackageType() {
                return packageType;
            }

            public void setPackageType(String packageType) {
                this.packageType = packageType;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public Integer getWidth() {
                return width;
            }

            public void setWidth(Integer width) {
                this.width = width;
            }

            public Integer getHeight() {
                return height;
            }

            public void setHeight(Integer height) {
                this.height = height;
            }

            public Integer getLength() {
                return length;
            }

            public void setLength(Integer length) {
                this.length = length;
            }

            public Integer getTaxable() {
                return taxable;
            }

            public void setTaxable(Integer taxable) {
                this.taxable = taxable;
            }

            public Object getTaxPercent() {
                return taxPercent;
            }

            public void setTaxPercent(Object taxPercent) {
                this.taxPercent = taxPercent;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public List<Object> getOptions() {
                return options;
            }

            public void setOptions(List<Object> options) {
                this.options = options;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

        }

        public class Closet {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("creator")
            @Expose
            private Object creator;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getCreator() {
                return creator;
            }

            public void setCreator(Object creator) {
                this.creator = creator;
            }

        }
    }
}