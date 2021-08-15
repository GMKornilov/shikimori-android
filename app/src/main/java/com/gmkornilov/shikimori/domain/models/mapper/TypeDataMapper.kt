package com.gmkornilov.shikimori.domain.models.mapper

interface TypeDataMapper<From, To> {
    fun map(from: From): To

    fun mapList(fromCollection: List<From>): List<To> {
        return fromCollection.map { map(it) }
    }
}