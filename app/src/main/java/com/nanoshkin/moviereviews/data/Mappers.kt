package com.nanoshkin.moviereviews.data

import com.nanoshkin.moviereviews.data.model.Movie
import com.nanoshkin.moviereviews.data.network.dto.Result

fun Result.toMovie(): Movie {
    return Movie(
        title = display_title,
        image = multimedia.src,
        summeryShort = summary_short,
        webPageUrl = link.url
    )
}