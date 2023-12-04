package com.mvpvn.jetpackcomposedemo.ui.screens.task

import androidx.lifecycle.ViewModel
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.stateFlow
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskTimeline
import com.mvpvn.jetpackcomposedemo.utilities.TimeFormat
import com.mvpvn.jetpackcomposedemo.utilities.getCurrentDate
import com.mvpvn.jetpackcomposedemo.utilities.getRangeOfHour
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel
import org.threeten.bp.LocalDate

@KoinViewModel
class TaskViewModel() : ViewModel() {
    val taskUiState = stateFlow(TaskState())

    init {
        val currentDate = LocalDate.now()
        val dividedHour = getCurrentDate().split(":")
        val currentHour = "${dividedHour[0]} h ${dividedHour[1]} min"
        val dateList = (0 until 7).map { currentDate.plusDays(it.toLong()) }
        val taskList = getTaskList()
        val taskTimelineList = getRangeOfHour().mapIndexed { index, time ->
            TaskTimeline(
                time = time,
                taskList = if (index % 4 == 0) taskList else emptyList()
            )
        }

        taskUiState.update { state ->
            state.copy(
                currentHour = currentHour,
                dateList = dateList,
                timelineList = taskTimelineList,
                taskList = taskList
            )
        }
    }

    fun updateSelectedDate(dateIndex: Int, date: LocalDate) {
        taskUiState.update {
            it.copy(
                selectedTaskDateState = date,
                taskList = if (dateIndex % 2 == 1 || dateIndex == 2) emptyList() else getTaskList()
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

data class TaskState(
    val isLoading: Boolean = false,
    val currentMonthYear: String = getCurrentDate(TimeFormat.MMMM_YYYY),
    val currentHour: String = "",
    val dateList: List<LocalDate> = emptyList(),
    val timelineList: List<TaskTimeline> = emptyList(),
    val taskList: List<Task> = emptyList(),
    val selectedTaskDateState: LocalDate = LocalDate.now()
)