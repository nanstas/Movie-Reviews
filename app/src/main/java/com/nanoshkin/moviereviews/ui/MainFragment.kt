package com.nanoshkin.moviereviews.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nanoshkin.moviereviews.R
import com.nanoshkin.moviereviews.databinding.FragmentMainBinding
import com.nanoshkin.moviereviews.databinding.FragmentWebBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModels<MainViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)

    private val moviesAdapter = MoviesAdapter(object : OnItemClickListener {
        override fun clickOnCard(url: String) {
            val action = MainFragmentDirections.actionMainFragmentToWebFragment(url)
            findNavController().navigate(action)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.data.collectLatest { moviesAdapter.submitData(it) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.adapterRecyclerView.adapter = moviesAdapter
    }
}