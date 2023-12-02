package com.mvpvn.jetpackcomposedemo.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold

data class HomeHeaderTitle(
    val title: String = "",
    val subTitle: String = "",
    val isFirstTitle: Boolean = false
)

data class MyTask(val title: String = "")

data class HomeTask(
    val title: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val categories: List<String> = emptyList()
)


@Composable
fun TitleItemView(headerTitle: HomeHeaderTitle, modifier: Modifier, onClickSubTitle: () -> Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val provideDimension = provideDimensions()
        val (textTitle, textSubtitle) = createRefs()

        Text(
            text = headerTitle.title,
            style = textBold,
            fontSize = R.dimen.sp26.toSp(),
            modifier = modifier.constrainAs(textTitle) {
                top.linkTo(
                    parent.top,
                    margin = if (headerTitle.isFirstTitle) provideDimension.dp145 else provideDimension.dp0
                )
                start.linkTo(parent.start)
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
                    end.linkTo(parent.end)
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
        val (imageCompletedTask, imagePendingTask, imageCanceledTask, imageOnGoingTask) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.img_completed_task),
            contentDescription = "",
            modifier = Modifier
                .clickable { onClickCompleteTask() }
                .constrainAs(imageCompletedTask) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.img_pending_task),
            contentDescription = "",
            modifier = Modifier
                .clickable { onClickPendingTask() }
                .constrainAs(imagePendingTask) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.img_canceled_task),
            contentDescription = "",
            modifier = Modifier
                .clickable { onClickCanceledTask() }
                .constrainAs(imageCanceledTask) {
                    top.linkTo(imageCompletedTask.bottom)
                    start.linkTo(imageCompletedTask.start)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.img_ongoing_task),
            contentDescription = "",
            modifier = Modifier
                .clickable { onClickOngoingTask() }
                .constrainAs(imageOnGoingTask) {
                    top.linkTo(imagePendingTask.bottom)
                    end.linkTo(imagePendingTask.end)
                }
        )
    }
}

@Composable
fun TodayTaskItemView(
    modifier: Modifier,
    homeTask: HomeTask,
    onClickTask: () -> Unit,
    onClickMore: () -> Unit
) {
    val provideDimension = provideDimensions()

    ConstraintLayout(
        modifier = modifier
            .clickable { onClickTask() }
            .fillMaxWidth()
            .shadow(
                elevation = provideDimension.dp14,
                shape = RoundedCornerShape(provideDimension.dp14)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(provideDimension.dp14)
            )
            .padding(vertical = provideDimension.dp15, horizontal = provideDimension.dp22)

    ) {
        val (divider, taskTitle, taskTime, taskCategoryList, imageMore) = createRefs()

        Divider(
            modifier = Modifier
                .background(colorResource(id = R.color.divider_purple))
                .size(provideDimension.dp2, provideDimension.dp35)
                .constrainAs(divider) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = homeTask.title,
            style = textBold,
            fontSize = R.dimen.sp16.toSp(),
            modifier = Modifier
                .constrainAs(taskTitle) {
                    top.linkTo(divider.top)
                    start.linkTo(divider.end, margin = provideDimension.dp10)
                },
            color = colorResource(id = R.color.splash_greeting)
        )

        Text(
            text = "${homeTask.startTime} = ${homeTask.endTime}",
            style = textBold,
            fontSize = R.dimen.sp14.toSp(),
            modifier = Modifier
                .constrainAs(taskTime) {
                    bottom.linkTo(divider.bottom)
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
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )

        Box(modifier = Modifier.constrainAs(taskCategoryList) {
            top.linkTo(divider.bottom, margin = provideDimension.dp22)
            start.linkTo(taskTime.start)
        }) {

        }
    }
}