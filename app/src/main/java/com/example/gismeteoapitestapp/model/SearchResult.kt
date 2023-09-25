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
    @SerializedName("items" ) val items : List<Items>? = null,
    @SerializedName("total" ) val total : Int? = null,
    @SerializedName("error" ) var error : Error? = null
)

data class Error (

    @SerializedName("message" ) var message : String? = null,
    @SerializedName("code"    ) var code    : Int?    = null

)

data class Items (
    @SerializedName("id"          ) val id          : Int
)