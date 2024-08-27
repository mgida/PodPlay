package com.example.podplay.data.repo

import com.example.podplay.data.remote.PodcastRemoteDataSource
import com.example.podplay.domain.repo.PodcastRepo

class PodcastRepoImpl(
    val remoteDataSource: PodcastRemoteDataSource
) : PodcastRepo {

    override suspend fun getBestPodcasts() = remoteDataSource.getBestPodcasts()
    override suspend fun getGenres() = remoteDataSource.getGenres()
}