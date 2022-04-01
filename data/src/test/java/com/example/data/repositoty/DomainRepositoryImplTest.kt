package com.example.data.repositoty

import com.example.gb_pprog.data.database.model.RoomModel
import com.example.gb_pprog.data.network.model.Meaning
import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.data.repository.*
import com.example.gb_pprog.domain.model.DomainMeaning
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.domain.model.DomainTranslation
import com.example.gb_pprog.domain.model.FavoriteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class DomainRepositoryImplTest {

    private val network = mock<NetworkRepository>()
    private val database = mock<DatabaseRepository>()

    @After
    fun tearDown() {
        Mockito.reset(network)
        Mockito.reset(database)
    }

    @Test
    fun should_get_notNull_favorites_data() {
        val domainRepositoryImpl = DomainRepositoryImpl(network, database)
        val testData: Flow<List<RoomModel>> = flowOf(
            listOf(
                RoomModel("test0", "data0"),
                RoomModel("test1", "data1"),
                RoomModel("test2", "data2"),
            )
        )
        Mockito.`when`(database.getAll()).thenReturn(testData)
        Assert.assertNotNull("Return data is null", domainRepositoryImpl.getAllFavorite())
    }

    @Test
    fun should_get_correct_favorites_data() {
        val domainRepositoryImpl = DomainRepositoryImpl(network, database)
        val testData: Flow<List<RoomModel>> = flowOf(
            listOf(
                RoomModel("test0", "data0"),
                RoomModel("test1", "data1"),
                RoomModel("test2", "data2"),
            )
        )
        Mockito.`when`(database.getAll()).thenReturn(testData)
        runBlocking {
            val actual = domainRepositoryImpl.getAllFavorite().toList()
            val expected = testData.map { it.toFavoriteModel() }.toList()
            Assert.assertEquals("Return data is not equal to input data", expected, actual)
        }
    }

    @Test
    fun should_get_un_correct_favorites_data() {
        val domainRepositoryImpl = DomainRepositoryImpl(network, database)
        val testData: Flow<List<RoomModel>> = flowOf(
            listOf(
                RoomModel("test0", "data0"),
                RoomModel("test1", "data1"),
                RoomModel("test2", "data2"),
            )
        )
        Mockito.`when`(database.getAll()).thenReturn(testData)
        runBlocking {
            val actual = domainRepositoryImpl.getAllFavorite().toList()
            val expected = flowOf(FavoriteModel("test0", "data0")).toList()
            Assert.assertNotEquals("Return data is equal to input data", expected, actual)
        }
    }

    @Test
    fun should_get_notNull_translate_data() {
        val domainRepositoryImpl = DomainRepositoryImpl(network, database)
        val testData: List<RetrofitTranslateDto> =
            listOf<RetrofitTranslateDto>(
                RetrofitTranslateDto(0, listOf<Meaning>(), "test1"),
                RetrofitTranslateDto(1, listOf<Meaning>(), "test2"),
                RetrofitTranslateDto(2, listOf<Meaning>(), "test3"),
            )
        runBlocking {
            Mockito.`when`(network.getData("test")).thenReturn(testData)
            Assert.assertNotNull("Return data is null", domainRepositoryImpl.translate("test"))
        }
    }

    @Test
    fun should_get_correct_translate_data() {
        val domainRepositoryImpl = DomainRepositoryImpl(network, database)
        val testData: List<RetrofitTranslateDto> =
            listOf<RetrofitTranslateDto>(
                RetrofitTranslateDto(0, listOf<Meaning>(), "test1"),
                RetrofitTranslateDto(1, listOf<Meaning>(), "test2"),
                RetrofitTranslateDto(2, listOf<Meaning>(), "test3"),
            )
        runBlocking {
            Mockito.`when`(network.getData("test")).thenReturn(testData)
            val actual = domainRepositoryImpl.translate("test")
            val expected = testData.toListDomainModel()
            Assert.assertEquals("Return data is not equal to input data", expected, actual)
        }
    }

    @Test
    fun should_get_un_correct_translate_data() {
        val domainRepositoryImpl = DomainRepositoryImpl(network, database)
        val testData: List<RetrofitTranslateDto> =
            listOf<RetrofitTranslateDto>(
                RetrofitTranslateDto(0, listOf<Meaning>(), "test1"),
                RetrofitTranslateDto(1, listOf<Meaning>(), "test2"),
                RetrofitTranslateDto(2, listOf<Meaning>(), "test3"),
            )
        runBlocking {
            Mockito.`when`(network.getData("test")).thenReturn(testData)
            val actual = domainRepositoryImpl.translate("test")
            val expected = DomainModel(0, listOf<DomainMeaning>(), "test1")
            Assert.assertNotEquals("Return data is equal to input data", expected, actual)
        }
    }

    @Test
    fun verify_saveFavorite() {
        val domainRepositoryImpl = DomainRepositoryImpl(network, database)
        val domainMeaning = DomainMeaning(
            id = 1,
            imageUrl = "imageUrl",
            partOfSpeechCode = "part",
            previewUrl = "preview",
            soundUrl = "sound",
            transcription = "transcription",
            translation = DomainTranslation(note = "note", text = "text")

        )
        val listMeanings = listOf(domainMeaning, domainMeaning)
        val domainModel = DomainModel(0, listMeanings, "test")
        val roomModel = domainModel.toRoomModel()
        runBlocking {
            domainRepositoryImpl.saveFavorite(domainModel)
            Mockito.verify(database).insert(roomModel)
        }
    }

    @Test
    fun verify_deleteFavorite() {
        val domainRepositoryImpl = DomainRepositoryImpl(network, database)
        val domainMeaning = DomainMeaning(
            id = 1,
            imageUrl = "imageUrl",
            partOfSpeechCode = "part",
            previewUrl = "preview",
            soundUrl = "sound",
            transcription = "transcription",
            translation = DomainTranslation(note = "note", text = "text")

        )
        val listMeanings = listOf(domainMeaning, domainMeaning)
        val domainModel = DomainModel(0, listMeanings, "test")
        val roomModel = domainModel.toRoomModel()
        val favoriteModel = roomModel.toFavoriteModel()
        runBlocking {
            domainRepositoryImpl.deleteFavorite(favoriteModel)
            Mockito.verify(database).delete(roomModel)
        }
    }
}