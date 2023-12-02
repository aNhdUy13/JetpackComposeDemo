package com.mvpvn.jetpackcomposedemo.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.Dimensions
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold

@Composable
fun HomeScreen() {
    val provideDimension = provideDimensions()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (homeHeader) = createRefs()

        HomeBody(
            modifier = Modifier
                .padding(horizontal = provideDimension.dp24)
                .fillMaxSize(),
            provideDimension = provideDimension
        )

        HomeHeader(
            modifier = Modifier
                .constrainAs(homeHeader) {
                    top.linkTo(parent.top)
                },
            provideDimension = provideDimension
        )
    }
}

@Composable
fun HomeHeader(modifier: Modifier, provideDimension: Dimensions) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.home_header))

    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(
                    start = provideDimension.dp24,
                    end = provideDimension.dp24,
                    bottom = provideDimension.dp10
                )
                .statusBarsPadding()
                .fillMaxWidth()
        ) {
            val (textGreetingTitle, textGreetingContent) = createRefs()
            val (imagePerson) = createRefs()

            Text(
                text = stringResource(id = R.string.home_greeting, "Duy Anh"),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = provideDimension.dp24)
                    .constrainAs(textGreetingTitle) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(imagePerson.start)
                    },
                color = colorResource(id = R.color.home_text_greeting),
                fontSize = R.dimen.sp28.toSp(),
                textAlign = TextAlign.Left,
                maxLines = 1,
                style = textBold
            )
            Text(
                text = stringResource(id = R.string.home_greeting_content),
                modifier = Modifier.constrainAs(textGreetingContent) {
                    top.linkTo(textGreetingTitle.bottom)
                    start.linkTo(parent.start)
                },
                color = colorResource(id = R.color.home_text_greeting_content),
                fontSize = R.dimen.sp14.toSp(),
                style = text
            )
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = "",
                modifier = Modifier
                    .shadow(
                        elevation = provideDimension.dp5,
                        shape = RoundedCornerShape(provideDimension.dp14)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(provideDimension.dp14)
                    )
                    .size(provideDimension.dp50)
                    .padding(provideDimension.dp10)
                    .constrainAs(imagePerson) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
            )
        }
    }
}

@Composable
fun HomeBody(modifier: Modifier, provideDimension: Dimensions) {
    LazyColumn(
        modifier = modifier
    ) {

    }
}

@Composable
fun TitleItemView(title: String, subTitle: String, onClickSubTitle: () -> Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (textTitle, textSubtitle) = createRefs()

        Text(
            text = title,
            style = textBold,
            fontSize = R.dimen.sp24.toSp(),
            modifier = Modifier.constrainAs(textTitle) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            color = colorResource(id = R.color.home_text_greeting)
        )

        Text(
            text = subTitle,
            style = text,
            fontSize = R.dimen.sp12.toSp(),
            modifier = Modifier
                .clickable { onClickSubTitle() }
                .constrainAs(textSubtitle) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            color = colorResource(id = R.color.home_text_subtitle)
        )
    }
}

@Composable
fun MyTaskItemView(text: String) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val provideDimension = provideDimensions()
        val (imageCompletedTask, imagePendingTask, imageCanceledTask, imageOnGoingTask) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.img_completed_task),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(imageCompletedTask) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.img_pending_task),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(imagePendingTask) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.img_canceled_task),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(imageCanceledTask) {
                    top.linkTo(imageCompletedTask.bottom)
                    start.linkTo(imageCompletedTask.start)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.img_ongoing_task),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(imageOnGoingTask) {
                    top.linkTo(imagePendingTask.bottom)
                    end.linkTo(imagePendingTask.end)
                }
        )
    }
}

@Composable
fun TodayTaskItemView(text: String) {
    val provideDimension = provideDimensions()

    ConstraintLayout(
        modifier = Modifier
            .padding(provideDimension.dp15)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(provideDimension.dp14)
            )
    ) {
        val (divider) = createRefs()

        Divider(
            modifier = Modifier
                .width(provideDimension.dp2)
                .constrainAs(divider) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )
    }
}