package com.mey.getnews.data.model

import com.google.gson.annotations.SerializedName

data class Base(
    @SerializedName("status") var status: String? = null,
    @SerializedName("totalResults") var totalResults: Int? = null,
    @SerializedName("articles") var articles: ArrayList<Articles> = arrayListOf(),
    @SerializedName("code"    ) var code    : String? = null,
    @SerializedName("message" ) var message : String? = null
)
