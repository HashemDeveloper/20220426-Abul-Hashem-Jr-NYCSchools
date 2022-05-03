package com.chase.interview.project.data.remote

import com.chase.interview.project.models.SatScoreDataObj
import com.chase.interview.project.utils.RequestState
import kotlinx.coroutines.flow.Flow

interface INYCSchoolRepo {
    fun getSATScores(dbn: String): Flow<RequestState<List<SatScoreDataObj>>>
}