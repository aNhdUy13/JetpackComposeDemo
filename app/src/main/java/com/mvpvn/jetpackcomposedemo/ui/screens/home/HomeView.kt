package com.mvpvn.jetpackcomposedemo.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.screens.home.models.HomeHeaderTitle
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold


@Composable
fun TitleItemView(headerTitle: HomeHeaderTitle, modifier: Modifier, onClickSubTitle: () -> Unit) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val provideDimension = provideDimensions()
        val (textTitle, textSubtitle) = createRefs()

        Text(
            text = headerTitle.title,
            style = textBold,
            fontSize = R.dimen.sp24.toSp(),
            modifier = Modifier
                .constrainAs(textTitle) {
                    top.linkTo(
                        parent.top,
                        margin = if (headerTitle.isFirstTitle) provideDimension.dp145 else provideDimension.dp0
                    )
                    start.linkTo(parent.start, margin = provideDimension.dp24)
                },
            color = colorResource(id = R.color.home_text_greeting)
        )

        Text(
            text = headerTitle.subTitle,
            style = text,
            fontSize = R.dimen.sp14.toSp(),
            modifier = Modifier
                .clickable { onClickSubTitle() }
                .constrainAs(textSubtitle) {
                    top.linkTo(textTitle.top)
                    bottom.linkTo(textTitle.bottom)
                    end.linkTo(parent.end, margin = provideDimension.dp24)
                },
            color = colorResource(id = R.color.home_text_subtitle)
        )
    }
}

@Composable
fun MyTaskItemView(
    modifier: Modifier,
    onClickCompleteTask: () -> Unit,
    onClickPendingTask: () -> Unit,
    onClickCanceledTask: () -> Unit,
    onClickOngoingTask: () -> Unit
) {

    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val provideDimension = provideDimensions()
        val (dividerVerticalCenter) = createRefs()
        val (imageCompletedTask, imagePendingTask, imageCanceledTask, imageOnGoingTask) = createRefs()
        val (imageMac, imageTime, imageFolder, imageClose) = createRefs()
        val interactionSource = remember { MutableInteractionSource() }

        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(0.dp)
                .constrainAs(dividerVerticalCenter) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.img_completed_task),
            contentDescription = "",
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClickCompleteTask
                )
                .constrainAs(imageCompletedTask) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = provideDimension.dp24)
                    end.linkTo(dividerVerticalCenter.start, margin = provideDimension.dp8)
                    width = Dimension.fillToConstraints
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_imac),
            contentDescription = "",
            modifier = Modifier.constrainAs(imageMac) {
                top.linkTo(imageCompletedTask.top, margin = provideDimension.dp10)
                start.linkTo(imageCompletedTask.start, margin = provideDimension.dp14)
            }
        )

        TextMyTask("Completed", "86 Task", imageMac)

        Image(
            painter = painterResource(id = R.drawable.img_canceled_task),
            contentDescription = "",
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClickCanceledTask
                )
                .constrainAs(imageCanceledTask) {
                    top.linkTo(imageCompletedTask.bottom, margin = provideDimension.dp16)
                    start.linkTo(imageCompletedTask.start)
                    end.linkTo(imageCompletedTask.end)
                    width = Dimension.fillToConstraints
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "",
            modifier = Modifier.constrainAs(imageClose) {
                top.linkTo(imageCanceledTask.top, margin = provideDimension.dp15)
                start.linkTo(imageCanceledTask.start, margin = provideDimension.dp15)
                width = Dimension.fillToConstraints
            }
        )

        TextMyTask("Canceled", "15 Task", imageClose, R.color.white)

        Image(
            painter = painterResource(id = R.drawable.img_pending_task),
            contentDescription = "",
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClickPendingTask
                )
                .constrainAs(imagePendingTask) {
                    top.linkTo(parent.top)
                    start.linkTo(dividerVerticalCenter.end, margin = provideDimension.dp8)
                    end.linkTo(parent.end, margin = provideDimension.dp24)
                    width = Dimension.fillToConstraints
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_time),
            contentDescription = "",
            modifier = Modifier.constrainAs(imageTime) {
                top.linkTo(imagePendingTask.top, margin = provideDimension.dp15)
                start.linkTo(imagePendingTask.start, margin = provideDimension.dp15)
                width = Dimension.fillToConstraints
            }
        )

        TextMyTask("Pending", "15 Task", imageTime, R.color.white)

        Image(
            painter = painterResource(id = R.drawable.img_ongoing_task),
            contentDescription = "",
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClickOngoingTask
                )
                .constrainAs(imageOnGoingTask) {
                    top.linkTo(imagePendingTask.bottom, margin = provideDimension.dp16)
                    start.linkTo(imagePendingTask.start)
                    end.linkTo(imagePendingTask.end)
                    width = Dimension.fillToConstraints
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_folder),
            contentDescription = "",
            modifier = Modifier.constrainAs(imageFolder) {
                top.linkTo(imageOnGoingTask.top, margin = provideDimension.dp10)
                start.linkTo(imageOnGoingTask.start, margin = provideDimension.dp14)
                width = Dimension.fillToConstraints
            }
        )

        this.TextMyTask("On Going", "67 Task", imageFolder)
    }
}

@Composable
fun TodayTaskItemView(
    modifier: Modifier,
    task: Task,
    onClickTask: () -> Unit,
    onClickMore: () -> Unit
) {
    val provideDimension = provideDimensions()

    ConstraintLayout(
        modifier = modifier
            .clickable { onClickTask() }
            .fillMaxWidth()
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
fun ConstraintLayoutScope.TextMyTask(
    title: String,
    subTitle: String,
    topImage: ConstrainedLayoutReference,
    textColor: Int = R.color.home_text_greeting
) {
    val providerDimension = provideDimensions()
    val textMyTask = createRef()

    Column(modifier = Modifier.constrainAs(textMyTask) {
        top.linkTo(topImage.bottom)
        start.linkTo(topImage.start)
    }) {
        Text(
            text = title,
            modifier = Modifier.padding(
                top = providerDimension.dp8
            ),
            color = colorResource(id = textColor),
            fontSize = R.dimen.sp16.toSp(),
            style = textBold
        )

        Text(
            text = subTitle,
            modifier = Modifier.padding(
                top = providerDimension.dp5
            ),
            color = colorResource(id = textColor),
            fontSize = R.dimen.sp14.toSp(),
            style = text
        )
    }
}