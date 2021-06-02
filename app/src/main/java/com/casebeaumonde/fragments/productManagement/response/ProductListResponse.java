package com.casebeaumonde.fragments.productManagement.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListResponse {

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

        @SerializedName("products")
        @Expose
        private Products products;

        public Products getProducts() {
            return products;
        }

        public void setProducts(Products products) {
            this.products = products;
        }

        public class Products {

            @SerializedName("current_page")
            @Expose
            private Integer currentPage;
            @SerializedName("data")
            @Expose
            private List<Datum> data = null;
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

            public Integer getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(Integer currentPage) {
                this.currentPage = currentPage;
            }

            public List<Datum> getData() {
                return data;
            }

            public void setData(List<Datum> data) {
                this.data = data;
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


            public class Datum {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("product_name")
                @Expose
                private String productName;
                @SerializedName("short_description")
                @Expose
                private String shortDescription;
                @SerializedName("description")
                @Expose
                private String description;

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                @SerializedName("regular_price")
                @Expose
                private Integer regularPrice;
                @SerializedName("sell_price")
                @Expose
                private Integer sellPrice;
                @SerializedName("stock_quantity")
                @Expose
                private String stock_quantity;

                public String getStock_quantity() {
                    return stock_quantity;
                }

                public void setStock_quantity(String stock_quantity) {
                    this.stock_quantity = stock_quantity;
                }

                @SerializedName("category_id")
                @Expose
                private Integer categoryId;
                @SerializedName("brand_id")
                @Expose
                private Integer brandId;
                @SerializedName("status")
                @Expose
                private Integer status;
                @SerializedName("product_images")
                @Expose
                private List<ProductImage> productImages = null;

                public class ProductImage {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("product_id")
                    @Expose
                    private Integer productId;
                    @SerializedName("image")
                    @Expose
                    private String image;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public Integer getProductId() {
                        return productId;
                    }

                    public void setProductId(Integer productId) {
                        this.productId = productId;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                }

                @SerializedName("category")
                @Expose
                private Category category;

                public class Category {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("name")
                    @Expose
                    private String name;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                }


                @SerializedName("brand")
                @Expose
                private Brand brand;

                public class Brand {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("name")
                    @Expose
                    private String name;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                }

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
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

                public Integer getCategoryId() {
                    return categoryId;
                }

                public void setCategoryId(Integer categoryId) {
                    this.categoryId = categoryId;
                }

                public Integer getBrandId() {
                    return brandId;
                }

                public void setBrandId(Integer brandId) {
                    this.brandId = brandId;
                }

                public Integer getStatus() {
                    return status;
                }

                public void setStatus(Integer status) {
                    this.status = status;
                }

                public List<ProductImage> getProductImages() {
                    return productImages;
                }

                public void setProductImages(List<ProductImage> productImages) {
                    this.productImages = productImages;
                }

                public Category getCategory() {
                    return category;
                }

                public void setCategory(Category category) {
                    this.category = category;
                }

                public Brand getBrand() {
                    return brand;
                }

                public void setBrand(Brand brand) {
                    this.brand = brand;
                }

            }

        }
    }
}