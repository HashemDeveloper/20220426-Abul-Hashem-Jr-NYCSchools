package com.chase.interview.project.di.ui

import android.content.Context
import com.chase.interview.project.NYCSchoolsApp
import com.chase.interview.project.data.local.ILocalRepo
import com.chase.interview.project.data.local.ISharedPrefService
import com.chase.interview.project.data.local.LocalRepo
import com.chase.interview.project.data.local.SharedPrefService
import com.chase.interview.project.data.remote.INYCSchoolRepo
import com.chase.interview.project.data.remote.NYCSchoolRepo
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
    @Singleton
    @Provides
    fun provideLocalRepo(localRepo: LocalRepo): ILocalRepo {
        return localRepo
    }
    @Singleton
    @Provides
    fun provideNYCSchoolRepo(repo: NYCSchoolRepo): INYCSchoolRepo {
        return repo
    }
}