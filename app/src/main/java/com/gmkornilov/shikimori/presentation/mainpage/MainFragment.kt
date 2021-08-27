package com.gmkornilov.shikimori.presentation.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmkornilov.shikimori.databinding.FragmentMainPageBinding
import com.gmkornilov.shikimori.di.qualifiers.ViewModelQualifier
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.animepreview.AnimePreviewAdapter
import com.gmkornilov.shikimori.presentation.extensions.mapVisibility
import javax.inject.Inject

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainPageBinding

    @Inject
    @ViewModelQualifier(MainViewModel::class)
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    private val nowOnScreensAdapter by lazy { AnimePreviewAdapter(viewModel) }

    private val announcementsAdapter by lazy { AnimePreviewAdapter(viewModel) }

    private val mostPopularAdapter by lazy { AnimePreviewAdapter(viewModel) }

    private val mostRatedAdapter by lazy { AnimePreviewAdapter(viewModel) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)

        ShikimoriApplication.instance.plusMainPageComponent().inject(this)

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
                    viewModel.announcementsLoading.removeObserver(this)
                    viewModel.nowOnScreensLoading.removeObserver(this)
                    viewModel.mostPopularLoading.removeObserver(this)
                    viewModel.mostRatedLoading.removeObserver(this)
                }
            }
            viewModel.mostRatedLoading.observe(viewLifecycleOwner, observer)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isRemoving) {
            ShikimoriApplication.instance.clearMainPageComponent()
        }
    }

    private fun observeNowOnScreens() {
        binding.nowOnScreensButton.setOnClickListener {
            viewModel.nowOnScreensClicked()
        }

        viewModel.nowOnScreensLoading.observe(viewLifecycleOwner, {
            binding.nowOnScreensShimmer.visibility = mapVisibility(it)
            if (it) {
                binding.nowOnScreensShimmer.startShimmer()
            } else {
                binding.nowOnScreensShimmer.stopShimmer()
            }
        })

        viewModel.nowOnScreensError.observe(viewLifecycleOwner, {
            binding.nowOnScreensError.root.visibility = mapVisibility(it)
        })

        viewModel.nowOnScreensLoadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.nowOnScreensList.visibility = mapVisibility(it)
        })

        viewModel.nowOnScreens.observe(viewLifecycleOwner, {
            nowOnScreensAdapter.submitList(it)
        })

        binding.nowOnScreensError.reloadButton.setOnClickListener {
            viewModel.loadNowOnScreens()
        }
    }

    private fun observeAnnouncements() {
        binding.anonsButton.setOnClickListener {
            viewModel.announcementsClicked()
        }

        viewModel.announcementsLoading.observe(viewLifecycleOwner, {
            binding.anonsShimmer.visibility = mapVisibility(it)
            if (it) {
                binding.anonsShimmer.startShimmer()
            } else {
                binding.anonsShimmer.stopShimmer()
            }
        })

        viewModel.announcementsError.observe(viewLifecycleOwner, {
            binding.anonsError.root.visibility = mapVisibility(it)
        })

        viewModel.announcementsLoadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.anonsList.visibility = mapVisibility(it)
        })

        viewModel.announcements.observe(viewLifecycleOwner, {
            announcementsAdapter.submitList(it)
        })

        binding.anonsError.reloadButton.setOnClickListener {
            viewModel.loadAnnouncements()
        }
    }

    private fun observeMostPopular() {
        binding.mostPopularButton.setOnClickListener {
            viewModel.mostPopularClicked()
        }

        viewModel.mostPopularLoading.observe(viewLifecycleOwner, {
            binding.mostPopularShimmer.visibility = mapVisibility(it)
            if (it) {
                binding.mostPopularShimmer.startShimmer()
            } else {
                binding.mostPopularShimmer.stopShimmer()
            }
        })

        viewModel.mostPopularError.observe(viewLifecycleOwner, {
            binding.mostPopularError.root.visibility = mapVisibility(it)
        })

        viewModel.mostPopularLoadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.mostPopularList.visibility = mapVisibility(it)
        })

        viewModel.mostPopular.observe(viewLifecycleOwner, {
            mostPopularAdapter.submitList(it)
        })

        binding.mostPopularError.reloadButton.setOnClickListener {
            viewModel.loadMostPopular()
        }
    }

    private fun observeMostRated() {
        binding.mostRatedButton.setOnClickListener {
            viewModel.mostRatedClicked()
        }

        viewModel.mostRatedLoading.observe(viewLifecycleOwner, {
            binding.mostRatedShimmer.visibility = mapVisibility(it)
            if (it) {
                binding.mostRatedShimmer.startShimmer()
            } else {
                binding.mostRatedShimmer.stopShimmer()
            }
        })

        viewModel.mostRatedError.observe(viewLifecycleOwner, {
            binding.mostRatedError.root.visibility = mapVisibility(it)
        })

        viewModel.mostRatedLoadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.mostRatedList.visibility = mapVisibility(it)
        })

        viewModel.mostRated.observe(viewLifecycleOwner, {
            mostRatedAdapter.submitList(it)
        })

        binding.mostRatedError.reloadButton.setOnClickListener {
            viewModel.loadMostRated()
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }
}