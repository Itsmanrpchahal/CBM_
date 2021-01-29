package com.casebeaumonde.activities.ClosetItem.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FilterResponse {

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("parent_id")
    @Expose
    private var parentId: Int? = null

    @SerializedName("creator_id")
    @Expose
    private var creatorId: Int? = null

    @SerializedName("fashionable_type")
    @Expose
    private var fashionableType: String? = null

    @SerializedName("fashionable_id")
    @Expose
    private var fashionableId: Int? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("category_id")
    @Expose
    private var categoryId: Int? = null

    @SerializedName("size")
    @Expose
    private var size: Size? = null

    @SerializedName("color")
    @Expose
    private var color: Color? = null

    @SerializedName("brand")
    @Expose
    private var brand: Brand? = null

    @SerializedName("price")
    @Expose
    private var price: Int? = null

    @SerializedName("picture")
    @Expose
    private var picture: String? = null

    @SerializedName("external_url")
    @Expose
    private var externalUrl: Any? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    private var updatedAt: String? = null

    @SerializedName("deleted_at")
    @Expose
    private var deletedAt: Any? = null

    @SerializedName("creator")
    @Expose
    private var creator: Creator? = null

    @SerializedName("category")
    @Expose
    private var category: Category? = null

    @SerializedName("hearts")
    @Expose
    private var hearts: List<Heart?>? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getParentId(): Int? {
        return parentId
    }

    fun setParentId(parentId: Int?) {
        this.parentId = parentId
    }

    fun getCreatorId(): Int? {
        return creatorId
    }

    fun setCreatorId(creatorId: Int?) {
        this.creatorId = creatorId
    }

    fun getFashionableType(): String? {
        return fashionableType
    }

    fun setFashionableType(fashionableType: String?) {
        this.fashionableType = fashionableType
    }

    fun getFashionableId(): Int? {
        return fashionableId
    }

    fun setFashionableId(fashionableId: Int?) {
        this.fashionableId = fashionableId
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getCategoryId(): Int? {
        return categoryId
    }

    fun setCategoryId(categoryId: Int?) {
        this.categoryId = categoryId
    }

    fun getSize(): Size? {
        return size
    }

    fun setSize(size: Size?) {
        this.size = size
    }

    fun getColor(): Color? {
        return color
    }

    fun setColor(color: Color?) {
        this.color = color
    }

    fun getBrand(): Brand? {
        return brand
    }

    fun setBrand(brand: Brand?) {
        this.brand = brand
    }

    fun getPrice(): Int? {
        return price
    }

    fun setPrice(price: Int?) {
        this.price = price
    }

    fun getPicture(): String? {
        return picture
    }

    fun setPicture(picture: String?) {
        this.picture = picture
    }

    fun getExternalUrl(): Any? {
        return externalUrl
    }

    fun setExternalUrl(externalUrl: Any?) {
        this.externalUrl = externalUrl
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    fun getDeletedAt(): Any? {
        return deletedAt
    }

    fun setDeletedAt(deletedAt: Any?) {
        this.deletedAt = deletedAt
    }

    fun getCreator(): Creator? {
        return creator
    }

    fun setCreator(creator: Creator?) {
        this.creator = creator
    }

    fun getCategory(): Category? {
        return category
    }

    fun setCategory(category: Category?) {
        this.category = category
    }

    fun getHearts(): List<Heart?>? {
        return hearts
    }

    fun setHearts(hearts: List<Heart?>?) {
        this.hearts = hearts
    }

    class Size {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null
    }

    class Color {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("user_id")
        @Expose
        var userId: Any? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null
    }

    class Brand {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("user_id")
        @Expose
        var userId: Any? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("description")
        @Expose
        var description: Any? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("logo")
        @Expose
        var logo: Any? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null
    }

    class Creator {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("firstname")
        @Expose
        var firstname: String? = null

        @SerializedName("lastname")
        @Expose
        var lastname: String? = null

        @SerializedName("fullname")
        @Expose
        var fullname: String? = null

        @SerializedName("phone")
        @Expose
        var phone: String? = null

        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("company")
        @Expose
        var company: Any? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("affiliate_id")
        @Expose
        var affiliateId: Int? = null

        @SerializedName("percent")
        @Expose
        var percent: String? = null

        @SerializedName("pending_balance")
        @Expose
        var pendingBalance: String? = null

        @SerializedName("available_balance")
        @Expose
        var availableBalance: String? = null

        @SerializedName("allow_hire")
        @Expose
        var allowHire: Int? = null

        @SerializedName("avatar")
        @Expose
        var avatar: String? = null

        @SerializedName("email_verified_at")
        @Expose
        var emailVerifiedAt: String? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null

        @SerializedName("deleted_at")
        @Expose
        var deletedAt: Any? = null

        @SerializedName("role")
        @Expose
        var role: String? = null
    }

    class Category {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("parent_id")
        @Expose
        var parentId: Int? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("active")
        @Expose
        var active: Int? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null

        @SerializedName("deleted_at")
        @Expose
        var deletedAt: Any? = null
    }

    class Heart {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("user_id")
        @Expose
        var userId: Int? = null

        @SerializedName("heartable_type")
        @Expose
        var heartableType: String? = null

        @SerializedName("heartable_id")
        @Expose
        var heartableId: Int? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null
    }
}