package com.mvpvn.jetpackcomposedemo.ui.screens.task.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.screens.base.BackActionBar
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold

@Composable
fun AddTaskScreen() {
    val provideDimensions = provideDimensions()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val textTitleState = remember { mutableStateOf("Plan for a month") }
        val textDateState = remember { mutableStateOf("4 August 2021") }
        val textStartTimeState = remember { mutableStateOf("07:00  AM") }
        val textEndTimeState = remember { mutableStateOf("07:30  AM") }
        val textDescriptionState = remember { mutableStateOf("Creating this month's work plan") }
        val textTaskTypeState = remember { mutableIntStateOf(-1) }

        BackActionBar(stringResource(id = R.string.add_task))
        AddTaskTitle(
            title = stringResource(id = R.string.title),
            modifier = Modifier.padding(
                top = provideDimensions.dp35,
                start = provideDimensions.dp36,
                end = provideDimensions.dp36
            )
        )
        AddTaskSimpleTextField(
            textState = textTitleState,
            modifier = Modifier.padding(horizontal = provideDimensions.dp36)
        )
        AddTaskTitle(
            title = stringResource(id = R.string.date),
            modifier = Modifier.padding(
                top = provideDimensions.dp20,
                start = provideDimensions.dp36,
                end = provideDimensions.dp36
            )
        )
        AddTaskSimpleTextField(
            textState = textDateState,
            modifier = Modifier.padding(horizontal = provideDimensions.dp36),
            isReadOnly = true,
            endIcon = R.drawable.ic_calendar
        )
        AddTaskTitle(
            title = stringResource(id = R.string.time),
            modifier = Modifier.padding(
                top = provideDimensions.dp20,
                start = provideDimensions.dp36,
                end = provideDimensions.dp36
            )
        )
        Row(
            modifier = Modifier.padding(
                start = provideDimensions.dp36,
                end = provideDimensions.dp36
            )
        ) {
            AddTaskSimpleTextField(
                textState = textStartTimeState,
                isTaskToCenter = true,
                modifier = Modifier.weight(1f),
                isReadOnly = true
            )
            Spacer(modifier = Modifier.width(provideDimensions.dp15))
            AddTaskSimpleTextField(
                textState = textEndTimeState,
                isTaskToCenter = true,
                modifier = Modifier.weight(1f),
                isReadOnly = true
            )
        }
        AddTaskTitle(
            title = stringResource(id = R.string.description),
            modifier = Modifier.padding(
                top = provideDimensions.dp24,
                start = provideDimensions.dp36,
                end = provideDimensions.dp36
            )
        )
        AddTaskSimpleTextField(
            textState = textDescriptionState,
            textHint = "",
            modifier = Modifier.padding(horizontal = provideDimensions.dp36)
        )
        AddTaskTitle(
            title = stringResource(id = R.string.type),
            modifier = Modifier.padding(
                top = provideDimensions.dp20,
                start = provideDimensions.dp36,
                end = provideDimensions.dp36
            )
        )
    }
}

@Composable
fun AddTaskTag() {

}

@Composable
fun AddTaskSimpleTextField(
    textState: MutableState<String>,
    textHint: String = "",
    isTaskToCenter: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    isReadOnly: Boolean = false,
    isSingleLine: Boolean = true,
    startIcon: Int = -1,
    endIcon: Int = -1,
    iconColor: Color = colorResource(id = R.color.color_purple_white)
) {
    val provideDimensions = provideDimensions()

    Column(modifier = modifier) {
        BasicTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier.fillMaxWidth(),
            readOnly = isReadOnly,
            singleLine = isSingleLine,
            textStyle = textBold.copy(
                color = colorResource(id = R.color.title_back_action_bar),
                fontSize = R.dimen.sp15.toSp()
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = provideDimensions.dp15,
                            bottom = provideDimensions.dp15,
                            start = if (startIcon != -1) provideDimensions.dp3 else provideDimensions.dp0,
                            end = if (endIcon != -1) provideDimensions.dp3 else provideDimensions.dp0
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (startIcon != -1) {
                        Icon(
                            painter = painterResource(id = startIcon),
                            contentDescription = "Start Icon",
                            tint = iconColor
                        )
                        Spacer(modifier = Modifier.width(provideDimensions.dp15))
                    }

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        innerTextField()
                        if (textState.value.isEmpty()) {
                            Text(
                                text = textHint,
                                modifier = if (isTaskToCenter) Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterStart) else Modifier,
                                style = text.copy(
                                    color = colorResource(id = R.color.text_hint),
                                    fontSize = R.dimen.sp16.toSp()
                                )
                            )
                        }
                    }

                    if (endIcon != -1) {
                        Icon(
                            painter = painterResource(id = endIcon),
                            contentDescription = "End Icon",
                            tint = iconColor
                        )
                    }
                }
            }
        )

        Divider(
            color = colorResource(id = R.color.divider_task),
            thickness = dimensionResource(id = R.dimen.dp1)
        )
    }

}

@Composable
fun AddTaskTitle(title: String, modifier: Modifier) {
    Text(
        text = title,
        modifier = modifier,
        color = colorResource(id = R.color.text_hint_2),
        style = text.copy(
            fontSize = R.dimen.sp14.toSp()
        )
    )
}