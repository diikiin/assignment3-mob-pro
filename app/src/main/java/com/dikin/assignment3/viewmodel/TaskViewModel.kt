package com.dikin.assignment3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = AppDatabase.getDatabase(application).taskDao()
    val tasks: LiveData<List<TaskEntity>> = taskDao.getAllTAsks()

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            val newTask = TaskEntity(title = title, description = description)
            taskDao.insert(newTask)
        }
    }
}