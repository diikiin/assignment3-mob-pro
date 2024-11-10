package com.dikin.assignment3.lazycolumn

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    private var _isDone: Boolean = false
) {
    val isDone get() = _isDone
}
