package com.chase.interview.project.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class GeocodedColumn(
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("coordinates")
    var coordinates: List<Double>? = null
)
