package com.nanoshkin.moviereviews.data.network.dto

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("suggested_link_text")
    val suggestedLinkText: String,
    val type: String,
    val url: String
)