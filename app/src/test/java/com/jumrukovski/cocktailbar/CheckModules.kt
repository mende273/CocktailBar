package com.jumrukovski.cocktailbar

import com.jumrukovski.cocktailbar.di.appModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

class CheckModules {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkKoinModule() {
        // Verify Koin configuration
        appModule.verify(
            extraTypes = listOf(
                okhttp3.logging.HttpLoggingInterceptor.Logger::class,
                java.io.File::class
            )
        )
    }
}
