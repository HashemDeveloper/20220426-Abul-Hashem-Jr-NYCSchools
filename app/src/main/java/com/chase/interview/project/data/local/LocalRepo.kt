package com.chase.interview.project.data.local

import android.content.Context
import com.chase.interview.project.models.SchoolDirectoryList
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import readAssetFile
import javax.inject.Inject

class LocalRepo @Inject constructor(): ILocalRepo {
    /**
     * Using flow to observe data from the UI layer
     */
    override fun getSchoolDirsFromFile(context: Context): Flow<SchoolDirectoryList> = flow {
        val gson = Gson()
        val data = readAssetFile(context,"2020_DOE_High_School_Directory.json")
        val schoolDirectoryList: SchoolDirectoryList = gson.fromJson(data, SchoolDirectoryList::class.java)
        emit(schoolDirectoryList)
    }
}