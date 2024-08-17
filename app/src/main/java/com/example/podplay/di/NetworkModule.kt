package com.example.podplay.di

import com.example.podplay.data.remote.AnnotationInterceptor
import com.example.podplay.data.remote.PodcastRemoteDataSource
import com.example.podplay.util.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

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
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideRecipeRemoteDataSource(retrofit: Retrofit): PodcastRemoteDataSource =
        retrofit.create(PodcastRemoteDataSource::class.java)
}