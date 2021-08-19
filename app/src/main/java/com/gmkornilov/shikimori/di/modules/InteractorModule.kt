package com.gmkornilov.shikimori.di.modules

import com.gmkornilov.shikimori.domain.interactors.SingleUseCase
import com.gmkornilov.shikimori.domain.interactors.mainpage.AnnouncementsAnimesUseCase
import com.gmkornilov.shikimori.domain.interactors.mainpage.MostPopularAnimesUseCase
import com.gmkornilov.shikimori.domain.interactors.mainpage.MostRatedAnimesUseCase
import com.gmkornilov.shikimori.domain.interactors.mainpage.NowOnScreenAnimesUseCase
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
interface InteractorModule {
    @Named("now on screens")
    @Binds
    fun bindNowOnScreensUseCase(nowOnScreenAnimesUseCase: NowOnScreenAnimesUseCase): SingleUseCase<Unit, List<AnimePreview>>

    @Named("announcements")
    @Binds
    fun bindAnnouncementsUseCase(announcementsAnimesUseCase: AnnouncementsAnimesUseCase): SingleUseCase<Unit, List<AnimePreview>>

    @Named("most popular")
    @Binds
    fun bindMostPopularUseCase(mostPopularAnimesUseCase: MostPopularAnimesUseCase): SingleUseCase<Unit, List<AnimePreview>>

    @Named("most rated")
    @Binds
    fun bindMostRatedUseCase(mostRatedAnimesUseCase: MostRatedAnimesUseCase): SingleUseCase<Unit, List<AnimePreview>>

}