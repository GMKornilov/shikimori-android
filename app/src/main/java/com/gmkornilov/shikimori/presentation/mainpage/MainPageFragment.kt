package com.gmkornilov.shikimori.presentation.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gmkornilov.shikimori.databinding.FragmentMainPageBinding
import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview
import com.gmkornilov.shikimori.presentation.mainpage.adapter.AnimePreviewAdapter
import com.gmkornilov.shikimori.presentation.mainpage.adapter.AnimePreviewClicked
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageFragment : Fragment() {
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var viewModel: MainPageViewModel

    private val nowOnScreensPreviewClicked = object : AnimePreviewClicked {
        override fun onClicked(animePreview: AnimePreview) {
            TODO("Not yet implemented")
        }
    }

    private val announcementsPreviewClicked = object : AnimePreviewClicked {
        override fun onClicked(animePreview: AnimePreview) {
            TODO("Not yet implemented")
        }
    }

    private val mostPopularPreviewClicked = object : AnimePreviewClicked {
        override fun onClicked(animePreview: AnimePreview) {
            TODO("Not yet implemented")
        }
    }

    private val mostRatedPreviewClicked = object : AnimePreviewClicked {
        override fun onClicked(animePreview: AnimePreview) {
            TODO("Not yet implemented")
        }
    }

    private val nowOnScreensAdapter = AnimePreviewAdapter(nowOnScreensPreviewClicked)
    private val announcementsAdapter = AnimePreviewAdapter(announcementsPreviewClicked)
    private val mostPopularAdapter = AnimePreviewAdapter(mostPopularPreviewClicked)
    private val mostRatedAdapter = AnimePreviewAdapter(mostRatedPreviewClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(MainPageViewModel::class.java)

        observeNowOnScreens()
        observeAnnouncements()
        observeMostPopular()
        observeMostRated()

        return binding.root
    }

    private fun observeNowOnScreens() {
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
}