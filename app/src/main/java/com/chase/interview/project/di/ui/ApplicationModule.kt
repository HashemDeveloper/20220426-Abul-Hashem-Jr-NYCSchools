package com.chase.interview.project.di.ui

import android.content.Context
import com.chase.interview.project.NYCSchoolsApp
import com.chase.interview.project.data.local.ISharedPrefService
import com.chase.interview.project.data.local.SharedPrefService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun provideContext(app: NYCSchoolsApp): Context {
        return app
    }
    @Singleton
    @Provides
    fun provideSharedPrefService(context: Context): ISharedPrefService {
        return SharedPrefService.invoke(context)
    }
}