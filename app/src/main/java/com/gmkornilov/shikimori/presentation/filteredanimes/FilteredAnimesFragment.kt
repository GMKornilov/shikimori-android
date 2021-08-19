package com.gmkornilov.shikimori.presentation.filteredanimes

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter

class FilteredAnimesFragment : Fragment() {

    companion object {
        const val filterKey = "FILTER"

        fun newInstance(filter: AnimeFilter): Fragment {
            val bundle = Bundle().apply {
                putSerializable(filterKey, filter)
            }
            return FilteredAnimesFragment().apply {
                arguments = bundle
            }
        }
    }
}