package com.chase.interview.project.di.ui

import android.content.Context
import com.chase.interview.project.NYCSchoolsApp
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
}