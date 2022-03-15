package com.example.gb_pprog.data.repository

import com.example.gb_pprog.data.database.model.RoomModel
import com.example.gb_pprog.data.network.model.Meaning
import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.data.network.model.Translation
import com.example.gb_pprog.domain.model.DomainMeaning
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.domain.model.DomainTranslation
import com.example.gb_pprog.domain.model.FavoriteModel

fun List<RetrofitTranslateDto>.toListDomainModel() = this.map {
    it.toDomainModel()
}

fun RetrofitTranslateDto.toDomainModel() = DomainModel(
    id = id,
    text = text,
    meanings = meanings.toListDomainMeaning()
)

fun List<Meaning>.toListDomainMeaning() = this.map {
    DomainMeaning(
        id = it.id,
        imageUrl = it.imageUrl,
        partOfSpeechCode = it.partOfSpeechCode,
        previewUrl = it.previewUrl,
        soundUrl = it.soundUrl,
        transcription = it.transcription,
        translation = it.translation.toDomainTranslation()
    )
}

fun Translation.toDomainTranslation(): DomainTranslation {
    return DomainTranslation(
        text = text,
        note = note ?: ""
    )
}

fun DomainModel.toRoomModel() = RoomModel(
    word = this.text,
    translation = this.meanings[0].translation.text
)

fun List<RoomModel>.toFavoriteModel() = this.map { it.toFavoriteModel() }

fun RoomModel.toFavoriteModel() = FavoriteModel(
    word = this.word,
    translation = this.translation
)

fun FavoriteModel.toRoomModel() = RoomModel(
    word = this.word,
    translation = this.translation
)