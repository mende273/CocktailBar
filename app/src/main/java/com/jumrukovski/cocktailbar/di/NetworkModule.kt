package com.jumrukovski.cocktailbar.di

import com.jumrukovski.cocktailbar.BuildConfig
import com.jumrukovski.cocktailbar.data.datasource.RemoteDataSource
import com.jumrukovski.cocktailbar.data.network.CacheInterceptor
import com.jumrukovski.cocktailbar.data.network.NoConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get(), get(), get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideNoConnectionInterceptor() }
    single { provideCacheInterceptor() }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(RemoteDataSource.ENDPOINT)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    noConnectionInterceptor: NoConnectionInterceptor,
    cacheInterceptor: CacheInterceptor
): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addNetworkInterceptor(cacheInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(noConnectionInterceptor)
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

fun provideCacheInterceptor(): CacheInterceptor = CacheInterceptor()

fun provideNoConnectionInterceptor(): NoConnectionInterceptor = NoConnectionInterceptor()
