package com.mvpvn.jetpackcomposedemo.ui.screens.task

import androidx.compose.foundation.background
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
import com.mvpvn.jetpackcomposedemo.ui.screens.home.MyTaskItemView
import com.mvpvn.jetpackcomposedemo.ui.screens.home.TitleItemView
import com.mvpvn.jetpackcomposedemo.ui.screens.home.TodayTaskItemView
import com.mvpvn.jetpackcomposedemo.ui.screens.home.models.HomeHeaderTitle
import com.mvpvn.jetpackcomposedemo.ui.screens.home.models.MyTask
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskHeaderTitle
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold

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

                        Icon(
                            painter = painterResource(id = if (textSearchState.value.isEmpty()) R.drawable.ic_clear_search_disable else R.drawable.ic_clear_search_enable),
                            contentDescription = "Delete Icon",
                            tint = colorResource(id = if (textSearchState.value.isEmpty()) R.color.purple_search else R.color.purple_text)
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
    val taskItemList = taskUiList()

    val secondItemPosition = 1
    val thirdItemPosition = 2
    val fourthItemPosition = 3

    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(taskItemList) { index, item ->
            val itemModifier = when (index) {
                secondItemPosition -> Modifier.padding(top = provideDimension.dp14)
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

                }

                is Task -> {

                }
            }
        }
    }
}

private fun taskUiList() = arrayListOf<Any>().apply {
    add(TaskHeaderTitle("Task", "", true))
    add(MyTask(""))
    add(TaskHeaderTitle("Today Task", "View all"))

    val taskList = mutableListOf<Any>()
    for (i in 1..10) {
        taskList.add(
            Task(
                title = "Header $i",
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