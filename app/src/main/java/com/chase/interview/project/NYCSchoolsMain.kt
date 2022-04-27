package com.chase.interview.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chase.interview.project.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class NYCSchoolsMain : BaseActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
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
        return R.id.welcomePage
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return this.dispatchingAndroidInjector
    }
}