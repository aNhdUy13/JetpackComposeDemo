package com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.models

import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task

data class PendingTaskTimeline(val time: String = "", val taskList: List<Task> = emptyList())