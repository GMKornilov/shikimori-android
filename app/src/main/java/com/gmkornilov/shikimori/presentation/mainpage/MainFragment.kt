package com.gmkornilov.shikimori.presentation.mainpage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.FragmentMainPageBinding
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.gmkornilov.shikimori.presentation.extensions.mapVisibility
import com.gmkornilov.shikimori.presentation.components.animepreview.animeHorizontalPreviewAdapterDelegate
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackNavigationManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main_page) {
    private val binding: FragmentMainPageBinding by viewBinding(FragmentMainPageBinding::bind)

    @Inject
    lateinit var viewAssistedModelFactory: MainViewModelAssistedFactory

    @Inject
    lateinit var backstackNavigationManager: BackstackNavigationManager

    private val router: Router by lazy {
        backstackNavigationManager.getCurrentBackstackRouter()
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(router, viewAssistedModelFactory)
    }

    private val nowOnScreensAdapter by lazy {
        AsyncListDifferDelegationAdapter(
            BaseComponent,
            animeHorizontalPreviewAdapterDelegate(viewModel),
        )
    }

    private val announcementsAdapter by lazy {
        AsyncListDifferDelegationAdapter(
            BaseComponent,
            animeHorizontalPreviewAdapterDelegate(viewModel),
        )
    }

    private val mostPopularAdapter by lazy {
        AsyncListDifferDelegationAdapter(
            BaseComponent,
            animeHorizontalPreviewAdapterDelegate(viewModel),
        )
    }

    private val mostRatedAdapter by lazy {
        AsyncListDifferDelegationAdapter(
            BaseComponent,
            animeHorizontalPreviewAdapterDelegate(viewModel),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ShikimoriApplication.INSTANCE.plusMainPageComponent().inject(this)

        binding.nowOnScreensList.adapter = nowOnScreensAdapter
        binding.anonsList.adapter = announcementsAdapter
        binding.mostPopularList.adapter = mostPopularAdapter
        binding.mostRatedList.adapter = mostRatedAdapter

        observeNowOnScreens()
        observeAnnouncements()
        observeMostPopular()
        observeMostRated()

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadAll()
            val observer = object : Observer<Boolean> {
                override fun onChanged(t: Boolean) {
                    if (t) {
                        return
                    }
                    binding.swipeRefresh.isRefreshing = false
                    viewModel.announcementsLoadingData.loading.removeObserver(this)
                    viewModel.nowOnScreensLoadingData.loading.removeObserver(this)
                    viewModel.mostPopularLoadingData.loading.removeObserver(this)
                    viewModel.mostRatedLoadingData.loading.removeObserver(this)
                }
            }
            viewModel.nowOnScreensLoadingData.loading.observe(viewLifecycleOwner, observer)
            viewModel.announcementsLoadingData.loading.observe(viewLifecycleOwner, observer)
            viewModel.mostPopularLoadingData.loading.observe(viewLifecycleOwner, observer)
            viewModel.mostRatedLoadingData.loading.observe(viewLifecycleOwner, observer)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isRemoving) {
            ShikimoriApplication.INSTANCE.clearMainPageComponent()
        }
    }

    private fun observeNowOnScreens() {
        binding.nowOnScreensButton.setOnClickListener {
            viewModel.nowOnScreensClicked()
        }

        viewModel.nowOnScreensLoadingData.loading.observe(viewLifecycleOwner, {
            binding.nowOnScreensShimmer.visibility = mapVisibility(it)
            if (it) {
                binding.nowOnScreensShimmer.startShimmer()
            } else {
                binding.nowOnScreensShimmer.stopShimmer()
            }
        })

        viewModel.nowOnScreensLoadingData.exception.observe(viewLifecycleOwner, {
            binding.nowOnScreensError.root.visibility = mapVisibility(it)
        })

        viewModel.nowOnScreensLoadingData.loadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.nowOnScreensList.visibility = mapVisibility(it)
        })

        viewModel.nowOnScreensLoadingData.values.observe(viewLifecycleOwner, {
            nowOnScreensAdapter.items = it
        })

        binding.nowOnScreensError.reloadButton.setOnClickListener {
            viewModel.nowOnScreensLoadingData.load()
        }
    }

    private fun observeAnnouncements() {
        binding.anonsButton.setOnClickListener {
            viewModel.announcementsClicked()
        }

        viewModel.announcementsLoadingData.loading.observe(viewLifecycleOwner, {
            binding.anonsShimmer.visibility = mapVisibility(it)
            if (it) {
                binding.anonsShimmer.startShimmer()
            } else {
                binding.anonsShimmer.stopShimmer()
            }
        })

        viewModel.announcementsLoadingData.exception.observe(viewLifecycleOwner, {
            binding.anonsError.root.visibility = mapVisibility(it)
        })

        viewModel.announcementsLoadingData.loadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.anonsList.visibility = mapVisibility(it)
        })

        viewModel.announcementsLoadingData.values.observe(viewLifecycleOwner, {
            announcementsAdapter.items = it
        })

        binding.anonsError.reloadButton.setOnClickListener {
            viewModel.announcementsLoadingData.load()
        }
    }

    private fun observeMostPopular() {
        binding.mostPopularButton.setOnClickListener {
            viewModel.mostPopularClicked()
        }

        viewModel.mostPopularLoadingData.loading.observe(viewLifecycleOwner, {
            binding.mostPopularShimmer.visibility = mapVisibility(it)
            if (it) {
                binding.mostPopularShimmer.startShimmer()
            } else {
                binding.mostPopularShimmer.stopShimmer()
            }
        })

        viewModel.mostPopularLoadingData.exception.observe(viewLifecycleOwner, {
            binding.mostPopularError.root.visibility = mapVisibility(it)
        })

        viewModel.mostPopularLoadingData.loadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.mostPopularList.visibility = mapVisibility(it)
        })

        viewModel.mostPopularLoadingData.values.observe(viewLifecycleOwner, {
            mostPopularAdapter.items = it
        })

        binding.mostPopularError.reloadButton.setOnClickListener {
            viewModel.mostPopularLoadingData.load()
        }
    }

    private fun observeMostRated() {
        binding.mostRatedButton.setOnClickListener {
            viewModel.mostRatedClicked()
        }

        viewModel.mostRatedLoadingData.loading.observe(viewLifecycleOwner, {
            binding.mostRatedShimmer.visibility = mapVisibility(it)
            if (it) {
                binding.mostRatedShimmer.startShimmer()
            } else {
                binding.mostRatedShimmer.stopShimmer()
            }
        })

        viewModel.mostRatedLoadingData.exception.observe(viewLifecycleOwner, {
            binding.mostRatedError.root.visibility = mapVisibility(it)
        })

        viewModel.mostRatedLoadingData.loadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.mostRatedList.visibility = mapVisibility(it)
        })

        viewModel.mostRatedLoadingData.values.observe(viewLifecycleOwner, {
            mostRatedAdapter.items = it
        })

        binding.mostRatedError.reloadButton.setOnClickListener {
            viewModel.mostRatedLoadingData.load()
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }
}