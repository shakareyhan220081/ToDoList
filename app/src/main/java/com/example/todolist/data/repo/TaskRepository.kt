package com.example.todolist.data.repo

import android.content.Context
import com.example.todolist.data.local.AppDatabase
import com.example.todolist.data.local.Task
import com.example.todolist.data.local.TaskDao


class TaskRepository(context: Context) {


    private var dao: TaskDao? = null


    fun init(context: Context) {
        dao = AppDatabase.getInstance(context).taskDao()
    }


    val tasks = AppDatabase.getInstance(context).taskDao().getAll()


    suspend fun add(title: String) {
        dao!!.insert(Task(title = title))
    }


    suspend fun toggle(task: Task) {
        dao!!.update(task.copy(done = !task.done))
    }


    suspend fun delete(task: Task) {
        dao!!.delete(task)
    }
}
