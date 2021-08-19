package com.gmkornilov.shikimori.presentation.filteredanimes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gmkornilov.shikimori.databinding.FragmentFilteredAnimesBinding
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import com.gmkornilov.shikimori.presentation.filteredanimes.adapter.AnimePreviewClicked
import com.gmkornilov.shikimori.presentation.filteredanimes.adapter.AnimeRowAdapter

class FilteredAnimesFragment : Fragment() {

    private lateinit var binding: FragmentFilteredAnimesBinding

    private lateinit var viewModel: FilteredAnimesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilteredAnimesBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(FilteredAnimesViewModel::class.java)

        bindList()

        return binding.root
    }

    private fun bindList() {
        val adapter = AnimeRowAdapter(viewModel)

        binding.previewList.adapter = adapter

        viewModel.previews.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
    }

    companion object {
        const val filterKey = "FILTER"

        fun newInstance(filter: AnimeFilter): Fragment {
            val bundle = Bundle().apply {
                putSerializable(filterKey, filter)
            }
            return FilteredAnimesFragment().apply {
                arguments = bundle
            }
        }
    }
}