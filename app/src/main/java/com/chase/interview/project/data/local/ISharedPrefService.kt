package com.chase.interview.project.data.local

interface ISharedPrefService {
    fun isLaunchedFirstTime(): Boolean
    fun setIsLaunchedFirstTime(isFirstTime: Boolean)
    fun getFilterOption(): String
    fun setFilterOption(title: String)
}