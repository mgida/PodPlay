package com.example.podplay.di

import com.example.podplay.data.remote.AnnotationInterceptor
import com.example.podplay.data.remote.PodcastRemoteDataSource
import com.example.podplay.util.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideInterceptor(): AnnotationInterceptor = AnnotationInterceptor()

    @Provides
    @Singleton
    fun provideClient(annotationInterceptor: AnnotationInterceptor): OkHttpClient =
        OkHttpClient().newBuilder().apply {
            addInterceptor(annotationInterceptor)
        }.build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideRecipeRemoteDataSource(retrofit: Retrofit): PodcastRemoteDataSource =
        retrofit.create(PodcastRemoteDataSource::class.java)
}