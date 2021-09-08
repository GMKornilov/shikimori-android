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
        bindStat()
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
        with(binding) {
            mainContent.adapter = adapter
        }

        viewModel.loadingData.loading.observe(viewLifecycleOwner, {

        })

        viewModel.loadingData.exception.observe(viewLifecycleOwner, {

        })

        viewModel.loadingData.loadedWithoutErrors.observe(viewLifecycleOwner, {

        })

        viewModel.loadingData.values.observe(viewLifecycleOwner, {
            adapter.items = it
        })
    }

    private fun bindStat() {
//        with(binding) {
//            contentScrolling.peopleListsList.adapter = stubAdapter
//            contentScrolling.peopleRatesList.adapter = stubAdapter
//        }
//        stubAdapter.submitList(stubStats)
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