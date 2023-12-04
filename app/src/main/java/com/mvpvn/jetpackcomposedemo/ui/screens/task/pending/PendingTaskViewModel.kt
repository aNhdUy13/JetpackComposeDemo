package com.mvpvn.jetpackcomposedemo.ui.screens.task.pending

import androidx.lifecycle.ViewModel
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.stateFlow
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskTimeline
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.models.PendingTaskTimeline
import com.mvpvn.jetpackcomposedemo.utilities.getFormattedDatesOfMonth
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PendingTaskViewModel() : ViewModel() {

    val pendingTaskUiState = stateFlow(PendingTaskState())

    init {
        val taskList = getTaskList()
        val timelineList = getFormattedDatesOfMonth().map {
            PendingTaskTimeline(it, taskList)
        }

        pendingTaskUiState.update {
            it.copy(
                timelineList = timelineList,
                taskList = taskList.toList()
            )
        }
    }

    private fun getTaskList() = arrayListOf<Task>().apply {
        val taskList = mutableListOf<Task>()
        for (i in 1..4) {
            taskList.add(
                Task(
                    title = "Task $i",
                    startTime = "07:00",
                    endTime = "07:15",
                    categories = emptyList(),
                    color =
                    when (i) {
                        1 -> R.color.divider_purple
                        2 -> R.color.red_white
                        3 -> R.color.green
                        4 -> R.color.blue
                        else -> R.color.divider_purple
                    }
                )
            )
        }
        addAll(taskList)
    }
}

data class PendingTaskState(
    val isLoading: Boolean = false,
    val timelineList: List<PendingTaskTimeline> = emptyList(),
    val taskList: List<Task> = emptyList()
)