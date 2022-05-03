package com.chase.interview.project.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chase.interview.project.data.local.ILocalRepo
import com.chase.interview.project.data.local.ISharedPrefService
import com.chase.interview.project.data.remote.INYCSchoolRepo
import com.chase.interview.project.di.ui.ISavedStateViewModel
import com.chase.interview.project.models.SatScoreDataObj
import com.chase.interview.project.models.SchoolDirectoryList
import com.chase.interview.project.utils.RequestState
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import readAssetFile
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SharedViewModel(private val savedStateHandle: SavedStateHandle,
                      private val iSharedPrefService: ISharedPrefService,private
                      val iLocalRepo: ILocalRepo, val iNycSchoolRepo: INYCSchoolRepo): ViewModel(), CoroutineScope {
    private val job = Job()

    private val _schoolDirectories: MutableStateFlow<SchoolDirectoryList?> = MutableStateFlow(null)
    var schoolDirectories: StateFlow<SchoolDirectoryList?> = _schoolDirectories.asStateFlow()

    private val _satScoreResult: MutableStateFlow<RequestState<List<SatScoreDataObj>>?> = MutableStateFlow(null)
    val satScoreResult: StateFlow<RequestState<List<SatScoreDataObj>>?> = _satScoreResult

    fun loadSchoolDirectory(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            this@SharedViewModel.iLocalRepo.getSchoolDirsFromFile(context).collect {
                _schoolDirectories.value = it
            }
        }
    }
    fun fetchSATScores(dbn: String) {
        viewModelScope.launch (Dispatchers.IO){
            this@SharedViewModel.iNycSchoolRepo.getSATScores(dbn).collect {
                _satScoreResult.value = it
            }
        }
    }
    private fun getSchoolDirectoryFromFile(context: Context): SchoolDirectoryList {
        val gson = Gson()
        val data = readAssetFile(context,"2020_DOE_High_School_Directory.json")
        return gson.fromJson(data, SchoolDirectoryList::class.java)
    }
    fun isLaunchedFirstTime(): Boolean {
        val isFirstTime = this.iSharedPrefService.isLaunchedFirstTime()
        if (!isFirstTime) {
            this.iSharedPrefService.setIsLaunchedFirstTime(true)
        }
        return !isFirstTime
    }

    fun getFilterOption(): String {
        return this.iSharedPrefService.getFilterOption()
    }

    fun setFilterOption(title: String) {
        this.iSharedPrefService.setFilterOption(title)
    }

    class Factory @Inject constructor(
        val iSharedPrefService: ISharedPrefService,
        val iLocalRepo: ILocalRepo,
        val inycSchoolRepo: INYCSchoolRepo
    ): ISavedStateViewModel<SharedViewModel?> {
        override fun create(savedStateHandle: SavedStateHandle): SharedViewModel {
            return SharedViewModel(savedStateHandle,this.iSharedPrefService, this.iLocalRepo, this.inycSchoolRepo)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = this.job + Dispatchers.IO
}