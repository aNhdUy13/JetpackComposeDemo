package com.mvpvn.jetpackcomposedemo.ui.screens.task.add

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.screens.base.BackActionBar
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold
import com.mvpvn.jetpackcomposedemo.utilities.TimeFormat
import com.mvpvn.jetpackcomposedemo.utilities.getCurrentDate
import com.mvpvn.jetpackcomposedemo.utilities.getCurrentTimeLater
import com.mvpvn.jetpackcomposedemo.utilities.showDatePickerDialog
import com.mvpvn.jetpackcomposedemo.utilities.showTimePickerDialog

@Composable
fun AddTaskScreen() {
    val provideDimensions = provideDimensions()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        val context = LocalContext.current
        val currentDate = getCurrentDate(TimeFormat.D_MMMM_YYYY)
        val currentHour = getCurrentDate(TimeFormat.HH_MM_a)
        val currentHourLater = getCurrentTimeLater()

        val textTitleState = remember { mutableStateOf("") }
        val textDateState = remember { mutableStateOf(currentDate) }
        val textStartTimeState = remember { mutableStateOf(currentHour) }
        val textEndTimeState = remember { mutableStateOf(currentHourLater) }
        val textDescriptionState = remember { mutableStateOf("") }
        val textTaskTypeState = remember { mutableIntStateOf(-1) }

        BackActionBar(stringResource(id = R.string.add_task))
        AddTaskTitle(
            title = stringResource(id = R.string.title),
            modifier = Modifier.padding(
                top = provideDimensions.dp28,
                start = provideDimensions.dp36,
                end = provideDimensions.dp36
            )
        )
        AddTaskSimpleTextField(
            textState = textTitleState,
            textHint = stringResource(id = R.string.hint_title_add_task),
            modifier = Modifier.padding(horizontal = provideDimensions.dp36)
        ) {}
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
        ) {
            showDatePickerDialog(context) { textDateState.value = it }
        }
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
            ) {
                showTimePickerDialog(context) { hour, min ->
//                    textStartTimeState.value = it
                }
            }
            Spacer(modifier = Modifier.width(provideDimensions.dp15))
            AddTaskSimpleTextField(
                textState = textEndTimeState,
                isTaskToCenter = true,
                modifier = Modifier.weight(1f),
                isReadOnly = true
            ) {
                showTimePickerDialog(context) { hour, min ->
//                    textStartTimeState.value = it
                }
            }
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
            textHint = stringResource(id = R.string.hint_description_add_task),
            modifier = Modifier.padding(horizontal = provideDimensions.dp36)
        ) {}
        AddTaskTitle(
            title = stringResource(id = R.string.type),
            modifier = Modifier.padding(
                top = provideDimensions.dp20,
                start = provideDimensions.dp36,
                end = provideDimensions.dp36
            )
        )
        AddTaskType(
            modifier = Modifier.padding(
                horizontal = provideDimensions.dp22
            )
        )
        AddTaskTitle(
            title = stringResource(id = R.string.tags),
            modifier = Modifier.padding(
                top = provideDimensions.dp20,
                start = provideDimensions.dp36,
                end = provideDimensions.dp36
            )
        )
        AddTaskTag(
            modifier = Modifier.padding(
                start = provideDimensions.dp24,
                end = provideDimensions.dp24,
                top = provideDimensions.dp16

            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = provideDimensions.dp24, vertical = provideDimensions.dp38)
        ) {
            Text(
                text = stringResource(id = R.string.create),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.button_color),
                        shape = RoundedCornerShape(provideDimensions.dp14)
                    )
                    .padding(vertical = provideDimensions.dp14),
                color = colorResource(id = R.color.white),
                fontSize = R.dimen.sp18.toSp(),
                textAlign = TextAlign.Center,
                style = textBold
            )
        }
    }
}

@Composable
fun AddTaskTag(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TagItem(
                stringResource(id = R.string.office),
                colorResource(id = R.color.purple_text),
                colorResource(id = R.color.purple_white)
            )
            TagItem(
                stringResource(id = R.string.home),
                colorResource(id = R.color.orange),
                colorResource(id = R.color.yellow_white)
            )
            TagItem(
                stringResource(id = R.string.urgent),
                colorResource(id = R.color.pink_white),
                colorResource(id = R.color.red_white_white)
            )
            TagItem(
                stringResource(id = R.string.work),
                colorResource(id = R.color.blue_text),
                colorResource(id = R.color.blue_white)
            )
        }

        Text(
            text = stringResource(id = R.string.add_new_tag),
            modifier = Modifier
                .padding(top = provideDimensions().dp16)
                .align(Alignment.CenterHorizontally),
            color = colorResource(id = R.color.color_purple_white),
            fontSize = R.dimen.sp12.toSp(),
            style = text
        )
    }

}

@Composable
fun TagItem(title: String, titleColor: Color, backgroundColor: Color) {
    val provideDimensions = provideDimensions()
    Text(
        text = title,
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(provideDimensions.dp22)
            )
            .padding(horizontal = provideDimensions.dp20, vertical = provideDimensions.dp6),
        color = titleColor,
        fontSize = R.dimen.sp14.toSp(),
        style = text
    )
}

@Composable
fun AddTaskType(modifier: Modifier) {
    val (checkedStatePersonal, onCheckedChangePersonal) = remember { mutableStateOf(false) }
    val (checkedStatePrivate, onCheckedChangePrivate) = remember { mutableStateOf(false) }
    val (checkedStateSecret, onCheckedChangeSecret) = remember { mutableStateOf(false) }

    val checkedIcon = painterResource(id = R.drawable.ic_checked_type)
    val uncheckedIcon = painterResource(id = R.drawable.ic_unchecked_type)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = provideDimensions().dp10),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TaskTypeCheckbox(
                isChecked = checkedStatePersonal,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                onCheckedChange = onCheckedChangePersonal
            )
            Spacer(modifier = Modifier.width(provideDimensions().dp5))
            Text(
                text = stringResource(id = R.string.personal),
                fontSize = R.dimen.sp16.toSp(),
                style = text
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TaskTypeCheckbox(
                isChecked = checkedStatePrivate,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                onCheckedChange = onCheckedChangePrivate
            )
            Spacer(modifier = Modifier.width(provideDimensions().dp5))
            Text(
                text = stringResource(id = R.string.private_checkbox),
                fontSize = R.dimen.sp16.toSp(),
                style = text
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TaskTypeCheckbox(
                isChecked = checkedStateSecret,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                onCheckedChange = onCheckedChangeSecret
            )
            Spacer(modifier = Modifier.width(provideDimensions().dp5))
            Text(
                text = stringResource(id = R.string.secret),
                fontSize = R.dimen.sp16.toSp(),
                style = text
            )
        }
    }
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
    iconColor: Color = colorResource(id = R.color.color_purple_white),
    onClickTextField: () -> Unit
) {
    val provideDimensions = provideDimensions()
    val interactionSource = remember { MutableInteractionSource() }

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
                        )
                        .then(
                            if (isReadOnly)
                                Modifier.clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = onClickTextField
                                )
                            else Modifier
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

@Composable
fun TaskTypeCheckbox(
    isChecked: Boolean,
    checkedIcon: Painter,
    uncheckedIcon: Painter,
    onCheckedChange: ((Boolean) -> Unit)?,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Image(
        painter = if (isChecked) checkedIcon else uncheckedIcon,
        contentDescription = "",
        modifier = Modifier
            .size(provideDimensions().dp18)
            .padding(top = provideDimensions().dp1)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onCheckedChange?.invoke(!isChecked)
            }
    )
}