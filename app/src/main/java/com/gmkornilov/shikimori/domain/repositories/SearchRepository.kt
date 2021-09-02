package com.gmkornilov.shikimori.domain.repositories

interface SearchRepository {
    fun getRecentSearches(): List<String>

    fun saveSearch(search: String)
}