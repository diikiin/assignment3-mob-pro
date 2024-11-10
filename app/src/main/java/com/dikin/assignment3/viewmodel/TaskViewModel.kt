package com.dikin.assignment3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dikin.assignment3.lazycolumn.Task

class TaskViewModel : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>(DataProvider.taskList)
    val tasks: LiveData<List<Task>> = _tasks

    fun addTask(title: String, description: String) {
        val newTask = Task(DataProvider.id, title, description)
        _tasks.value = _tasks.value?.plus(newTask)
    }
}