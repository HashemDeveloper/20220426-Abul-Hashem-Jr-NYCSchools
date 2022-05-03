package com.chase.interview.project.data

import com.chase.interview.project.models.SatScoreDataObj
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NYCOpenDataAPI {
    @GET("f9bf-2cp4.json")
    suspend fun getSATScores(@Query("dbn") dbn: String): Response<List<SatScoreDataObj>>
}