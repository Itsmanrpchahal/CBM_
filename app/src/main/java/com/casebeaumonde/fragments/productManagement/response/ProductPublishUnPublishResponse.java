package com.casebeaumonde.fragments.productManagement.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductPublishUnPublishResponse {

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

        @SerializedName("product")
        @Expose
        private Product product;
        @SerializedName("first_page_url")
        @Expose
        private String firstPageUrl;
        @SerializedName("from")
        @Expose
        private Integer from;
        @SerializedName("last_page")
        @Expose
        private Integer lastPage;
        @SerializedName("last_page_url")
        @Expose
        private String lastPageUrl;
        @SerializedName("next_page_url")
        @Expose
        private Object nextPageUrl;
        @SerializedName("path")
        @Expose
        private String path;
        @SerializedName("per_page")
        @Expose
        private Integer perPage;
        @SerializedName("prev_page_url")
        @Expose
        private Object prevPageUrl;
        @SerializedName("to")
        @Expose
        private Integer to;
        @SerializedName("total")
        @Expose
        private Integer total;

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public String getFirstPageUrl() {
            return firstPageUrl;
        }

        public void setFirstPageUrl(String firstPageUrl) {
            this.firstPageUrl = firstPageUrl;
        }

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getLastPage() {
            return lastPage;
        }

        public void setLastPage(Integer lastPage) {
            this.lastPage = lastPage;
        }

        public String getLastPageUrl() {
            return lastPageUrl;
        }

        public void setLastPageUrl(String lastPageUrl) {
            this.lastPageUrl = lastPageUrl;
        }

        public Object getNextPageUrl() {
            return nextPageUrl;
        }

        public void setNextPageUrl(Object nextPageUrl) {
            this.nextPageUrl = nextPageUrl;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Object getPrevPageUrl() {
            return prevPageUrl;
        }

        public void setPrevPageUrl(Object prevPageUrl) {
            this.prevPageUrl = prevPageUrl;
        }

        public Integer getTo() {
            return to;
        }

        public void setTo(Integer to) {
            this.to = to;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public class Product {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("user_id")
            @Expose
            private Integer userId;
            @SerializedName("product_name")
            @Expose
            private String productName;
            @SerializedName("short_description")
            @Expose
            private String shortDescription;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("product_type")
            @Expose
            private Integer productType;
            @SerializedName("regular_price")
            @Expose
            private Integer regularPrice;
            @SerializedName("sell_price")
            @Expose
            private Integer sellPrice;
            @SerializedName("stock_quantity")
            @Expose
            private Integer stockQuantity;
            @SerializedName("shipping_method")
            @Expose
            private Object shippingMethod;
            @SerializedName("category_id")
            @Expose
            private Integer categoryId;
            @SerializedName("brand_id")
            @Expose
            private Object brandId;
            @SerializedName("status")
            @Expose
            private Boolean status;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("product_images")
            @Expose
            private List<Object> productImages = null;
            @SerializedName("category")
            @Expose
            private Category category;
            @SerializedName("brand")
            @Expose
            private Object brand;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getShortDescription() {
                return shortDescription;
            }

            public void setShortDescription(String shortDescription) {
                this.shortDescription = shortDescription;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Integer getProductType() {
                return productType;
            }

            public void setProductType(Integer productType) {
                this.productType = productType;
            }

            public Integer getRegularPrice() {
                return regularPrice;
            }

            public void setRegularPrice(Integer regularPrice) {
                this.regularPrice = regularPrice;
            }

            public Integer getSellPrice() {
                return sellPrice;
            }

            public void setSellPrice(Integer sellPrice) {
                this.sellPrice = sellPrice;
            }

            public Integer getStockQuantity() {
                return stockQuantity;
            }

            public void setStockQuantity(Integer stockQuantity) {
                this.stockQuantity = stockQuantity;
            }

            public Object getShippingMethod() {
                return shippingMethod;
            }

            public void setShippingMethod(Object shippingMethod) {
                this.shippingMethod = shippingMethod;
            }

            public Integer getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Integer categoryId) {
                this.categoryId = categoryId;
            }

            public Object getBrandId() {
                return brandId;
            }

            public void setBrandId(Object brandId) {
                this.brandId = brandId;
            }

            public Boolean getStatus() {
                return status;
            }

            public void setStatus(Boolean status) {
                this.status = status;
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

            public List<Object> getProductImages() {
                return productImages;
            }

            public void setProductImages(List<Object> productImages) {
                this.productImages = productImages;
            }

            public Category getCategory() {
                return category;
            }

            public void setCategory(Category category) {
                this.category = category;
            }

            public Object getBrand() {
                return brand;
            }

            public void setBrand(Object brand) {
                this.brand = brand;
            }

            public class Category {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("parent_id")
                @Expose
                private Integer parentId;
                @SerializedName("name")
                @Expose
                private String name;
                @SerializedName("description")
                @Expose
                private String description;
                @SerializedName("active")
                @Expose
                private Integer active;
                @SerializedName("created_at")
                @Expose
                private String createdAt;
                @SerializedName("updated_at")
                @Expose
                private String updatedAt;
                @SerializedName("deleted_at")
                @Expose
                private Object deletedAt;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public Integer getParentId() {
                    return parentId;
                }

                public void setParentId(Integer parentId) {
                    this.parentId = parentId;
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

                public Integer getActive() {
                    return active;
                }

                public void setActive(Integer active) {
                    this.active = active;
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

            }
        }
    }
}