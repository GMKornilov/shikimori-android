package com.gmkornilov.shikimori.presentation.navigation.backstacks

import androidx.annotation.IdRes
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.presentation.navigation.backstacks.appbackstacks.CatalogBackstackInfo
import com.gmkornilov.shikimori.presentation.navigation.backstacks.appbackstacks.MainBackstackInfo
import com.gmkornilov.shikimori.presentation.navigation.backstacks.appbackstacks.SearchBackstackInfo

enum class Backstacks(
    @IdRes val tabItemId: Int,
    val backstackInfo: BackstackInfo,
) {
    HOME(R.id.page_home, MainBackstackInfo()),
    SEARCH(R.id.page_search, SearchBackstackInfo()),
    CATALOG(R.id.page_catalog, CatalogBackstackInfo());


    companion object {
        private val map = values().associateBy(Backstacks::tabItemId)

        fun findByTabItemId(tabItemId: Int) = map[tabItemId]
    }
}