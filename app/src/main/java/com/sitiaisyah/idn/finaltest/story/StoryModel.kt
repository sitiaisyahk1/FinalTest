package com.sitiaisyah.idn.finaltest.story

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryModel (
    var username: String,
    var img : Int
) : Parcelable