package com.mvpvn.jetpackcomposedemo.ui.screens.task.pending

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.screens.base.BackActionBar
import com.mvpvn.jetpackcomposedemo.ui.screens.task.TaskBody
import com.mvpvn.jetpackcomposedemo.ui.screens.task.TaskHeader
import com.mvpvn.jetpackcomposedemo.ui.screens.task.TaskViewModel
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.models.PendingTaskHeaderTitle
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.models.PendingTaskSearch


@Composable
fun PendingTaskScreen() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val viewModel: PendingTaskViewModel = viewModel()
        PendingTaskBody(modifier = Modifier.fillMaxSize(), viewModel = viewModel)
        BackActionBar(stringResource(id = R.string.pending))
    }
}

@Composable
private fun PendingTaskBody(modifier: Modifier = Modifier, viewModel: PendingTaskViewModel) {
    val uiState by viewModel.pendingTaskUiState.collectAsState()
    val pendingTaskItemList = uiState.toUiList()
    val secondItemPosition = 1
    val thirdItemPosition = 2
    val fourthItemPosition = 3

    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(pendingTaskItemList) { index, item ->
            val itemModifier = when (index) {
                secondItemPosition -> Modifier.padding(top = provideDimensions().dp14)
                thirdItemPosition -> Modifier.padding(top = provideDimensions().dp22)
                fourthItemPosition -> Modifier.padding(
                    top = provideDimensions().dp20,
                    start = provideDimensions().dp24,
                    end = provideDimensions().dp24
                )

                pendingTaskItemList.size - 1 -> Modifier.padding(
                    top = provideDimensions().dp20,
                    bottom = provideDimensions().dp145,
                    start = provideDimensions().dp24,
                    end = provideDimensions().dp24
                )

                else -> Modifier.padding(
                    top = provideDimensions().dp10,
                    start = provideDimensions().dp24,
                    end = provideDimensions().dp24
                )
            }
            when (item) {
                is PendingTaskHeaderTitle -> PendingTaskHeaderItemView(item)
                is PendingTaskSearch -> PendingTaskSearchItemView(item, Modifier)
                is Task -> PendingTaskTimelineItemView(item)

            }
        }
    }
}

private fun PendingTaskState.toUiList() = arrayListOf<Any>().apply {
    add(PendingTaskSearch())
    add(PendingTaskHeaderTitle())
    addAll(timelineList)
}