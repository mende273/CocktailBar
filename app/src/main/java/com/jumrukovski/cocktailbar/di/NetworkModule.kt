package com.jumrukovski.cocktailbar.di

import android.content.Context
import com.jumrukovski.cocktailbar.BuildConfig
import com.jumrukovski.cocktailbar.data.network.ApiService
import com.jumrukovski.cocktailbar.data.network.NoConnectionInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideService(get()) }
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get(), get(), get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideNoConnectionInterceptor() }
    single { provideHttpCache(androidContext()) }
}

fun provideService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(ApiService.ENDPOINT)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    cache: Cache,
    noConnectionInterceptor: NoConnectionInterceptor
): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(noConnectionInterceptor)
        .cache(cache)
    return builder.build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    return loggingInterceptor
}

fun provideHttpCache(context: Context): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(context.cacheDir, cacheSize.toLong())
}

fun provideNoConnectionInterceptor(): NoConnectionInterceptor = NoConnectionInterceptor()
