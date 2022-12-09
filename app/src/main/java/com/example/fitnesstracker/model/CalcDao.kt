package com.example.fitnesstracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalcDao {

//    @Delete
//    @Query
//    @Update
//    @Insert
    @Insert
    fun insert(calc: Calc)

    @Query("SELECT * FROM Calc WHERE type = :type ")
    fun getRegisterByType(type: String): List<Calc>
}