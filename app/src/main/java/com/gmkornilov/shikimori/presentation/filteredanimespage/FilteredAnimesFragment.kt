package com.gmkornilov.shikimori.presentation.filteredanimespage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.FragmentFilteredAnimesBinding
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.extensions.mapVisibility
import com.gmkornilov.shikimori.presentation.items.animepreview.AnimePreviewAdapter
import com.gmkornilov.shikimori.presentation.navigation.arguments.AnimeFilter
import javax.inject.Inject

class FilteredAnimesFragment : Fragment(R.layout.fragment_filtered_animes) {
    @Inject
    lateinit var viewModelFactory: FilteredAnimesViewModelAssistedFactory

    private val binding: FragmentFilteredAnimesBinding by viewBinding(FragmentFilteredAnimesBinding::bind)

    private val viewModel: FilteredAnimesViewModel by viewModels {
        val animeFilter = requireArguments().getParcelable<AnimeFilter>(filterKey)!!
        FilteredAnimesViewModelFactory(animeFilter, viewModelFactory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ShikimoriApplication.instance.plusFilteredAnimesPageComponent().inject(this)
        bindList()
        bindLoading()
        bindError()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isRemoving) {
            ShikimoriApplication.instance.clearFilteredAnimesPageComponent()
        }
    }

    private fun bindList() {
        val adapter = AnimePreviewAdapter(viewModel)

        binding.previewList.adapter = adapter

        viewModel.previews.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private fun bindLoading() {
        viewModel.loading.observe(viewLifecycleOwner, {
            binding.loadingProgress.visibility = mapVisibility(it)
        })

        viewModel.loadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.previewList.visibility = mapVisibility(it)
        })
    }

    private fun bindError() {
        binding.errorLayout.reloadButton.setOnClickListener {
            viewModel.loadPreviews()
        }

        viewModel.exception.observe(viewLifecycleOwner, {
            binding.errorLayout.root.visibility = mapVisibility(it)
        })
    }

    companion object {
        const val filterKey = "FILTER"

        fun newInstance(filter: AnimeFilter): Fragment {
            val bundle = Bundle().apply {
                putParcelable(filterKey, filter)
            }
            return FilteredAnimesFragment().apply {
                arguments = bundle
            }
        }
    }
}