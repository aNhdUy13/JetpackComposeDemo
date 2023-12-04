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
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.Dimensions
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskDate
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskHeaderTitle
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskTimeline
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold
import com.mvpvn.jetpackcomposedemo.utilities.TimeFormat
import com.mvpvn.jetpackcomposedemo.utilities.getCurrentDate
import org.threeten.bp.LocalDate

@Composable
fun TaskScreen() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (taskHeader) = createRefs()

        TaskBody(
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
fun TaskBody(modifier: Modifier) {
    val provideDimension = provideDimensions()
    val selectedTaskDateState = remember { mutableStateOf(LocalDate.now()) }

    val currentDate = LocalDate.now()
    val currentMonthYear = getCurrentDate(TimeFormat.MMMM_YYYY)
    val dividedHour = getCurrentDate().split(":")
    val currentHour = "${dividedHour[0]} h ${dividedHour[1]} min"
    val dateList = (0 until 7).map { currentDate.plusDays(it.toLong()) }
    val timelineList = getRangeOfHour().map {
        TaskTimeline(it)
    }

    val taskItemList = taskUiList(dateList, timelineList, currentMonthYear, currentHour)

    val secondItemPosition = 1
    val thirdItemPosition = 2
    val fourthItemPosition = 3

    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(taskItemList) { index, item ->
            val itemModifier = when (index) {
                secondItemPosition -> Modifier.padding(
                    top = provideDimension.dp14,
                    start = provideDimension.dp32,
                    end = provideDimension.dp32
                )

                thirdItemPosition -> Modifier.padding(top = provideDimension.dp22)
                fourthItemPosition -> Modifier.padding(
                    top = provideDimension.dp20,
                    start = provideDimension.dp24,
                    end = provideDimension.dp24
                )

                taskItemList.size - 1 -> Modifier.padding(
                    top = provideDimension.dp20,
                    bottom = provideDimension.dp145,
                    start = provideDimension.dp24,
                    end = provideDimension.dp24
                )

                else -> Modifier.padding(
                    top = provideDimension.dp10,
                    start = provideDimension.dp24,
                    end = provideDimension.dp24
                )
            }
            when (item) {
                is TaskHeaderTitle -> {
                    TitleItemView(
                        taskHeaderTitle = item,
                        modifier = if (index == thirdItemPosition) itemModifier else Modifier,
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
                        selectedDate = selectedTaskDateState.value,
                        onClickTaskDate = { selectedTaskDate ->
                            selectedTaskDateState.value = selectedTaskDate
                        }
                    )
                }

                is TaskTimeline -> {
                    TaskTimelineItemView(item)
                }
                is Task -> {
                    TaskItemView(
                        modifier = if (index == fourthItemPosition || index > fourthItemPosition) itemModifier else Modifier,
                        task = item,
                        onClickTask = {

                        },
                        onClickMore = {

                        }
                    )
                }
            }

        }
    }
}

private fun getRangeOfHour(): List<String> {
    return (0..23).map { hour ->
        String.format("%02d:00", hour)
    }
}

private fun taskUiList(
    dateList: List<LocalDate>,
    timeList: List<TaskTimeline>,
    currentMonthYear: String,
    currentHour: String
) = arrayListOf<Any>().apply {
    add(
        TaskHeaderTitle(
            title = "Task",
            subTitle = currentMonthYear,
            isFirstTitle = true,
            isSubTitleContainIcon = true
        )
    )
    add(TaskDate(dateList))
    add(TaskHeaderTitle("Today", currentHour))
    addAll(timeList)

    val taskList = mutableListOf<Any>()
    for (i in 1..10) {
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