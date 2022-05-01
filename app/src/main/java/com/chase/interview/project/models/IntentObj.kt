package com.chase.interview.project.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IntentObj(
    val data: String
):Parcelable
