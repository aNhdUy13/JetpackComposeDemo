package com.mvpvn.jetpackcomposedemo.ui.screens.task

import androidx.lifecycle.ViewModel
import com.mvpvn.jetpackcomposedemo.core.extension.stateFlow
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskTimeline
import com.mvpvn.jetpackcomposedemo.utilities.TimeFormat
import com.mvpvn.jetpackcomposedemo.utilities.getCurrentDate
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

        taskUiState.update { state ->
            state.copy(
                currentHour = currentHour,
                dateList = (0 until 7).map { currentDate.plusDays(it.toLong()) },
                taskTimelineList = getRangeOfHour().map { hour -> TaskTimeline(hour) }
            )
        }
    }

    fun updateSelectedDate(date: LocalDate) {
        taskUiState.update {
            it.copy(selectedTaskDateState = date)
        }
    }

    private fun getRangeOfHour(): List<String> {
        return (0..23).map { hour ->
            String.format("%02d:00", hour)
        }
    }
}

data class TaskState(
    val isLoading: Boolean = false,
    val currentMonthYear: String = getCurrentDate(TimeFormat.MMMM_YYYY),
    val currentHour: String = "",
    val dateList: List<LocalDate> = emptyList(),
    val taskTimelineList: List<TaskTimeline> = emptyList(),
    val selectedTaskDateState: LocalDate = LocalDate.now()
)