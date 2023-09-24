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
    @SerializedName("id"          ) val id          : Int
)