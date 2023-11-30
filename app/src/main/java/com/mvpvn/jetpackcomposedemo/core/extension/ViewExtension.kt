package com.mvpvn.jetpackcomposedemo.core.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun Int.toSp(): TextUnit {
    return dimensionResource(id = this).value.sp
}
