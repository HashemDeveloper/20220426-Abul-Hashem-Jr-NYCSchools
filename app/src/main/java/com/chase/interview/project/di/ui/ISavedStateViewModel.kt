package com.chase.interview.project.di.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface ISavedStateViewModel<V : ViewModel?> {
    fun create(savedStateHandle: SavedStateHandle): V
}