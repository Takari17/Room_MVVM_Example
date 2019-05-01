package com.example.roomexample.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Defines the columns for your table, this table only has 2 columns, 1 for an Id and another for the user inputted data
 */
@Entity(tableName = "data_table")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dataString: String?
)