package com.chase.interview.project.base

import FLAGS_FULLSCREEN
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import com.chase.interview.project.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity: AppCompatActivity() {
    protected abstract fun getLayout(): Int
    protected abstract fun getAppTheme(): Int
    protected abstract fun getNavLayout(): Int
    protected abstract fun getDestination(): Int
    protected abstract fun loadDatFromFile()
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(getAppTheme())
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val graphInflater: NavInflater = navHostFragment.navController.navInflater
        val navGraph: NavGraph = graphInflater.inflate(getNavLayout())
        val navController: NavController = navHostFragment.navController
        this.controller = navController
        val destination: Int = getDestination()
        navGraph.setStartDestination(destination)
        navController.graph = navGraph
        loadDatFromFile()
    }

    override fun onResume() {
        super.onResume()
        container?.postDelayed({
            container?.systemUiVisibility = FLAGS_FULLSCREEN
        }, IMMERSIVE_FLAG_TIMEOUT)
    }

    fun getNavController(): NavController {
        return this.controller
    }
    companion object {
        private const val IMMERSIVE_FLAG_TIMEOUT = 500L
    }
}