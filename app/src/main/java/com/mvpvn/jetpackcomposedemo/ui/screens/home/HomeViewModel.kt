package com.mvpvn.jetpackcomposedemo.ui.screens.home

import androidx.lifecycle.ViewModel
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.stateFlow
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel() : ViewModel() {

    val homeUiState = stateFlow(HomeState())

    init {
        val taskList = arrayListOf<Task>()
        for (i in 1..7) {
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

        homeUiState.update {
            it.copy(taskList = taskList.toList())
        }
    }
}

data class HomeState(
    val isLoading: Boolean = false,
    val taskList: List<Task> = emptyList()
)