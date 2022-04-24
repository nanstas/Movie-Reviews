package com.nanoshkin.moviereviews.data.network

import com.nanoshkin.moviereviews.BuildConfig
import com.nanoshkin.moviereviews.data.network.dto.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieReviewsApi {
    @GET("reviews/all.json")
    suspend fun getMovies(
        @Query("offset") page: Int,
        @Query("api-key") key: String = BuildConfig.MOVIE_REVIEWS_API_KEY,
    ): Response<MoviesResponse>
}