package com.mvpvn.jetpackcomposedemo.ui.screens.task.models

data class Task(
    val title: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val categories: List<String> = emptyList()
)