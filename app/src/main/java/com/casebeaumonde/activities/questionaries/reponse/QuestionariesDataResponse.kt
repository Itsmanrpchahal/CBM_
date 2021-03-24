package com.casebeaumonde.activities.questionaries.reponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuestionariesDataResponse {
    @SerializedName("code")
    @Expose
    var code: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    inner class Data {
        @SerializedName("customer")
        @Expose
        var customer: Customer? = null

        @SerializedName("business")
        @Expose
        var business: Business? = null

        @SerializedName("influencer")
        @Expose
        var influencer: Influencer? = null

        inner class Customer {
            @SerializedName("astrological_sign")
            @Expose
            var astrologicalSign: List<String>? = null

            @SerializedName("body_type")
            @Expose
            var bodyType: List<String>? = null

            @SerializedName("eye_color")
            @Expose
            var eyeColor: List<String>? = null

            @SerializedName("hair_color")
            @Expose
            var hairColor: List<String>? = null

            @SerializedName("characterstic")
            @Expose
            var characterstic: List<String>? = null

            @SerializedName("stores")
            @Expose
            var stores: List<String>? = null

            @SerializedName("drives_to_shop")
            @Expose
            var drivesToShop: List<String>? = null

            @SerializedName("fashion_events")
            @Expose
            var fashionEvents: List<String>? = null

            @SerializedName("like_cbm")
            @Expose
            var likeCbm: List<String>? = null

            @SerializedName("brand")
            @Expose
            var brand: List<Brand>? = null

            @SerializedName("country")
            @Expose
            var country: List<Country>? = null

            inner class Brand {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("name")
                @Expose
                var name: String? = null
            }

            inner class Country {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("code")
                @Expose
                var code: String? = null

                @SerializedName("name")
                @Expose
                var name: String? = null

                @SerializedName("state")
                @Expose
                var state: List<State>? = null

                inner class State {
                    @SerializedName("id")
                    @Expose
                    var id: Int? = null

                    @SerializedName("country_id")
                    @Expose
                    var countryId: Int? = null

                    @SerializedName("code")
                    @Expose
                    var code: String? = null

                    @SerializedName("name")
                    @Expose
                    var name: String? = null
                }
            }
        }

        inner class Business {
            @SerializedName("struggle_with")
            @Expose
            var struggleWith: List<String>? = null

            @SerializedName("partnered_with")
            @Expose
            var partneredWith: List<String>? = null
        }

        inner class Influencer {
            @SerializedName("time_spend")
            @Expose
            var timeSpend: List<String>? = null

            @SerializedName("help_users")
            @Expose
            var helpUsers: List<String>? = null
        }
    }
}
