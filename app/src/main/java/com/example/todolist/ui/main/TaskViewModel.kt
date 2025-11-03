package com.example.todolist.ui.main


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.Task
import com.example.todolist.data.repo.TaskRepository
import kotlinx.coroutines.launch


class TaskViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = TaskRepository(app).also {
        // it.init(app)
    }


    val tasks = repo.tasks


    fun add(title: String) = viewModelScope.launch {
        repo.add(title)
    }


    fun toggle(task: Task) = viewModelScope.launch {
        repo.toggle(task)
    }


    fun delete(task: Task) = viewModelScope.launch {
        repo.delete(task)
    }


    // util
    fun remainingCount(list: List<Task>): Int = list.count { !it.done }
}
