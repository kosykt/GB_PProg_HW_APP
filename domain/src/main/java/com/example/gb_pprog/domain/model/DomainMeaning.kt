package com.example.gb_pprog.domain.model

data class DomainMeaning(
    val id: Int,
    val imageUrl: String,
    val partOfSpeechCode: String,
    val previewUrl: String,
    val soundUrl: String,
    val transcription: String,
    val translation: DomainTranslation,
)
