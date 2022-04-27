package com.chase.interview.project.di.ui

import com.chase.interview.project.NYCSchoolsApp
import com.chase.interview.project.di.networking.ClientModule
import com.chase.interview.project.di.networking.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityModule::class, ApplicationModule::class,
    ViewModelModule::class,RetrofitModule::class,ClientModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun buildApp(app: NYCSchoolsApp): Builder
        fun build(): ApplicationComponent
    }
    fun inject(app: NYCSchoolsApp)
}