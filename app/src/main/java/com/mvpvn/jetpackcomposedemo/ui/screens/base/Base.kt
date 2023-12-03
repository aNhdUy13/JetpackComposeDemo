package com.mvpvn.jetpackcomposedemo.ui.screens.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold


@Composable
fun BackActionBar(title: String) {
    val provideDimension = provideDimensions()
    val interactionSource = remember { MutableInteractionSource() }

    Spacer(modifier = Modifier.padding(provideDimension.dp10))
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = provideDimension.dp36, vertical = provideDimension.dp10)
    ) {
        val (imageBack, textTitle) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "",
            modifier = Modifier
//                .clickable(
//                    interactionSource = interactionSource,
//                    indication = null,
//                    onClick = onBackPress
//                )
                .constrainAs(imageBack) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = title,
            modifier = Modifier.constrainAs(textTitle) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            color = colorResource(id = R.color.title_back_action_bar),
            fontSize = R.dimen.sp18.toSp(),
            style = textBold
        )
    }
}