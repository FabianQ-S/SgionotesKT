package com.example.sgionoteskt.app

import android.app.Application
import androidx.room.Room
import com.example.sgionoteskt.data.db.AppDatabase

class App : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "notas_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
