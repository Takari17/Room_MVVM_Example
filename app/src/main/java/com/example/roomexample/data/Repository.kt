package com.example.roomexample.data

import android.content.Context
import com.example.roomexample.data.local.Data
import com.example.roomexample.data.local.DataBase
/*
Not too useful when you only have 1 data source, but I'll use it nonetheless.
 */
class Repository(context: Context) {

    private val db = DataBase.invoke(context)
    private val dao = db.dao()

    fun insert(dataString: Data) = dao.insert(dataString)

    fun listenToDbData() = dao.listenToDbData()

    fun deleteAllData() = dao.deleteAll()
}