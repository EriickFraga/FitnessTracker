package com.example.fitnesstracker

import android.app.Application
import com.example.fitnesstracker.model.AppDataBase

class App : Application() {

    lateinit var db: AppDataBase

    override fun onCreate() {
        super.onCreate()
        db = AppDataBase.getDatabase(this)
    }
}