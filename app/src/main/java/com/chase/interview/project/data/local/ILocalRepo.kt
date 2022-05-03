package com.chase.interview.project.data.local

import android.content.Context
import com.chase.interview.project.models.SchoolDirectoryList
import kotlinx.coroutines.flow.Flow

interface ILocalRepo {
   fun getSchoolDirsFromFile(context: Context): Flow<SchoolDirectoryList>
}