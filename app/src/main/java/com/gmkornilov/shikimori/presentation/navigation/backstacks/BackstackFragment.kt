package com.gmkornilov.shikimori.presentation.navigation.backstacks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.di.backstack.BackstackNavigation
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.navigation.RouterProvider
import javax.inject.Inject

class BackstackFragment : Fragment(R.layout.backstack_layout), BackPressedListener {
    private val rootScreenInfo: BackstackInfo by lazy {
        return@lazy requireArguments().getParcelable<BackstackInfo>(ROOT_SCREEN_KEY)!!
    }

    private val navigator: Navigator by lazy {
        AppNavigator(requireActivity(), R.id.backstackContainer, childFragmentManager)
    }

    @Inject
    @BackstackNavigation
    lateinit var ciceroneHolder: NavigatorHolder

    @Inject
    @BackstackNavigation
    lateinit var router: Router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ShikimoriApplication.instance.backstackComponentManager.getCurrentBackstackComponent(
            rootScreenInfo.getBackstackName()
        ).inject(this)

        if (savedInstanceState == null) {
            router.newRootScreen(rootScreenInfo.getRootScreen())
        }
    }

    override fun onStart() {
        super.onStart()
        ciceroneHolder.setNavigator(navigator)
    }

    override fun onStop() {
        super.onStop()
        ciceroneHolder.removeNavigator()
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.backstackContainer)
        return if (fragment != null && fragment is BackPressedListener) {
            fragment.onBackPressed()
        } else {
            val activity = activity
            if (activity is RouterProvider) {
                activity.router.exit()
                true
            } else {
                false
            }
        }
    }

    companion object {
        private const val ROOT_SCREEN_KEY = "ROOT_SCREEN_KEY"

        fun newInstance(rootScreenInfo: BackstackInfo): Fragment = BackstackFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ROOT_SCREEN_KEY, rootScreenInfo)
            }
        }
    }
}