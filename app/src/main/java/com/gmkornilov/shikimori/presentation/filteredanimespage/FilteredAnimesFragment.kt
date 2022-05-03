package com.gmkornilov.shikimori.presentation.filteredanimespage

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.FragmentFilteredAnimesBinding
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.gmkornilov.shikimori.presentation.components.animepreview.animeVerticalPreviewAdapterDelegate
import com.gmkornilov.shikimori.presentation.extensions.getAppCompatDrawable
import com.gmkornilov.shikimori.presentation.navigation.arguments.AnimeFilter
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackNavigationManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import javax.inject.Inject

class FilteredAnimesFragment : Fragment(R.layout.fragment_filtered_animes) {
    @Inject
    lateinit var viewModelFactory: FilteredAnimesViewModelAssistedFactory

    @Inject
    lateinit var backstackNavigationManager: BackstackNavigationManager

    private val router: Router by lazy {
        backstackNavigationManager.getCurrentBackstackRouter()
    }

    private val binding: FragmentFilteredAnimesBinding by viewBinding(FragmentFilteredAnimesBinding::bind)

    private val viewModel: FilteredAnimesViewModel by viewModels {
        val animeFilter = requireArguments().getParcelable<AnimeFilter>(filterKey)
        FilteredAnimesViewModelFactory(animeFilter, router, viewModelFactory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ShikimoriApplication.INSTANCE.plusFilteredAnimesPageComponent().inject(this)
        bindList()
        bindLoading()
        bindError()

        with(binding) {
            val titleType = requireArguments().getSerializable(titleType) as FilteredAnimesTitleType
            val title = context?.getString(titleType.titleRes)
            toolbar.title = title

            val backAvailable = requireArguments().getBoolean(backAvailableKey)
            if (backAvailable) {
                toolbar.navigationIcon = context?.getAppCompatDrawable(R.drawable.ic_back_button)
            } else {
                toolbar.navigationIcon = null
            }
        }
    }

    override fun onStart() {
        super.onStart()

        with(binding) {
            toolbar.setNavigationOnClickListener {
                router.exit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isRemoving) {
            ShikimoriApplication.INSTANCE.clearFilteredAnimesPageComponent()
        }
    }

    private fun bindList() {
        val adapter = AsyncListDifferDelegationAdapter(
            BaseComponent,
            animeVerticalPreviewAdapterDelegate(viewModel),
        )

        binding.previewList.adapter = adapter

        viewModel.values.observe(viewLifecycleOwner) {
            adapter.items = it
        }
    }

    private fun bindLoading() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loadingProgress.isVisible = it
        }

        viewModel.loadedWithoutErrors.observe(viewLifecycleOwner) {
            binding.previewList.isVisible = it
        }
    }

    private fun bindError() {
        binding.errorLayout.reloadButton.setOnClickListener {
            viewModel.load()
        }

        viewModel.exception.observe(viewLifecycleOwner) {
            binding.errorLayout.root.isVisible = it
        }
    }

    companion object {
        private const val filterKey = "FILTER"
        private const val titleType = "TITLE_TYPE"
        private const val backAvailableKey = "BACK_AVAILABLE"

        fun newInstance(filter: AnimeFilter?, filteredAnimesTitleType: FilteredAnimesTitleType, backAvailable: Boolean): Fragment {
            val bundle = Bundle().apply {
                putParcelable(filterKey, filter)
                putSerializable(titleType, filteredAnimesTitleType)
                putBoolean(backAvailableKey, backAvailable)
            }
            return FilteredAnimesFragment().apply {
                arguments = bundle
            }
        }
    }
}