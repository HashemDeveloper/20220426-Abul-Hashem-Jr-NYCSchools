package com.chase.interview.project.models

import com.google.gson.annotations.SerializedName

data class SatScoreDataObj(
    val dbn: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("num_of_sat_test_takers")
    val numOfSATTakes: String,
    @SerializedName("sat_critical_reading_avg_score")
    val criticalReadingAvgScore:String,
    @SerializedName("sat_math_avg_score")
    val satMathAvgScore: String,
    @SerializedName("sat_writing_avg_score")
    val satWritingAvgScore: String
)
