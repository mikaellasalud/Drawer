package com.globe.todo

import android.app.Application
import android.util.Log
import androidx.room.Room

class TodoApp : Application() {

    lateinit var todoDb: TodoDb

    private fun initializeDb() {

        todoDb = Room.databaseBuilder(
            applicationContext, TodoDb::class.java, "todo-db"
        ).build()
    }

    override fun onCreate() {
        super.onCreate()

        initializeDb()
    }
}