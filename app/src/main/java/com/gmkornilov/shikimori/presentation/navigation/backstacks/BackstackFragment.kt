package com.gmkornilov.shikimori.presentation.navigation.backstacks

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.BackstackLayoutBinding
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.navigation.RouterProvider
import javax.inject.Inject

class BackstackFragment : Fragment(R.layout.backstack_layout), BackPressedListener {
    private val backstackInfo: BackstackInfo by lazy {
        return@lazy requireArguments().getParcelable<BackstackInfo>(ROOT_SCREEN_KEY)!!
    }

    private val navigator: Navigator by lazy {
        AppNavigator(requireActivity(), R.id.backstackContainer, childFragmentManager)
    }

    private val binding: BackstackLayoutBinding by viewBinding(BackstackLayoutBinding::bind)

    @Inject
    lateinit var backstackNavigationManager: BackstackNavigationManager

    private val ciceroneHolder: NavigatorHolder by lazy {
        backstackNavigationManager.getCurrentBackstackNavigatorHolder(backstackInfo.getBackstackName())
    }

    private val router: Router by lazy {
        backstackNavigationManager.getCurrentBackstackRouter(backstackInfo.getBackstackName())
    }

    private val backstackChangedListener = FragmentManager.OnBackStackChangedListener {
        if (childFragmentManager.backStackEntryCount > 0) {
            with (binding) {
                toolbar.setNavigationIcon(R.drawable.ic_back_button)
            }
        } else {
            with (binding) {
                toolbar.navigationIcon = null
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ShikimoriApplication.INSTANCE.appComponent.inject(this)

        childFragmentManager.addOnBackStackChangedListener(backstackChangedListener)
        with (binding) {
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }

        if (savedInstanceState == null) {
            router.newRootScreen(backstackInfo.getRootScreen())
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

    override fun onDestroy() {
        super.onDestroy()

        if (isRemoving) {
            backstackNavigationManager.clearCurrentBackstackComponent()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) {
            backstackNavigationManager.changeBackstack(backstackInfo.getBackstackName())
        }
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
                router.exit()
                true
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