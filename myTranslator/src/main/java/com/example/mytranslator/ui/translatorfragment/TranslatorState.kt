package com.example.mytranslator.ui.translatorfragment

import com.example.gb_pprog.domain.model.DomainModel

sealed class TranslatorState {

    data class Success(val response: List<DomainModel>?): TranslatorState()
    data class Error(val error: String): TranslatorState()
    object Loading : TranslatorState()
}
