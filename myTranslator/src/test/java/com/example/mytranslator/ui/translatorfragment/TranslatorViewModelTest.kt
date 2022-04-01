package com.example.mytranslator.ui.translatorfragment

import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.GetTranslateUseCase
import com.example.gb_pprog.domain.SaveFavoriteUseCase
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.domain.model.FavoriteModel
import com.example.mytranslator.di.TranslatorSubcomponentProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class TranslatorViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val getAllFavoritesUseCase = mock<GetAllFavoritesUseCase>()
    private val getTranslateUseCase = mock<GetTranslateUseCase>()
    private val saveFavoriteUseCase = mock<SaveFavoriteUseCase>()
    private val deleteFavoriteUseCase = mock<DeleteFavoriteUseCase>()
    private val translatorSubcomponentProvider = mock<TranslatorSubcomponentProvider>()

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Mockito.reset(
            getAllFavoritesUseCase,
            getTranslateUseCase,
            saveFavoriteUseCase,
            deleteFavoriteUseCase,
            translatorSubcomponentProvider
        )
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun translatorState_initialValue_notNull() {
        runBlocking {
            val viewModel = TranslatorViewModel(
                getAllFavoritesUseCase,
                getTranslateUseCase,
                saveFavoriteUseCase,
                deleteFavoriteUseCase,
                translatorSubcomponentProvider
            )
            delay(1000)
            Assert.assertNotNull("translatorState is null", viewModel.translatorState)
        }
    }

    @Test
    fun should_return_correct_translatorState_initialValue() {
        runBlocking {
            val viewModel = TranslatorViewModel(
                getAllFavoritesUseCase,
                getTranslateUseCase,
                saveFavoriteUseCase,
                deleteFavoriteUseCase,
                translatorSubcomponentProvider
            )
            delay(1000)
            val expected = TranslatorState.Success(emptyList())
            val actual = viewModel.translatorState.value
            Assert.assertEquals("translatorState is not correct", expected, actual)
        }
    }

    @Test
    fun should_return_notCorrect_translatorState_initialValue() {
        runBlocking {
            val viewModel = TranslatorViewModel(
                getAllFavoritesUseCase,
                getTranslateUseCase,
                saveFavoriteUseCase,
                deleteFavoriteUseCase,
                translatorSubcomponentProvider
            )
            delay(1000)
            val expected = TranslatorState.Success(listOf(DomainModel(0, emptyList(), "test")))
            val actual = viewModel.translatorState.value
            Assert.assertNotEquals("translatorState is not correct", expected, actual)
        }
    }

    @Test
    fun should_get_the_correct_translation_of_the_word() {
        runBlocking {
            val viewModel = TranslatorViewModel(
                getAllFavoritesUseCase,
                getTranslateUseCase,
                saveFavoriteUseCase,
                deleteFavoriteUseCase,
                translatorSubcomponentProvider
            )
            delay(1000)
            val listModels = flowOf(
                listOf(
                    DomainModel(id = 0, meanings = emptyList(), text = "test_0")
                )
            )
            Mockito.`when`(getTranslateUseCase.execute("test")).thenReturn(listModels)
            viewModel.getTranslate("test")
            delay(1000)
            val expected = TranslatorState.Success(listModels.first())
            val actual = viewModel.translatorState.value
            Assert.assertEquals("translation is not correct", expected, actual)
        }
    }

    @Test
    fun should_get_the_unCorrect_translation_of_the_word() {
        runBlocking {
            val viewModel = TranslatorViewModel(
                getAllFavoritesUseCase,
                getTranslateUseCase,
                saveFavoriteUseCase,
                deleteFavoriteUseCase,
                translatorSubcomponentProvider
            )
            delay(1000)
            val listModels = flowOf(
                listOf(
                    DomainModel(id = 0, meanings = emptyList(), text = "test_0")
                )
            )
            Mockito.`when`(getTranslateUseCase.execute("test")).thenReturn(listModels)
            viewModel.getTranslate("test")
            delay(1000)
            val expected = TranslatorState.Loading
            val actual = viewModel.translatorState.value
            Assert.assertNotEquals("translation is not correct", expected, actual)
        }
    }

    @Test
    fun should_return_true_if_favoriteWords_contain_domainModel() {
        runBlocking {
            val favoriteWords: Flow<List<FavoriteModel>> = flowOf(
                listOf(
                    FavoriteModel(word = "test_1", translation = "model_1"),
                    FavoriteModel(word = "test_2", translation = "model_2"),
                    FavoriteModel(word = "test_3", translation = "model_3"),
                )
            )
            val domainModel = DomainModel(id = 0, meanings = emptyList(), text = "test_1")
            Mockito.`when`(getAllFavoritesUseCase.execute()).thenReturn(favoriteWords)
            val viewModel = TranslatorViewModel(
                getAllFavoritesUseCase,
                getTranslateUseCase,
                saveFavoriteUseCase,
                deleteFavoriteUseCase,
                translatorSubcomponentProvider
            )
            delay(1000)
            val actual = viewModel.checkIsFavorite(domainModel)
            Assert.assertTrue("is not contain = false", actual)
        }
    }

    @Test
    fun should_return_false_if_favoriteWords_notContain_domainModel() {
        runBlocking {
            val favoriteWords: Flow<List<FavoriteModel>> = flowOf(
                listOf(
                    FavoriteModel(word = "test_1", translation = "model_1"),
                    FavoriteModel(word = "test_2", translation = "model_2"),
                    FavoriteModel(word = "test_3", translation = "model_3"),
                )
            )
            val domainModel = DomainModel(id = 0, meanings = emptyList(), text = "test_4")
            Mockito.`when`(getAllFavoritesUseCase.execute()).thenReturn(favoriteWords)
            val viewModel = TranslatorViewModel(
                getAllFavoritesUseCase,
                getTranslateUseCase,
                saveFavoriteUseCase,
                deleteFavoriteUseCase,
                translatorSubcomponentProvider
            )
            delay(1000)
            val actual = viewModel.checkIsFavorite(domainModel)
            Assert.assertFalse("is contain = true", actual)
        }
    }

    @Test
    fun should_return_false_if_favoriteWordClickHandler_deleted_the_model(){
        runBlocking {
            val favoriteWords: Flow<List<FavoriteModel>> = flowOf(
                listOf(
                    FavoriteModel(word = "test_1", translation = "model_1"),
                    FavoriteModel(word = "test_2", translation = "model_2"),
                    FavoriteModel(word = "test_3", translation = "model_3"),
                )
            )
            val domainModel = DomainModel(id = 0, meanings = emptyList(), text = "test_1")
            Mockito.`when`(getAllFavoritesUseCase.execute()).thenReturn(favoriteWords)
            val viewModel = TranslatorViewModel(
                getAllFavoritesUseCase,
                getTranslateUseCase,
                saveFavoriteUseCase,
                deleteFavoriteUseCase,
                translatorSubcomponentProvider
            )
            delay(1000)
            val actual = viewModel.favoriteWordClickHandler(domainModel)
            Assert.assertFalse("is contain = true", actual)
        }
    }

    @Test
    fun should_return_true_if_favoriteWordClickHandler_saved_the_model(){
        runBlocking {
            val favoriteWords: Flow<List<FavoriteModel>> = flowOf(
                listOf(
                    FavoriteModel(word = "test_1", translation = "model_1"),
                    FavoriteModel(word = "test_2", translation = "model_2"),
                    FavoriteModel(word = "test_3", translation = "model_3"),
                )
            )
            val domainModel = DomainModel(id = 0, meanings = emptyList(), text = "test_4")
            Mockito.`when`(getAllFavoritesUseCase.execute()).thenReturn(favoriteWords)
            val viewModel = TranslatorViewModel(
                getAllFavoritesUseCase,
                getTranslateUseCase,
                saveFavoriteUseCase,
                deleteFavoriteUseCase,
                translatorSubcomponentProvider
            )
            delay(1000)
            val actual = viewModel.favoriteWordClickHandler(domainModel)
            Assert.assertTrue("is contain = true", actual)
        }
    }
}