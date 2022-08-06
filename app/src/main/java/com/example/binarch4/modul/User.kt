package com.example.binarch4.modul

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var nama: String,
    ) : Parcelable
