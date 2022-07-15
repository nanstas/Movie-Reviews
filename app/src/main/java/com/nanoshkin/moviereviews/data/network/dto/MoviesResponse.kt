package com.nanoshkin.moviereviews.data.network.dto

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val copyright: String,
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("num_results")
    val numResults: Int,
    val results: List<Result>,
    val status: String
)