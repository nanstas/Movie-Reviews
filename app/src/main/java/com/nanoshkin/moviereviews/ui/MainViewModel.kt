package com.nanoshkin.moviereviews.ui

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nanoshkin.moviereviews.data.model.Movie
import com.nanoshkin.moviereviews.data.network.MovieReviewsApi
import com.nanoshkin.moviereviews.data.network.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieReviewsApi: MovieReviewsApi
) : ViewModel() {
    val data: Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = true),
        pagingSourceFactory = { MoviesPagingSource(movieReviewsApi) }
    )
        .flow
}
