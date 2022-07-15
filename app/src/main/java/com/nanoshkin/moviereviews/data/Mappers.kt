package com.nanoshkin.moviereviews.data

import com.nanoshkin.moviereviews.data.model.Movie
import com.nanoshkin.moviereviews.data.network.dto.Result

fun Result.toMovie(): Movie {
    return Movie(
        title = displayTitle,
        image = multimedia.src,
        summeryShort = summaryShort,
        webPageUrl = link.url,
        criticsPick = criticsPick == 1
    )
}