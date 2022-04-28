package com.chase.interview.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.chase.interview.project.base.BaseActivity
import com.chase.interview.project.di.ui.withFactory
import com.chase.interview.project.viewmodel.SharedViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class NYCSchoolsMain : BaseActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var viewModelFactory: SharedViewModel.Factory
    private val sharedViewModel: SharedViewModel by viewModels {
        withFactory(viewModelFactory)
    }
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getAppTheme(): Int {
        return R.style.Theme_20220426AbulHashemJrNYCSchools
    }

    override fun getNavLayout(): Int {
       return R.navigation.nav_layout
    }

    override fun getDestination(): Int {
        val isFirstTimeLaunch: Boolean = this.sharedViewModel.isLaunchedFirstTime()
        return if (isFirstTimeLaunch) {
            R.id.welcomePage
        } else {
            R.id.schoolDirectoryPage
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return this.dispatchingAndroidInjector
    }
}