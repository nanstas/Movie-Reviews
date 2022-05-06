package com.nanoshkin.moviereviews.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nanoshkin.moviereviews.data.model.Movie
import com.nanoshkin.moviereviews.databinding.ItemMovieBinding

interface OnItemClickListener {
    fun clickOnCard(url: String)
}

class MoviesAdapter(private val onItemClickListener: OnItemClickListener) :
    PagingDataAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MovieDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class MoviesViewHolder(
        private val binding: ItemMovieBinding,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                moviesScreenImageView.load(movie.image) {
                    placeholder(ColorDrawable(Color.TRANSPARENT))
                }
                titleTextView.text = movie.title
                summeryShortTextView.text = movie.summeryShort

                if (movie.criticsPick) {
                    icCriticsPickImageView.visibility = View.VISIBLE
                } else {
                    icCriticsPickImageView.visibility = View.GONE
                }

                itemMovieCardView.setOnClickListener {
                    onItemClickListener.clickOnCard(movie.webPageUrl)
                }
            }
        }
    }
}

private object MovieDiffItemCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title && oldItem.image == newItem.image && oldItem.image == newItem.summeryShort
    }
}