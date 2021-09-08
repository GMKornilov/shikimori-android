package com.gmkornilov.shikimori.presentation.animepage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.FragmentAnimePageBinding
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.gmkornilov.shikimori.presentation.components.description.descriptionAdapterDelegate
import com.gmkornilov.shikimori.presentation.components.keyvalue.keyValueAdapterDelegate
import com.gmkornilov.shikimori.presentation.components.sectionheader.sectionHeaderAdapterDelegate
import com.gmkornilov.shikimori.presentation.extensions.loadUrl
import com.gmkornilov.shikimori.presentation.components.animepreview.AnimePreview
import com.gmkornilov.shikimori.presentation.components.rating.ratingAdapterDelegate
import com.gmkornilov.shikimori.presentation.components.screenshots.screenshotAdapterDelegate
import com.gmkornilov.shikimori.presentation.components.stat.statAdapterDelegate
import com.gmkornilov.shikimori.presentation.extensions.mapVisibility
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackNavigationManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import javax.inject.Inject

class AnimePageFragment : Fragment(R.layout.fragment_anime_page) {

    @Inject
    lateinit var backstackNavigationManager: BackstackNavigationManager

    @Inject
    lateinit var animePageViewModelAssistedFactory: AnimePageViewModelAssistedFactory


    private val router: Router by lazy {
        backstackNavigationManager.getCurrentBackstackRouter()
    }

    private val binding: FragmentAnimePageBinding by viewBinding(FragmentAnimePageBinding::bind)

    private val viewModel: AnimePageViewModel by viewModels {
        AnimePageViewModelFactory(animePreview.id, animePageViewModelAssistedFactory)
    }

    private val animePreview: AnimePreview by lazy {
        requireArguments().getParcelable(PREVIEW)!!
    }

    private val adapter = AsyncListDifferDelegationAdapter(
        BaseComponent,
        sectionHeaderAdapterDelegate(),
        descriptionAdapterDelegate(),
        keyValueAdapterDelegate(),
        statAdapterDelegate(),
        ratingAdapterDelegate(),
        screenshotAdapterDelegate(),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ShikimoriApplication.INSTANCE.plusAnimePageComponent().inject(this)

        postponeEnterTransition()
        with(binding) {
            toolbar.title = animePreview.titleText

            animeThumbnail.loadUrl(animePreview.thumbnailImageUrl) {
                startPostponedEnterTransition()
            }
        }
        bindMainContent()
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
            ShikimoriApplication.INSTANCE.clearAnimePageComponent()
        }
    }

    private fun bindMainContent() {
        with(binding.contentScrolling) {
            mainContent.adapter = adapter

            viewModel.loadingData.loading.observe(viewLifecycleOwner, {
                if (it) {
                    contentShimmer.startShimmer()
                } else {
                    contentShimmer.stopShimmer()
                }
                contentShimmer.visibility = mapVisibility(it)
            })

            viewModel.loadingData.exception.observe(viewLifecycleOwner, {
                errorLayout.root.visibility = mapVisibility(it)
            })

            viewModel.loadingData.loadedWithoutErrors.observe(viewLifecycleOwner, {
                mainContent.visibility = mapVisibility(it)
            })

            viewModel.loadingData.values.observe(viewLifecycleOwner, {
                adapter.items = it
            })

            errorLayout.reloadButton.setOnClickListener {
                viewModel.loadingData.load()
            }
        }
    }

    companion object {
        const val PREVIEW = "PREVIEW"

        fun newInstance(animePreview: AnimePreview): Fragment {
            val bundle = Bundle().apply {
                putParcelable(PREVIEW, animePreview)
            }
            return AnimePageFragment().apply {
                arguments = bundle
            }
        }
    }
}