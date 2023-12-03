package com.mvpvn.jetpackcomposedemo.ui.screens.task.models

import com.mvpvn.jetpackcomposedemo.R

data class Task(
    val title: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val categories: List<String> = emptyList(),
    val color: Int = R.color.divider_purple
)