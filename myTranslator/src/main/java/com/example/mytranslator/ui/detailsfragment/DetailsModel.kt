package com.example.mytranslator.ui.detailsfragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsModel(
    val word: String,
    val transcription: String,
    val translation: String,
    val note: String,
    val imageUrl: String,
) : Parcelable
