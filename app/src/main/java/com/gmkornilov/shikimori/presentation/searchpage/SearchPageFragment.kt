package com.gmkornilov.shikimori.presentation.searchpage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.FragmentSearchBinding
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.gmkornilov.shikimori.presentation.extensions.mapVisibility
import com.gmkornilov.shikimori.presentation.components.animepreview.animeHorizontalPreviewAdapterDelegate
import com.gmkornilov.shikimori.presentation.components.animepreview.animeVerticalPreviewAdapterDelegate
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackNavigationManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import javax.inject.Inject

class SearchPageFragment : Fragment(R.layout.fragment_search) {
    private val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)

    @Inject
    lateinit var backstackNavigationManager: BackstackNavigationManager

    @Inject
    lateinit var searchPageViewModelAssistedFactory: SearchPageViewModelAssistedFactory

    private val router: Router by lazy {
        backstackNavigationManager.getCurrentBackstackRouter()
    }

    private val viewModel: SearchPageViewModel by viewModels {
        SearchPageViewModelFactory(router, searchPageViewModelAssistedFactory)
    }

    private val animePreviewAdapter: AsyncListDifferDelegationAdapter<BaseComponent> by lazy {
        AsyncListDifferDelegationAdapter(
            BaseComponent,
            animeVerticalPreviewAdapterDelegate(viewModel),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ShikimoriApplication.INSTANCE.plusSearchPageComponent().inject(this)

        bindLoading()
        bindError()
        bindContent()
        bindSearch()
    }

    private fun bindLoading() {
        with(binding) {
            viewModel.searchLoadingData.loading.observe(viewLifecycleOwner, {
                loadingProgress.visibility = mapVisibility(it)
            })
        }
    }

    private fun bindError() {
        with(binding) {
            viewModel.searchLoadingData.exception.observe(viewLifecycleOwner, {
                errorLayout.root.visibility = mapVisibility(it)
            })
            errorLayout.reloadButton.setOnClickListener {

            }
        }
    }

    private fun bindContent() {
        with(binding) {
            searchedPreviewList.adapter = animePreviewAdapter

            viewModel.searchLoadingData.loadedWithoutErrors.observe(viewLifecycleOwner, {
                searchedPreviewList.visibility = mapVisibility(it)
            })

            viewModel.searchLoadingData.values.observe(viewLifecycleOwner, {
                animePreviewAdapter.items = it
            })
        }
    }

    private fun bindSearch() {
        with(binding) {
            searchView.isSubmitButtonEnabled = true

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query ?: return false
                    viewModel.loadPreviews(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText ?: return false
                    Log.d("SEARCH", newText)
                    // TODO: load some previews from db
                    return false
                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isRemoving) {
            ShikimoriApplication.INSTANCE.clearSearchPageComponent()
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return SearchPageFragment()
        }
    }
}