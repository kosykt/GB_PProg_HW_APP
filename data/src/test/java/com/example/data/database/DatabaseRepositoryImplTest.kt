package com.example.data.database

import com.example.gb_pprog.data.database.AppDatabase
import com.example.gb_pprog.data.database.DatabaseRepositoryImpl
import com.example.gb_pprog.data.database.model.RoomModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class DatabaseRepositoryImplTest {

    private val db = mock<AppDatabase>()

    @After
    fun tearDown() {
        Mockito.reset(db)
    }

    @Test
    fun should_return_notNull() {
        val databaseRepositoryImpl = DatabaseRepositoryImpl(db)
        val testData = listOf<RoomModel>(
            RoomModel("test0", "data0"),
            RoomModel("test1", "data1"),
            RoomModel("test2", "data2"),
        )
        runBlocking {
            Mockito.`when`(db.roomDao().getAll()).thenReturn(flow {
                /** вызывает исключение NullPointerException **/
                TODO("https://developer.android.com/training/data-storage/room/testing-db")
                emit(testData)
            })
        }
    }
}