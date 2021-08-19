package com.gmkornilov.shikimori.presentation.animepage

import android.os.Bundle
import androidx.fragment.app.Fragment

class AnimePageFragment : Fragment() {

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