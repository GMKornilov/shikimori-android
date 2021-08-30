package com.gmkornilov.shikimori.presentation.searchpage

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.FragmentSearchBinding

class SearchPageFragment : Fragment(R.layout.fragment_search) {
    private val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)

    companion object {
        fun newInstance(): Fragment {
            return SearchPageFragment()
        }
    }
}