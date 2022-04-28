package com.chase.interview.project.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.chase.interview.project.data.local.ISharedPrefService
import com.chase.interview.project.di.ui.ISavedStateViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SharedViewModel(private val savedStateHandle: SavedStateHandle,
                      private val iSharedPrefService: ISharedPrefService): ViewModel(), CoroutineScope {
    private val job = Job()

    fun isLaunchedFirstTime(): Boolean {
        val isFirstTime = this.iSharedPrefService.isLaunchedFirstTime()
        if (!isFirstTime) {
            this.iSharedPrefService.setIsLaunchedFirstTime(true)
        }
        return !isFirstTime
    }
    class Factory @Inject constructor(
        val iSharedPrefService: ISharedPrefService
    ): ISavedStateViewModel<SharedViewModel?> {
        override fun create(savedStateHandle: SavedStateHandle): SharedViewModel {
            return SharedViewModel(savedStateHandle,this.iSharedPrefService)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = this.job + Dispatchers.IO
}