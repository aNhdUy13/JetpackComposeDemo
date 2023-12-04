package com.mvpvn.jetpackcomposedemo.ui.screens.task.pending

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.models.PendingTaskTimeline
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.models.PendingTaskHeaderTitle
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.models.PendingTaskSearch
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold

@Composable
fun PendingTaskHeaderItemView(item: PendingTaskHeaderTitle, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_calendar_pending_task),
            contentDescription = "",
            modifier = Modifier.padding(end = provideDimensions().dp10)
        )
        Text(
            text = item.title,
            fontSize = R.dimen.sp22.toSp(),
            color = colorResource(id = R.color.black),
            style = textBold.copy(
                fontWeight = FontWeight(400)
            )
        )
    }
}

@Composable
fun PendingTaskSearchItemView(item: PendingTaskSearch, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = provideDimensions().dp145)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = provideDimensions().dp24,
                    end = provideDimensions().dp15,
                )
                .background(
                    color = colorResource(id = R.color.search_bg),
                    shape = RoundedCornerShape(provideDimensions().dp15)
                )
                .weight(1f)
        ) {
            val textSearchState = remember { mutableStateOf("") }

            BasicTextField(
                value = textSearchState.value,
                onValueChange = { textSearchState.value = it },
                singleLine = true,
                textStyle = textBold.copy(
                    color = colorResource(id = R.color.black),
                    fontSize = R.dimen.sp14.toSp()
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .padding(
                                horizontal = provideDimensions().dp20,
                                vertical = provideDimensions().dp15
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search Icon",
                            tint = colorResource(id = R.color.purple_search)
                        )
                        Spacer(modifier = Modifier.width(provideDimensions().dp15))
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

        Image(
            painter = painterResource(id = R.drawable.ic_search_util),
            contentDescription = "",
            modifier = Modifier.padding(end = provideDimensions().dp24)
        )
    }
}

@Composable
fun PendingTaskTimelineItemView(item: PendingTaskTimeline, modifier: Modifier) {
    val provideDimensions = provideDimensions()

    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (time, taskList) = createRefs()

        Text(
            text = item.time,
            fontSize = R.dimen.sp14.toSp(),
            modifier = Modifier
                .padding(
                    start = provideDimensions().dp28,
                    end = provideDimensions().dp28
                )
                .constrainAs(time) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            color = colorResource(id = R.color.hint_task),
            style = text
        )

        LazyRow(
            modifier = Modifier
                .height(provideDimensions().dp100)
                .constrainAs(taskList) {
                    top.linkTo(time.bottom, margin = provideDimensions.dp16)
                    start.linkTo(parent.start, margin = provideDimensions.dp28)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            itemsIndexed(item.taskList) { index: Int, task: Task ->
                TaskItemView(
                    modifier = if (index == item.taskList.size - 1) Modifier.padding(end = provideDimensions.dp30) else Modifier.padding(
                        end = provideDimensions.dp10
                    ),
                    task = task,
                    onClickTask = {},
                    onClickMore = {}
                )
            }
        }
    }

}

@Composable
private fun TaskItemView(
    modifier: Modifier,
    task: Task,
    onClickTask: () -> Unit,
    onClickMore: () -> Unit
) {
    val provideDimension = provideDimensions()

    ConstraintLayout(
        modifier = modifier
            .clickable { onClickTask() }
            .width(provideDimension.dp190)
            .background(
                color = colorResource(id = R.color.task_background),
                shape = RoundedCornerShape(provideDimension.dp15)
            )
            .padding(vertical = provideDimension.dp15, horizontal = provideDimension.dp15)

    ) {
        val (divider, taskTitle, taskTime, taskCategoryList, imageMore) = createRefs()

        Divider(
            modifier = Modifier
                .size(provideDimension.dp2, provideDimension.dp35)
                .constrainAs(divider) {
                    top.linkTo(parent.top, margin = provideDimension.dp5)
                    start.linkTo(parent.start)
                },
            color = colorResource(id = task.color)
        )

        Text(
            text = task.title,
            style = textBold,
            fontSize = R.dimen.sp16.toSp(),
            modifier = Modifier
                .constrainAs(taskTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(divider.end, margin = provideDimension.dp10)
                },
            color = colorResource(id = R.color.splash_greeting)
        )

        Text(
            text = "${task.startTime} - ${task.endTime}",
            style = text,
            fontSize = R.dimen.sp14.toSp(),
            modifier = Modifier
                .constrainAs(taskTime) {
                    top.linkTo(taskTitle.bottom)
                    start.linkTo(taskTitle.start)
                },
            color = colorResource(id = R.color.text_time)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = "",
            modifier = Modifier
                .clickable { onClickMore() }
                .constrainAs(imageMore) {
                    top.linkTo(divider.top)
                    end.linkTo(parent.end)
                }
        )

        Row(
            modifier =
            Modifier
                .constrainAs(taskCategoryList) {
                    top.linkTo(divider.bottom, margin = provideDimension.dp22)
                    start.linkTo(taskTime.start)
                }
        ) {
            Text(
                text = "Urgent",
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.divider),
                        shape = RoundedCornerShape(provideDimension.dp2)
                    )
                    .padding(
                        vertical = provideDimension.dp2,
                        horizontal = provideDimension.dp7
                    ),
                fontSize = R.dimen.sp10.toSp(),
                color = colorResource(id = R.color.divider_purple),
                style = text
            )

            Spacer(modifier = Modifier.padding(provideDimension.dp4))

            Text(
                text = "Home",
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.divider),
                        shape = RoundedCornerShape(provideDimension.dp2)
                    )
                    .padding(
                        vertical = provideDimension.dp2,
                        horizontal = provideDimension.dp7
                    ),
                fontSize = R.dimen.sp10.toSp(),
                color = colorResource(id = R.color.divider_purple),
                style = text
            )
        }
    }
}