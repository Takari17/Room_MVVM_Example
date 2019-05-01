package com.example.roomexample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

/*
Methods that will interact with the database
 */
@Dao
interface Dao {

    @Insert
    fun insert(data: Data): Completable

    @Query("DELETE FROM data_table")
    fun deleteAll(): Completable

    @Query("select * from data_table order by dataString asc") // acending order
    fun listenToDbData(): Flowable<List<Data>>
}
