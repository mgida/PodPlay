package com.example.podplay.data.repo

import com.example.podplay.data.remote.PodcastRemoteDataSource
import com.example.podplay.domain.repo.PodcastRepo

class PodcastRepoImpl(
    val remoteDataSource: PodcastRemoteDataSource
) : PodcastRepo {

    override suspend fun getBestPodcasts(genreId:String) = remoteDataSource.getBestPodcasts(genreId)
    override suspend fun getGenres(topLevel:Int) = remoteDataSource.getGenres(topLevel)
    override suspend fun getPodcastDetails(id: String, sort:String) = remoteDataSource.getPodcastDetails(id = id, sortBy = sort)
}