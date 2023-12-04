package com.mvpvn.jetpackcomposedemo.ui.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskDate
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskHeaderTitle
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.TaskTimeline
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold
import com.mvpvn.jetpackcomposedemo.utilities.TimeFormat
import com.mvpvn.jetpackcomposedemo.utilities.abbreviateDayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun TitleItemView(
    taskHeaderTitle: TaskHeaderTitle,
    modifier: Modifier,
    onClickSubTitle: () -> Unit
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val provideDimension = provideDimensions()
        val (textTitle, textSubtitle, imageSubTitle) = createRefs()

        Text(
            text = taskHeaderTitle.title,
            style = textBold,
            fontSize = R.dimen.sp24.toSp(),
            modifier = Modifier
                .constrainAs(textTitle) {
                    top.linkTo(
                        parent.top,
                        margin = if (taskHeaderTitle.isFirstTitle) provideDimension.dp145 else provideDimension.dp0
                    )
                    start.linkTo(parent.start, margin = provideDimension.dp24)
                },
            color = colorResource(id = R.color.home_text_greeting)
        )

        if (taskHeaderTitle.isSubTitleContainIcon) {
            Image(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(imageSubTitle) {
                        top.linkTo(textSubtitle.top)
                        bottom.linkTo(textSubtitle.bottom)
                        end.linkTo(textSubtitle.start, margin = provideDimension.dp3)
                    }
            )
        }

        Text(
            text = taskHeaderTitle.subTitle,
            style = text,
            fontSize = if (!taskHeaderTitle.isSubTitleContainIcon) R.dimen.sp14.toSp() else R.dimen.sp12.toSp(),
            modifier = Modifier
                .clickable { onClickSubTitle() }
                .constrainAs(textSubtitle) {
                    top.linkTo(textTitle.top)
                    bottom.linkTo(textTitle.bottom)
                    end.linkTo(parent.end, margin = provideDimension.dp24)
                },
            color = colorResource(id = if (!taskHeaderTitle.isSubTitleContainIcon) R.color.black else R.color.hint_task)
        )
    }
}

@Composable
fun TaskDateItemView(
    modifier: Modifier,
    taskDate: TaskDate,
    selectedDate: LocalDate,
    onClickTaskDate: (Int, LocalDate) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        taskDate.dateTimeList.forEachIndexed { index, time ->
            TaskDateChildItemView(
                date = time,
                isSelected = selectedDate == time,
                interactionSource = interactionSource
            ) { selectedLocalDate ->
                onClickTaskDate(index, selectedLocalDate)
            }
        }
    }
}

@Composable
fun TaskTimelineItemView(modifier: Modifier, item: TaskTimeline) {
    val provideDimensions = provideDimensions()

    ConstraintLayout(modifier = modifier) {
        val (divider, time, textEmptyTask, taskList) = createRefs()

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = provideDimensions.dp24)
                .constrainAs(divider) { top.linkTo(parent.top) },
            color = colorResource(id = R.color.divider),
            thickness = dimensionResource(id = R.dimen.dp1)
        )

        Text(
            text = item.time,
            fontSize = R.dimen.sp14.toSp(),
            modifier = Modifier
                .constrainAs(time) {
                    start.linkTo(divider.start, margin = provideDimensions.dp30)
                    if (item.taskList.isEmpty()) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    } else {
                        top.linkTo(divider.bottom, margin = provideDimensions.dp24)
                    }
                },
            color = colorResource(id = R.color.splash_greeting),
            style = text
        )

        if (item.taskList.isEmpty()) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.text_time),
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        )
                    ) {
                        append(stringResource(id = R.string.dont_have_schedule_or))
                    }
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.splash_greeting),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(" ${stringResource(id = R.string.plush_add)}")
                    }
                },
                modifier = Modifier
                    .constrainAs(textEmptyTask) {
                        top.linkTo(parent.top, margin = provideDimensions.dp16)
                        bottom.linkTo(parent.bottom, margin = provideDimensions.dp16)
                        start.linkTo(time.end, margin = provideDimensions.dp21)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                fontSize = R.dimen.sp14.toSp()
            )
        } else {
            LazyRow(
                modifier = Modifier
                    .height(provideDimensions.dp100)
                    .constrainAs(taskList) {
                        top.linkTo(parent.top, margin = provideDimensions.dp16)
                        bottom.linkTo(parent.bottom, margin = provideDimensions.dp16)
                        start.linkTo(time.end, margin = provideDimensions.dp21)
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
}

@Composable
fun EmptyTodayTask() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = provideDimensions().dp50),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_empty_today_task),
            contentDescription = "",
        )

        Text(
            text = stringResource(id = R.string.empty_today_task),
            modifier = Modifier.padding(vertical = provideDimensions().dp32),
            fontSize = R.dimen.sp16.toSp(),
            color = colorResource(id = R.color.home_text_greeting_content),
            textAlign = TextAlign.Center,
            style = text.copy(
                lineHeight = 24.sp
            )
        )
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


@Composable
private fun TaskDateChildItemView(
    date: LocalDate,
    isSelected: Boolean,
    interactionSource: MutableInteractionSource,
    onClickTaskDate: (LocalDate) -> Unit
) {
    val context = LocalContext.current
    val formattedDate = date.format(DateTimeFormatter.ofPattern(TimeFormat.EEEE_D))
    val dayOfWeek = formattedDate.split("/")[0]
    val dayNumber = formattedDate.split("/")[1]

    Column(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClickTaskDate(date)
            }
            .size(
                width = provideDimensions().dp45,
                height = provideDimensions().dp71
            )
            .background(
                color = colorResource(id = if (isSelected) R.color.button_color else R.color.white),
                shape = RoundedCornerShape(provideDimensions().dp12)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = abbreviateDayOfWeek(context, dayOfWeek),
            fontSize = R.dimen.sp16.toSp(),
            color = colorResource(id = if (isSelected) R.color.white else R.color.title_back_action_bar),
            style = textBold
        )
        Spacer(modifier = Modifier.height(provideDimensions().dp5))
        Text(
            text = dayNumber,
            fontSize = R.dimen.sp14.toSp(),
            color = colorResource(id = if (isSelected) R.color.white else R.color.title_back_action_bar),
            style = text
        )
    }
}
