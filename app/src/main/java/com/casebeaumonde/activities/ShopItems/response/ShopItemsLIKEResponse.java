package com.casebeaumonde.activities.ShopItems.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopItemsLIKEResponse {

@SerializedName("code")
@Expose
private String code;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private Data data;

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

    public class Data {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("account_id")
        @Expose
        private Integer accountId;
        @SerializedName("creator_user_id")
        @Expose
        private Integer creatorUserId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("short_name")
        @Expose
        private String shortName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("category_id")
        @Expose
        private Integer categoryId;
        @SerializedName("source_type")
        @Expose
        private Object sourceType;
        @SerializedName("source_id")
        @Expose
        private Object sourceId;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("taxable")
        @Expose
        private Integer taxable;
        @SerializedName("tax_percent")
        @Expose
        private Object taxPercent;
        @SerializedName("cost")
        @Expose
        private Integer cost;
        @SerializedName("discount_percent")
        @Expose
        private Integer discountPercent;
        @SerializedName("total_qty")
        @Expose
        private String totalQty;
        @SerializedName("msrp")
        @Expose
        private String msrp;
        @SerializedName("mpn")
        @Expose
        private String mpn;
        @SerializedName("um")
        @Expose
        private Object um;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("weight_unit")
        @Expose
        private String weightUnit;
        @SerializedName("size")
        @Expose
        private Integer size;
        @SerializedName("color")
        @Expose
        private Integer color;
        @SerializedName("brand")
        @Expose
        private Integer brand;
        @SerializedName("barcode")
        @Expose
        private Object barcode;
        @SerializedName("length_unit")
        @Expose
        private String lengthUnit;
        @SerializedName("height_unit")
        @Expose
        private String heightUnit;
        @SerializedName("width_unit")
        @Expose
        private String widthUnit;
        @SerializedName("package_type")
        @Expose
        private List<Object> packageType = null;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("length")
        @Expose
        private Integer length;
        @SerializedName("last_sku")
        @Expose
        private Integer lastSku;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("upc")
        @Expose
        private String upc;
        @SerializedName("sku")
        @Expose
        private String sku;
        @SerializedName("active")
        @Expose
        private Integer active;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("set_id")
        @Expose
        private Object setId;
        @SerializedName("look_id")
        @Expose
        private Object lookId;
        @SerializedName("marketplace_id")
        @Expose
        private Object marketplaceId;
        @SerializedName("publish_date")
        @Expose
        private String publishDate;
        @SerializedName("reviews_total")
        @Expose
        private Integer reviewsTotal;
        @SerializedName("reviews_percent")
        @Expose
        private Integer reviewsPercent;
        @SerializedName("options")
        @Expose
        private List<Object> options = null;
        @SerializedName("variants")
        @Expose
        private List<Object> variants = null;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        @SerializedName("likes")
        @Expose
        private List<Like> likes = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAccountId() {
            return accountId;
        }

        public void setAccountId(Integer accountId) {
            this.accountId = accountId;
        }

        public Integer getCreatorUserId() {
            return creatorUserId;
        }

        public void setCreatorUserId(Integer creatorUserId) {
            this.creatorUserId = creatorUserId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public Object getSourceType() {
            return sourceType;
        }

        public void setSourceType(Object sourceType) {
            this.sourceType = sourceType;
        }

        public Object getSourceId() {
            return sourceId;
        }

        public void setSourceId(Object sourceId) {
            this.sourceId = sourceId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public Integer getCost() {
            return cost;
        }

        public void setCost(Integer cost) {
            this.cost = cost;
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

        public String getMsrp() {
            return msrp;
        }

        public void setMsrp(String msrp) {
            this.msrp = msrp;
        }

        public String getMpn() {
            return mpn;
        }

        public void setMpn(String mpn) {
            this.mpn = mpn;
        }

        public Object getUm() {
            return um;
        }

        public void setUm(Object um) {
            this.um = um;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getWeightUnit() {
            return weightUnit;
        }

        public void setWeightUnit(String weightUnit) {
            this.weightUnit = weightUnit;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Integer getColor() {
            return color;
        }

        public void setColor(Integer color) {
            this.color = color;
        }

        public Integer getBrand() {
            return brand;
        }

        public void setBrand(Integer brand) {
            this.brand = brand;
        }

        public Object getBarcode() {
            return barcode;
        }

        public void setBarcode(Object barcode) {
            this.barcode = barcode;
        }

        public String getLengthUnit() {
            return lengthUnit;
        }

        public void setLengthUnit(String lengthUnit) {
            this.lengthUnit = lengthUnit;
        }

        public String getHeightUnit() {
            return heightUnit;
        }

        public void setHeightUnit(String heightUnit) {
            this.heightUnit = heightUnit;
        }

        public String getWidthUnit() {
            return widthUnit;
        }

        public void setWidthUnit(String widthUnit) {
            this.widthUnit = widthUnit;
        }

        public List<Object> getPackageType() {
            return packageType;
        }

        public void setPackageType(List<Object> packageType) {
            this.packageType = packageType;
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

        public Integer getLastSku() {
            return lastSku;
        }

        public void setLastSku(Integer lastSku) {
            this.lastSku = lastSku;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUpc() {
            return upc;
        }

        public void setUpc(String upc) {
            this.upc = upc;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public Integer getActive() {
            return active;
        }

        public void setActive(Integer active) {
            this.active = active;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getSetId() {
            return setId;
        }

        public void setSetId(Object setId) {
            this.setId = setId;
        }

        public Object getLookId() {
            return lookId;
        }

        public void setLookId(Object lookId) {
            this.lookId = lookId;
        }

        public Object getMarketplaceId() {
            return marketplaceId;
        }

        public void setMarketplaceId(Object marketplaceId) {
            this.marketplaceId = marketplaceId;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public Integer getReviewsTotal() {
            return reviewsTotal;
        }

        public void setReviewsTotal(Integer reviewsTotal) {
            this.reviewsTotal = reviewsTotal;
        }

        public Integer getReviewsPercent() {
            return reviewsPercent;
        }

        public void setReviewsPercent(Integer reviewsPercent) {
            this.reviewsPercent = reviewsPercent;
        }

        public List<Object> getOptions() {
            return options;
        }

        public void setOptions(List<Object> options) {
            this.options = options;
        }

        public List<Object> getVariants() {
            return variants;
        }

        public void setVariants(List<Object> variants) {
            this.variants = variants;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

        public List<Like> getLikes() {
            return likes;
        }

        public void setLikes(List<Like> likes) {
            this.likes = likes;
        }

        public class Like {

            @SerializedName("user_id")
            @Expose
            private Integer userId;
            @SerializedName("item_id")
            @Expose
            private Integer itemId;
            @SerializedName("role")
            @Expose
            private Object role;
            @SerializedName("pivot")
            @Expose
            private Pivot pivot;

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public Integer getItemId() {
                return itemId;
            }

            public void setItemId(Integer itemId) {
                this.itemId = itemId;
            }

            public Object getRole() {
                return role;
            }

            public void setRole(Object role) {
                this.role = role;
            }

            public Pivot getPivot() {
                return pivot;
            }

            public void setPivot(Pivot pivot) {
                this.pivot = pivot;
            }

            public class Pivot {

                @SerializedName("item_id")
                @Expose
                private Integer itemId;
                @SerializedName("user_id")
                @Expose
                private Integer userId;
                @SerializedName("created_at")
                @Expose
                private String createdAt;
                @SerializedName("updated_at")
                @Expose
                private String updatedAt;

                public Integer getItemId() {
                    return itemId;
                }

                public void setItemId(Integer itemId) {
                    this.itemId = itemId;
                }

                public Integer getUserId() {
                    return userId;
                }

                public void setUserId(Integer userId) {
                    this.userId = userId;
                }

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public String getUpdatedAt() {
                    return updatedAt;
                }

                public void setUpdatedAt(String updatedAt) {
                    this.updatedAt = updatedAt;
                }

            }
        }

    }
}