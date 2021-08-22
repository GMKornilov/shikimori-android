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
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import com.gmkornilov.shikimori.presentation.mainpage.adapter.AnimePreviewAdapter
import com.gmkornilov.shikimori.presentation.mainpage.adapter.AnimePreviewClicked
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainPageBinding
    private val viewModel: MainViewModel by viewModels()

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

        })

        viewModel.nowOnScreensLoadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.nowOnScreensList.visibility = mapVisibility(it)
        })

        viewModel.nowOnScreens.observe(viewLifecycleOwner, {
            nowOnScreensAdapter.setData(it)
        })
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

        })

        viewModel.announcementsLoadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.anonsList.visibility = mapVisibility(it)
        })

        viewModel.announcements.observe(viewLifecycleOwner, {
            announcementsAdapter.setData(it)
        })
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

        })

        viewModel.mostPopularLoadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.mostPopularList.visibility = mapVisibility(it)
        })

        viewModel.mostPopular.observe(viewLifecycleOwner, {
            mostPopularAdapter.setData(it)
        })
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

        })

        viewModel.mostRatedLoadedWithoutErrors.observe(viewLifecycleOwner, {
            binding.mostRatedList.visibility = mapVisibility(it)
        })

        viewModel.mostRated.observe(viewLifecycleOwner, {
            mostRatedAdapter.setData(it)
        })
    }

    private fun mapVisibility(isVisible: Boolean): Int {
        return if (isVisible) View.VISIBLE else View.GONE
    }

    companion object {
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }
}