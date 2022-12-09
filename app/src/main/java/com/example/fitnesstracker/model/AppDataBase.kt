package com.example.fitnesstracker.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Calc::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun calcDao(): CalcDao

    // SINGLETON CONFIG
    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context: Context): AppDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, AppDataBase::class.java, "fitness_tracker")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}