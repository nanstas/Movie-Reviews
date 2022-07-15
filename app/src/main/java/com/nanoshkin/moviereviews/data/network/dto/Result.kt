package com.nanoshkin.moviereviews.data.network.dto

import com.google.gson.annotations.SerializedName

data class Result(
    val byline: String,
    @SerializedName("critics_pick")
    val criticsPick: Int,
    @SerializedName("date_updated")
    val dateUpdated: String,
    @SerializedName("display_title")
    val displayTitle: String,
    val headline: String,
    val link: Link,
    @SerializedName("mpaa_rating")
    val mpaaRating: String,
    val multimedia: Multimedia,
    @SerializedName("opening_date")
    val openingDate: String,
    @SerializedName("publication_date")
    val publicationDate: String,
    @SerializedName("summary_short")
    val summaryShort: String
)