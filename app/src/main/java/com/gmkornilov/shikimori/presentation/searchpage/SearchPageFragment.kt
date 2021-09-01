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
import com.gmkornilov.shikimori.presentation.extensions.mapVisibility
import com.gmkornilov.shikimori.presentation.items.animepreview.AnimePreviewAdapter
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackNavigationManager
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

    private val animePreviewAdapter: AnimePreviewAdapter by lazy {
        AnimePreviewAdapter(viewModel)
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
        with (binding) {
            viewModel.loading.observe(viewLifecycleOwner, {
                loadingProgress.visibility = mapVisibility(it)
            })
        }
    }

    private fun bindError() {
        with (binding) {
            viewModel.exception.observe(viewLifecycleOwner, {
                errorLayout.root.visibility = mapVisibility(it)
            })
            errorLayout.reloadButton.setOnClickListener {

            }
        }
    }

    private fun bindContent() {
        with (binding) {
            searchedPreviewList.adapter = animePreviewAdapter

            viewModel.loadedWithoutErrors.observe(viewLifecycleOwner, {
                searchedPreviewList.visibility = mapVisibility(it)
            })

            viewModel.previews.observe(viewLifecycleOwner, {
                animePreviewAdapter.submitList(it)
            })
        }
    }

    private fun bindSearch() {
        with (binding) {
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