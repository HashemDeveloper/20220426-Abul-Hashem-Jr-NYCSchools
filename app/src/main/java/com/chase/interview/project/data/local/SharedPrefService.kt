package com.chase.interview.project.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.chase.interview.project.utils.Common
import javax.inject.Inject

class SharedPrefService @Inject constructor(): ISharedPrefService {

    override fun isLaunchedFirstTime(): Boolean {
        return pref?.getBoolean(Common.FIRST_LAUNCHED, false)!!
    }

    override fun setIsLaunchedFirstTime(isFirstTime: Boolean) {
        pref?.edit(commit = true) {
            putBoolean(Common.FIRST_LAUNCHED, isFirstTime)
        }
    }

    override fun getFilterOption(): String {
        return pref?.getString(Common.FILTER_OPTION,"")!!
    }

    override fun setFilterOption(title: String) {
        pref?.edit(commit = true) {
            putString(Common.FILTER_OPTION,title)
        }
    }

    companion object {
        private var pref: SharedPreferences?= null
        @Volatile
        private var instance: SharedPrefService?= null
        private val LOCK = Any()

        operator fun invoke(context: Context): SharedPrefService = instance ?: synchronized(LOCK) {
            this.instance ?: buildSharedPrefService(context).also {
                instance = it
            }
        }
        private fun buildSharedPrefService(context: Context): SharedPrefService {
            this.pref = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPrefService()
        }
    }
}