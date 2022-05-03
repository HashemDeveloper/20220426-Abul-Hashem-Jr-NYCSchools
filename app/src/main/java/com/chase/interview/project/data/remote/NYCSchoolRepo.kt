package com.chase.interview.project.data.remote

import com.chase.interview.project.data.NYCOpenDataAPI
import com.chase.interview.project.models.SatScoreDataObj
import com.chase.interview.project.utils.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class NYCSchoolRepo @Inject constructor(val dataSource: NYCOpenDataAPI): INYCSchoolRepo {
    override fun getSATScores(dbn: String): Flow<RequestState<List<SatScoreDataObj>>> = flow {
        emit(RequestState.Loading)
        val response: Response<List<SatScoreDataObj>> = dataSource.getSATScores(dbn)
        try {
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    emit(RequestState.Success(res))
                }
            } else {
                emit(RequestState.Error(Throwable(response.message())))
            }
        } catch (ex: Exception) {
            emit(RequestState.Error(Throwable(response.message())))
        }

    }.flowOn(Dispatchers.IO)
}