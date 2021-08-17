package com.gmkornilov.shikimori.domain.models.common

data class AnimeFilter(
    val page: Int?,
    val limit: Int?,
    val order: AnimeOrder?,
    val kind: AnimeKind?,
    val status: AnimeStatus?,
    val season: String?,
    val minimalScore: Float?,
    val duration: AnimeDuration?,
    val rating: AnimeRating?,
    val genreIds: List<Int>?,
    val studioIds: List<Int>?,
    val franchises: List<String>?,
    val censored: Boolean?,
    // val mylist
    val ids: List<Long>?,
    val excludeIds: List<Long>?,
    val searchString: String?,
) {
    class Builder() {
        private var page: Int? = null
        private var limit: Int? = null
        private var order: AnimeOrder? = null
        private var kind: AnimeKind? = null
        private var status: AnimeStatus? = null
        private var season: String? = null
        private var minimalScore: Float? = null
        private var duration: AnimeDuration? = null
        private var rating: AnimeRating? = null
        private var genreIds: MutableList<Int>? = null
        private var studioIds: MutableList<Int>? = null
        private var franchises: MutableList<String>? = null
        private var censored: Boolean? = null

        // var mylist
        private var ids: MutableList<Long>? = null
        private var excludeIds: MutableList<Long>? = null
        private var searchString: String? = null

        fun page(page: Int): Builder {
            return apply {
                this.page = page
            }
        }

        fun limit(limit: Int): Builder {
            return apply {
                this.limit = limit
            }
        }

        fun order(order: AnimeOrder): Builder {
            return apply {
                this.order = order
            }
        }

        // TODO: add methods for including/excluding years
        fun kind(kind: AnimeKind): Builder {
            return apply {
                this.kind = kind
            }
        }

        // TODO: add methods for including/excluding statuses
        fun status(status: AnimeStatus): Builder {
            return apply {
                this.status = status
            }
        }

        // TODO: add methods for including/excluding years
        fun season(season: String): Builder {
            return apply {
                this.season = season
            }
        }

        fun minimalScore(minimalScore: Float): Builder {
            return apply {
                this.minimalScore = minimalScore
            }
        }

        fun duration(duration: AnimeDuration): Builder {
            return apply {
                this.duration = duration
            }
        }

        // TODO: add methods for including/excluding year ratings
        fun rating(rating: AnimeRating): Builder {
            return apply {
                this.rating = rating
            }
        }

        fun addGenreId(genreId: Int): Builder {
            return apply {
                if (this.genreIds != null) {
                    genreIds?.add(genreId)
                } else {
                    genreIds = mutableListOf(genreId)
                }
            }
        }

        fun genreIds(genreIds: List<Int>): Builder {
            return apply {
                this.genreIds = genreIds.toMutableList()
            }
        }

        fun addStudioId(studioId: Int): Builder {
            return apply {
                if (this.studioIds != null) {
                    studioIds?.add(studioId)
                } else {
                    studioIds = mutableListOf(studioId)
                }
            }
        }

        fun studioIds(studioIds: List<Int>): Builder {
            return apply {
                this.studioIds = studioIds.toMutableList()
            }
        }

        fun addFranchise(franchise: String): Builder {
            return apply {
                if (franchises != null) {
                    franchises?.add(franchise)
                } else {
                    franchises = mutableListOf(franchise)
                }
            }
        }

        fun franchises(franchises: List<String>): Builder {
            return apply {
                this.franchises = franchises.toMutableList()
            }
        }

        fun censored(censored: Boolean): Builder {
            return apply {
                this.censored = censored
            }
        }

        fun addId(id: Long): Builder {
            return apply {
                if (ids != null) {
                    ids?.add(id)
                } else {
                    ids = mutableListOf(id)
                }
            }
        }

        fun ids(ids: List<Long>): Builder {
            return apply {
                this.ids = ids.toMutableList()
            }
        }

        fun excludeId(excludeId: Long): Builder {
            return apply {
                if (excludeIds != null) {
                    excludeIds?.add(excludeId)
                } else {
                    excludeIds = mutableListOf(excludeId)
                }
            }
        }

        fun excludeIds(excludeIds: List<Long>): Builder {
            return apply {
                this.excludeIds = excludeIds.toMutableList()
            }
        }

        fun searchString(searchString: String): Builder {
            return apply {
                this.searchString = searchString
            }
        }

        fun build(): AnimeFilter {
            return AnimeFilter(
                page,
                limit,
                order,
                kind,
                status,
                season,
                minimalScore,
                duration,
                rating,
                genreIds,
                studioIds,
                franchises,
                censored,
                ids,
                excludeIds,
                searchString,
            )
        }
    }
}
