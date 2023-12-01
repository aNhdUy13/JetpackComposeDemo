package com.mvpvn.jetpackcomposedemo.utilities

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

fun slideInTransition(offsetX: Int, duration: Int = 500) = slideInHorizontally(
    initialOffsetX = { offsetX },
    animationSpec = tween(duration)
)

fun slideOutTransition(offsetX: Int, duration: Int = 500) = slideOutHorizontally(
    targetOffsetX = { offsetX },
    animationSpec = tween(duration)
)