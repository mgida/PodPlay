package com.example.podplay.di

import com.example.podplay.data.remote.PodcastRemoteDataSource
import com.example.podplay.data.repo.PodcastRepoImpl
import com.example.podplay.domain.repo.PodcastRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PodcastRepoModule {

    @Provides
    @Singleton
    fun providePodcastRepo(
        remoteDataSource: PodcastRemoteDataSource
    ): PodcastRepo =
        PodcastRepoImpl(
            remoteDataSource = remoteDataSource
        )
}