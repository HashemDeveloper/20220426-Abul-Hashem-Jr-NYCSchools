package com.chase.interview.project.di.ui

import com.chase.interview.project.ui.SchoolDirectoryDetailsPage
import com.chase.interview.project.ui.SchoolDirectoryPage
import com.chase.interview.project.ui.WelcomePage
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeWelcomePage(): WelcomePage
    @ContributesAndroidInjector
    abstract fun contributeSchoolDirPage(): SchoolDirectoryPage
    @ContributesAndroidInjector
    abstract fun contributeSchoolDirDetailsPage():SchoolDirectoryDetailsPage
}