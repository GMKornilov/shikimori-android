package com.gmkornilov.shikimori.domain.repositories

/**
 * Repository for saving and loading recent searches
 */
interface SearchRepository {
    /**
     * Loads recent user searches
     *
     * @return list of user searches
     */
    fun getRecentSearches(): List<String>

    /**
     * Saves user [search]
     *
     * @param search user search
     */
    fun saveSearch(search: String)
}