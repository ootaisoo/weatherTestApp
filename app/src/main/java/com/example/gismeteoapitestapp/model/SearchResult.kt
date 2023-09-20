package com.example.gismeteoapitestapp.model

import com.google.gson.annotations.SerializedName

data class SearchResult(

    @SerializedName("meta"     ) var meta     : Meta?     = Meta(),
    @SerializedName("response" ) var response : Response? = Response()

)

data class Meta (

    @SerializedName("message" ) var message : String? = null,
    @SerializedName("code"    ) var code    : String? = null

)


data class Response (

    @SerializedName("items" ) var items : ArrayList<Items> = arrayListOf(),
    @SerializedName("total" ) var total : Int?             = null

)

data class Items (

    @SerializedName("district"    ) var district    : District? = District(),
    @SerializedName("id"          ) var id          : Int?      = null,
    @SerializedName("subDistrict" ) var subDistrict : String?   = null,
    @SerializedName("url"         ) var url         : String?   = null,
    @SerializedName("nameP"       ) var nameP       : String?   = null,
    @SerializedName("name"        ) var name        : String?   = null,
    @SerializedName("rate"        ) var rate        : Int?      = null,
    @SerializedName("weight"      ) var weight      : Int?      = null,
    @SerializedName("kind"        ) var kind        : String?   = null,
    @SerializedName("country"     ) var country     : Country?  = Country()

)

data class Country (

    @SerializedName("name"  ) var name  : String? = null,
    @SerializedName("code"  ) var code  : String? = null,
    @SerializedName("nameP" ) var nameP : String? = null

)

data class District (

    @SerializedName("name"  ) var name  : String? = null,
    @SerializedName("nameP" ) var nameP : String? = null

)