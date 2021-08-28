package com.gmkornilov.shikimori.presentation.filteredanimespage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gmkornilov.shikimori.databinding.FragmentFilteredAnimesBinding
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.animepreview.AnimePreviewAdapter
import com.gmkornilov.shikimori.presentation.navigation.arguments.AnimeFilter
import javax.inject.Inject

class FilteredAnimesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: FilteredAnimesViewModelAssistedFactory

    private lateinit var binding: FragmentFilteredAnimesBinding

    private val viewModel: FilteredAnimesViewModel by viewModels {
        viewModelFactory.create(requireArguments().getParcelable(filterKey)!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilteredAnimesBinding.inflate(inflater, container, false)

        ShikimoriApplication.instance.plusFilteredAnimesPageComponent().inject(this)

        bindList()

        return binding.root
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