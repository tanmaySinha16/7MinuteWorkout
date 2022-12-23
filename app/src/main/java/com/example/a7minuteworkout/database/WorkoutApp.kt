package com.example.a7minuteworkout.database

import android.app.Application
import com.example.a7minuteworkout.database.HistoryDatabase


class WorkoutApp:Application() {

    val db by lazy{
        HistoryDatabase.getInstance(this)
    }

}