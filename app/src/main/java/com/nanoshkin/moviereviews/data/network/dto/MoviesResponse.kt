package com.nanoshkin.moviereviews.data.network.dto

data class MoviesResponse(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)