package com.chase.interview.project.di.ui

import com.chase.interview.project.NYCSchoolsMain
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeNYCSchoolsMain(): NYCSchoolsMain
}