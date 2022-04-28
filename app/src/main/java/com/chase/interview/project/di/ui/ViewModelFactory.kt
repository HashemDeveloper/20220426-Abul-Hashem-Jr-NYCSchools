package com.chase.interview.project.di.ui

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

@Suppress("UNCHECKED_CAST")
class ViewModelFactory<out V : ViewModel?>(private val savedStateViewModel: ISavedStateViewModel<V>,
                                           owner: SavedStateRegistryOwner, defaultArg: Bundle?= null) :
    AbstractSavedStateViewModelFactory(owner, defaultArg) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return this.savedStateViewModel.create(handle) as T
    }
}

@MainThread
inline fun <reified VM : ViewModel?> SavedStateRegistryOwner.withFactory(
    factory: ISavedStateViewModel<VM>,
    defaultArgs: Bundle? = null
) = ViewModelFactory(factory, this, defaultArgs)