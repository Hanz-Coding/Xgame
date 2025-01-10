package com.example.photos_homework.di

import com.example.photos_homework.data.PhotoRepositoryImpl
import com.example.photos_homework.data.api.PhotoApi
import com.example.photos_homework.data.api.PhotoApi.Companion.BASE_URL
import com.example.photos_homework.domain.repository.CacheRepository
import com.example.photos_homework.domain.repository.PhotoRepository
import com.example.photos_homework.presentation.viewmodel.PhotoViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { PhotoRepositoryImpl(get()) }.bind<PhotoRepository>()
    single { CacheRepository() }
    single { get<Retrofit>().create(PhotoApi::class.java) }
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    viewModelOf(::PhotoViewModel)
}