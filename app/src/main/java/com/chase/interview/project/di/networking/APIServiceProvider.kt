package com.chase.interview.project.di.networking

import com.chase.interview.project.data.NYCOpenDataAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object APIServiceProvider {
    @Singleton
    @Provides
    internal fun provideAPI(retrofit: Retrofit): NYCOpenDataAPI {
        return retrofit.create(NYCOpenDataAPI::class.java)
    }
}