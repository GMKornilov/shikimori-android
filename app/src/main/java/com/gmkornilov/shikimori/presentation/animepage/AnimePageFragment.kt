package com.gmkornilov.shikimori.presentation.animepage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.FragmentAnimePageBinding
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackNavigationManager
import javax.inject.Inject

class AnimePageFragment : Fragment(R.layout.fragment_anime_page) {

    @Inject
    lateinit var backstackNavigationManager: BackstackNavigationManager

    private val router: Router by lazy {
        backstackNavigationManager.getCurrentBackstackRouter()
    }

    private val binding: FragmentAnimePageBinding by viewBinding(FragmentAnimePageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ShikimoriApplication.INSTANCE.plusAnimePageComponent().inject(this)
    }

    override fun onStart() {
        super.onStart()

        with (binding) {
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

    companion object {
        const val ID = "ID"

        fun newInstance(id: Long): Fragment {
            val bundle = Bundle().apply {
                putLong(ID, id)
            }
            return AnimePageFragment().apply {
                arguments = bundle
            }
        }
    }
}