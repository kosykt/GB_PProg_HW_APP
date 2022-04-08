package com.example.mytranslator.ui.favoritefragment

import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.model.FavoriteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class FavoriteViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val getAllFavoritesUseCase = mock<GetAllFavoritesUseCase>()
    private val deleteFavoriteUseCase = mock<DeleteFavoriteUseCase>()

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Mockito.reset(getAllFavoritesUseCase)
        Mockito.reset(deleteFavoriteUseCase)
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun should_return_correct_favoriteWords() {
        CoroutineScope(Dispatchers.Main).launch {
            val expected: Flow<List<FavoriteModel>> = flowOf(
                listOf(
                    FavoriteModel("test_1", "model_1"),
                    FavoriteModel("test_2", "model_2"),
                    FavoriteModel("test_3", "model_3"),
                )
            )
            Mockito.`when`(getAllFavoritesUseCase.execute()).thenReturn(expected)
            val viewModel = FavoriteViewModel(getAllFavoritesUseCase, deleteFavoriteUseCase)
            val actual = viewModel.favoriteWords.value
            Assert.assertEquals("list not equals", expected.first(), actual)
        }
    }

    @Test
    fun should_return_unCorrect_favoriteWords() {
        CoroutineScope(Dispatchers.Main).launch {
            val expected: Flow<List<FavoriteModel>> = flowOf(
                listOf(
                    FavoriteModel("test_1", "model_1"),
                    FavoriteModel("test_2", "model_2"),
                    FavoriteModel("test_3", "model_3"),
                )
            )
            Mockito.`when`(getAllFavoritesUseCase.execute()).thenReturn(expected)
            val viewModel = FavoriteViewModel(getAllFavoritesUseCase, deleteFavoriteUseCase)
            val actual = viewModel.favoriteWords.value.last()
            Assert.assertNotEquals("list equals", expected.first(), actual)
        }
    }

    @Test
    fun should_delete_favoriteWords() {
        CoroutineScope(Dispatchers.Main).launch {
            val model = FavoriteModel("test_1", "model_1")
            val expected: Flow<List<FavoriteModel>> = flowOf(
                listOf(
                    FavoriteModel("test_2", "model_2"),
                    FavoriteModel("test_3", "model_3"),
                )
            )
            Mockito.`when`(getAllFavoritesUseCase.execute()).thenReturn(expected)
            val viewModel = FavoriteViewModel(getAllFavoritesUseCase, deleteFavoriteUseCase)
            viewModel.deleteFavorite(model)
            Mockito.verify(deleteFavoriteUseCase).execute(model)
            val actual = viewModel.favoriteWords.value.last()
            Assert.assertNotEquals("list equals", expected.first(), actual)
        }
    }
}