package com.mvpvn.jetpackcomposedemo.ui.screens.task.pending

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
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
            fontSize = R.dimen.sp20.toSp(),
            color = colorResource(id = R.color.black),
            style = textBold.copy(
                fontWeight = FontWeight(500)
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
fun PendingTaskTimelineItemView(item: Task) {

}