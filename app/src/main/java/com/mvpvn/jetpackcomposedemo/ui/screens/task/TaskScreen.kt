package com.mvpvn.jetpackcomposedemo.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.Dimensions
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.EmptyTask
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskDate
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskHeaderTitle
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskTimeline
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold

@Composable
fun TaskScreen() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (taskHeader) = createRefs()
        val taskViewModel: TaskViewModel = viewModel()

        TaskBody(
            taskViewModel = taskViewModel,
            modifier = Modifier.fillMaxSize()
        )

        TaskHeader(
            modifier = Modifier
                .constrainAs(taskHeader) {
                    top.linkTo(parent.top)
                },
            provideDimensions = provideDimensions()
        )
    }
}

@Composable
fun TaskHeader(modifier: Modifier, provideDimensions: Dimensions) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color.White.copy(alpha = 0f)
                    )
                )
            )

    ) {
        ConstraintLayout(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .padding(
                    start = provideDimensions.dp24,
                    end = provideDimensions.dp24,
                    top = provideDimensions.dp15,
                    bottom = provideDimensions.dp10
                )
                .background(
                    color = colorResource(id = R.color.search_bg),
                    shape = RoundedCornerShape(provideDimensions.dp15)
                )
        ) {
            val textSearchState = remember { mutableStateOf("") }

            BasicTextField(
                value = textSearchState.value,
                onValueChange = { textSearchState.value = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = textBold.copy(
                    color = colorResource(id = R.color.black),
                    fontSize = R.dimen.sp14.toSp()
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = provideDimensions.dp20,
                                vertical = provideDimensions.dp15
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search Icon",
                            tint = colorResource(id = R.color.purple_search)
                        )
                        Spacer(modifier = Modifier.width(provideDimensions.dp15))
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            innerTextField()
                            if (textSearchState.value.isEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.hint_search),
                                    style = text.copy(
                                        color = colorResource(id = R.color.hint_search),
                                        fontSize = R.dimen.sp14.toSp()
                                    )
                                )
                            }
                        }

                        val isSearchTextEmpty = textSearchState.value.isEmpty()
                        Icon(
                            painter = painterResource(id = if (isSearchTextEmpty) R.drawable.ic_clear_search_disable else R.drawable.ic_clear_search_enable),
                            contentDescription = "Delete Icon",
                            modifier = Modifier.clickable {
                                if (!isSearchTextEmpty) textSearchState.value = ""
                            },
                            tint = colorResource(id = if (isSearchTextEmpty) R.color.purple_search else R.color.purple_text)
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun TaskBody(taskViewModel: TaskViewModel, modifier: Modifier) {
    val provideDimension = provideDimensions()
    val taskUiState by taskViewModel.taskUiState.collectAsState()

    val taskItemList = taskUiState.toUiList()
    val position3rd = 2
    val position4th = 3
    val lastPosition = taskItemList.size - 1

    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(taskItemList) { index, item ->
            val itemModifier = when (index) {
                position3rd -> Modifier.padding(top = provideDimension.dp22)
                position4th -> Modifier.padding(top = provideDimension.dp24)
                lastPosition -> Modifier.padding(bottom = provideDimension.dp145)
                else -> Modifier
            }
            when (item) {
                is TaskHeaderTitle -> {
                    TitleItemView(
                        taskHeaderTitle = item,
                        modifier = if (index == position3rd) itemModifier else Modifier,
                        onClickSubTitle = {}
                    )
                }

                is TaskDate -> {
                    TaskDateItemView(
                        modifier = Modifier.padding(
                            top = provideDimension.dp7,
                            start = provideDimension.dp28,
                            end = provideDimension.dp28
                        ),
                        taskDate = item,
                        selectedDate = taskUiState.selectedTaskDateState,
                        onClickTaskDate = { dateIndex, selectedTaskDate ->
                            taskViewModel.updateSelectedDate(dateIndex, selectedTaskDate)
                        }
                    )
                }

                is TaskTimeline -> {
                    TaskTimelineItemView(
                        modifier = if (index == position4th || index == lastPosition) itemModifier else Modifier,
                        item = item
                    )
                }

                is EmptyTask -> EmptyTodayTask()
            }
        }
    }
}

private fun TaskState.toUiList() = arrayListOf<Any>().apply {
    add(
        TaskHeaderTitle(
            title = "Task",
            subTitle = currentMonthYear,
            isFirstTitle = true,
            isSubTitleContainIcon = true
        )
    )
    add(TaskDate(dateList))
    add(TaskHeaderTitle("Today", if (taskList.isNotEmpty()) currentHour else ""))
    if (taskList.isNotEmpty()) {
        addAll(timelineList)
    } else {
        add(EmptyTask())
    }
}