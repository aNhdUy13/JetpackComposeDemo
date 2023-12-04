package com.mvpvn.jetpackcomposedemo.ui.screens.task.pending

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
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.models.PendingTaskHeaderTitle
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.models.PendingTaskSearch
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.models.PendingTaskTimeline


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

    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(pendingTaskItemList) { index, item ->
            val itemModifier = when (index) {
                secondItemPosition -> Modifier.padding(
                    top = provideDimensions().dp23,
                    start = provideDimensions().dp24,
                    end = provideDimensions().dp24
                )

                else -> Modifier.padding(
                    top = provideDimensions().dp24
                )
            }
            when (item) {
                is PendingTaskHeaderTitle -> PendingTaskHeaderItemView(
                    item = item,
                    modifier = if (index == secondItemPosition) itemModifier else Modifier
                )
                is PendingTaskSearch -> PendingTaskSearchItemView(item, Modifier)
                is PendingTaskTimeline -> PendingTaskTimelineItemView(item, itemModifier)
            }
        }
    }
}

private fun PendingTaskState.toUiList() = arrayListOf<Any>().apply {
    add(PendingTaskSearch())
    add(PendingTaskHeaderTitle("Dec 2023"))
    addAll(timelineList)
}