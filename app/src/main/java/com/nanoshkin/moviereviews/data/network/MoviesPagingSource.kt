package com.nanoshkin.moviereviews.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.nanoshkin.moviereviews.data.model.Movie
import com.nanoshkin.moviereviews.data.toMovie
import retrofit2.HttpException

class MoviesPagingSource(
    private val movieReviewsApi: MovieReviewsApi,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val response = movieReviewsApi.getMovies(pageNumber)

            return if (response.isSuccessful) {
                val movies = response.body()!!.results.map { it.toMovie() }
                val nextPageNumber = if (movies.isEmpty()) null else pageNumber + 21
                val prevPageNumber = if (pageNumber > 0) pageNumber - 21 else null
                Page(data = movies, prevKey = prevPageNumber, nextKey = nextPageNumber)
            } else {
                Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return Error(e)
        } catch (e: Exception) {
            return Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(21) ?: anchorPage.nextKey?.minus(21)
    }

    companion object {
        private const val INITIAL_PAGE_NUMBER = 0
    }
}