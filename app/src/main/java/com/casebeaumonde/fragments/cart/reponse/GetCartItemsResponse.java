package com.casebeaumonde.fragments.cart.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCartItemsResponse {

@SerializedName("code")
@Expose
private String code;
@SerializedName("cartItems")
@Expose
private CartItems cartItems;

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public CartItems getCartItems() {
return cartItems;
}

public void setCartItems(CartItems cartItems) {
this.cartItems = cartItems;
}

    public class CartItems {

        @SerializedName("Maggie Pi")
        @Expose
        private List<MaggiePus> maggiePi = null;

        public List<MaggiePus> getMaggiePi() {
            return maggiePi;
        }

        public void setMaggiePi(List<MaggiePus> maggiePi) {
            this.maggiePi = maggiePi;
        }

        public class MaggiePus {

            @SerializedName("sub_total")
            @Expose
            private Integer subTotal;
            @SerializedName("total")
            @Expose
            private Integer total;
            @SerializedName("shipping_method")
            @Expose
            private Object shippingMethod;
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("user_id")
            @Expose
            private Integer userId;
            @SerializedName("product_id")
            @Expose
            private Integer productId;
            @SerializedName("retailer_id")
            @Expose
            private Integer retailerId;
            @SerializedName("variant_id")
            @Expose
            private Object variantId;
            @SerializedName("qty")
            @Expose
            private Integer qty;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("product")
            @Expose
            private Product product;

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
                @SerializedName("sell_price")
                @Expose
                private Integer sellPrice;
                @SerializedName("regular_price")
                @Expose
                private Integer regularPrice;
                @SerializedName("product_images")
                @Expose
                private List<ProductImage> productImages = null;
                @SerializedName("category")
                @Expose
                private Object category;
                @SerializedName("brand")
                @Expose
                private Object brand;
                @SerializedName("user")
                @Expose
                private User user;

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

                public Integer getSellPrice() {
                    return sellPrice;
                }

                public void setSellPrice(Integer sellPrice) {
                    this.sellPrice = sellPrice;
                }

                public Integer getRegularPrice() {
                    return regularPrice;
                }

                public void setRegularPrice(Integer regularPrice) {
                    this.regularPrice = regularPrice;
                }

                public List<ProductImage> getProductImages() {
                    return productImages;
                }

                public void setProductImages(List<ProductImage> productImages) {
                    this.productImages = productImages;
                }

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

                public Object getCategory() {
                    return category;
                }

                public void setCategory(Object category) {
                    this.category = category;
                }

                public Object getBrand() {
                    return brand;
                }

                public void setBrand(Object brand) {
                    this.brand = brand;
                }

                public User getUser() {
                    return user;
                }

                public void setUser(User user) {
                    this.user = user;
                }

                public class User {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("firstname")
                    @Expose
                    private String firstname;
                    @SerializedName("lastname")
                    @Expose
                    private String lastname;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public String getFirstname() {
                        return firstname;
                    }

                    public void setFirstname(String firstname) {
                        this.firstname = firstname;
                    }

                    public String getLastname() {
                        return lastname;
                    }

                    public void setLastname(String lastname) {
                        this.lastname = lastname;
                    }

                }

            }

            public Integer getSubTotal() {
                return subTotal;
            }

            public void setSubTotal(Integer subTotal) {
                this.subTotal = subTotal;
            }

            public Integer getTotal() {
                return total;
            }

            public void setTotal(Integer total) {
                this.total = total;
            }

            public Object getShippingMethod() {
                return shippingMethod;
            }

            public void setShippingMethod(Object shippingMethod) {
                this.shippingMethod = shippingMethod;
            }

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

            public Integer getProductId() {
                return productId;
            }

            public void setProductId(Integer productId) {
                this.productId = productId;
            }

            public Integer getRetailerId() {
                return retailerId;
            }

            public void setRetailerId(Integer retailerId) {
                this.retailerId = retailerId;
            }

            public Object getVariantId() {
                return variantId;
            }

            public void setVariantId(Object variantId) {
                this.variantId = variantId;
            }

            public Integer getQty() {
                return qty;
            }

            public void setQty(Integer qty) {
                this.qty = qty;
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

            public Product getProduct() {
                return product;
            }

            public void setProduct(Product product) {
                this.product = product;
            }

        }
    }
}