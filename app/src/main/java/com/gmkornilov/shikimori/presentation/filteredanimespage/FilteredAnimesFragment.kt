package com.gmkornilov.shikimori.presentation.filteredanimespage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.FragmentFilteredAnimesBinding
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.gmkornilov.shikimori.presentation.extensions.mapVisibility
import com.gmkornilov.shikimori.presentation.components.animepreview.animePreviewAdapterDelegate
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
        val animeFilter = requireArguments().getParcelable<AnimeFilter>(filterKey)!!
        FilteredAnimesViewModelFactory(animeFilter, router, viewModelFactory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ShikimoriApplication.INSTANCE.plusFilteredAnimesPageComponent().inject(this)
        bindList()
        bindLoading()
        bindError()
    }

    override fun onStart() {
        super.onStart()

        with(binding) {
            toolbarLayout.toolbar.setNavigationOnClickListener {
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
            animePreviewAdapterDelegate(viewModel),
        )

        binding.previewList.adapter = adapter

        viewModel.loadingData.values.observe(viewLifecycleOwner, {
            adapter.items = it
        })
    }

    private fun bindLoading() {
        viewModel.loadingData.loading.observe(viewLifecycleOwner, {
            binding.loadingProgress.visibility = mapVisibility(it)
        })

        viewModel.loadingData.loadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.previewList.visibility = mapVisibility(it)
        })
    }

    private fun bindError() {
        binding.errorLayout.reloadButton.setOnClickListener {
            viewModel.loadingData.load()
        }

        viewModel.loadingData.exception.observe(viewLifecycleOwner, {
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