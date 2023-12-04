package com.mvpvn.jetpackcomposedemo.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
import com.mvpvn.jetpackcomposedemo.ui.screens.home.models.HomeHeaderTitle
import com.mvpvn.jetpackcomposedemo.ui.screens.home.models.MyTask
import com.mvpvn.jetpackcomposedemo.ui.screens.task.models.Task
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold

@Composable
fun HomeScreen() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (homeHeader) = createRefs()

        HomeBody(
            modifier = Modifier.fillMaxSize()
        )

        HomeHeader(
            modifier = Modifier
                .constrainAs(homeHeader) {
                    top.linkTo(parent.top)
                },
            provideDimension = provideDimensions()
        )
    }
}

@Composable
fun HomeHeader(modifier: Modifier, provideDimension: Dimensions) {
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
                .padding(
                    start = provideDimension.dp24,
                    end = provideDimension.dp24,
                    top = provideDimension.dp15,
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
fun HomeBody(modifier: Modifier) {
    val provideDimension = provideDimensions()
    val homeItemList = homeUiList()

    val secondItemPosition = 1
    val thirdItemPosition = 2
    val fourthItemPosition = 3

    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(homeItemList) { index, item ->
            val itemModifier = when (index) {
                secondItemPosition -> Modifier.padding(top = provideDimension.dp14)
                thirdItemPosition -> Modifier.padding(top = provideDimension.dp22)
                fourthItemPosition -> Modifier.padding(
                    top = provideDimension.dp20,
                    start = provideDimension.dp24,
                    end = provideDimension.dp24
                )

                homeItemList.size - 1 -> Modifier.padding(
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
                is HomeHeaderTitle -> {
                    TitleItemView(
                        headerTitle = item,
                        modifier = if (index == thirdItemPosition) itemModifier else Modifier,
                        onClickSubTitle = {}
                    )
                }

                is MyTask -> {
                    MyTaskItemView(
                        modifier = if (index == secondItemPosition) itemModifier else Modifier,
                        onClickCompleteTask = {},
                        onClickPendingTask = {},
                        onClickCanceledTask = {},
                        onClickOngoingTask = {}
                    )
                }

                is Task -> {
                    TodayTaskItemView(
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

private fun homeUiList() = arrayListOf<Any>().apply {
    add(HomeHeaderTitle("My Task", "", true))
    add(MyTask(""))
    add(HomeHeaderTitle("Today Task", "View all"))

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