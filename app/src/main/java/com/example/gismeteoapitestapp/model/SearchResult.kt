package com.example.gismeteoapitestapp.model

import com.google.gson.annotations.SerializedName

data class SearchResult(

    @SerializedName("meta"     ) val meta     : Meta,
    @SerializedName("response" ) val response : Response

)

data class Meta (

    @SerializedName("message" ) val message : String? = null,
    @SerializedName("code"    ) val code    : String? = null

)


data class Response (

    @SerializedName("items" ) val items : List<Items> = arrayListOf(),
    @SerializedName("total" ) val total : Int

)

data class Items (

    @SerializedName("district"    ) val district    : District?,
    @SerializedName("id"          ) val id          : Int,
    @SerializedName("subDistrict" ) val subDistrict : SubDistrict?,
    @SerializedName("url"         ) val url         : String?   = null,
    @SerializedName("nameP"       ) val nameP       : String?   = null,
    @SerializedName("name"        ) val name        : String?   = null,
    @SerializedName("rate"        ) val rate        : Int?      = null,
    @SerializedName("weight"      ) val weight      : Long?      = null,
    @SerializedName("kind"        ) val kind        : String?   = null,
    @SerializedName("country"     ) val country     : Country?  = Country()

)

data class Country (

    @SerializedName("name"  ) val name  : String? = null,
    @SerializedName("code"  ) val code  : String? = null,
    @SerializedName("nameP" ) val nameP : String? = null

)

data class District (

    @SerializedName("name"  ) val name  : String? = null,
    @SerializedName("nameP" ) val nameP : String? = null

)

data class SubDistrict (

    @SerializedName("name"  ) val name  : String? = null,
    @SerializedName("nameP" ) val nameP : String? = null

)