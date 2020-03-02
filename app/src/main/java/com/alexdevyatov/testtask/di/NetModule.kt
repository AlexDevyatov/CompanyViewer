package com.alexdevyatov.testtask.di

import com.alexdevyatov.testtask.repository.Repository
import com.alexdevyatov.testtask.service.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    companion object {
        const val BASE_URL = "http://megakohz.bget.ru/test_task/"
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    internal fun provideRetrofit(gson : Gson, okHttpClient: OkHttpClient): Retrofit
            = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): ApiService
            = retrofit.create<ApiService>(ApiService::class.java)

    @Provides
    @Singleton
    internal fun provideRepository(service: ApiService): Repository = Repository(service)
}