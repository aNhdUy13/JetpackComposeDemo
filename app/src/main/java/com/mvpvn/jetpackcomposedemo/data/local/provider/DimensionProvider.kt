package com.mvpvn.jetpackcomposedemo.data.local.provider

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.mvpvn.jetpackcomposedemo.R

@Composable
fun provideDimensions() = Dimensions(
    dp1 = dimensionResource(id = R.dimen.dp1),
    dp2 = dimensionResource(id = R.dimen.dp2),
    dp3 = dimensionResource(id = R.dimen.dp3),
    dp4 = dimensionResource(id = R.dimen.dp4),
    dp5 = dimensionResource(id = R.dimen.dp5),
    dp6 = dimensionResource(id = R.dimen.dp6),
    dp7 = dimensionResource(id = R.dimen.dp7),
    dp8 = dimensionResource(id = R.dimen.dp8),
    dp9 = dimensionResource(id = R.dimen.dp9),
    dp10 = dimensionResource(id = R.dimen.dp10),
    dp11 = dimensionResource(id = R.dimen.dp11),
    dp12 = dimensionResource(id = R.dimen.dp12),
    dp13 = dimensionResource(id = R.dimen.dp13),
    dp14 = dimensionResource(id = R.dimen.dp14),
    dp15 = dimensionResource(id = R.dimen.dp15),
    dp16 = dimensionResource(id = R.dimen.dp16),
    dp32 = dimensionResource(id = R.dimen.dp32),
    dp36 = dimensionResource(id = R.dimen.dp36),
    dp38 = dimensionResource(id = R.dimen.dp38),
    dp45 = dimensionResource(id = R.dimen.dp45),
    dp52 = dimensionResource(id = R.dimen.dp52),
    dp55 = dimensionResource(id = R.dimen.dp55),
    dp58 = dimensionResource(id = R.dimen.dp58),
    dp60 = dimensionResource(id = R.dimen.dp60),
    dp65 = dimensionResource(id = R.dimen.dp65),
    dp70 = dimensionResource(id = R.dimen.dp70),
    dp76 = dimensionResource(id = R.dimen.dp76),
    dp90 = dimensionResource(id = R.dimen.dp90),
    dp95 = dimensionResource(id = R.dimen.dp95),
    dp130 = dimensionResource(id = R.dimen.dp130)
)

data class Dimensions(
    val dp1: Dp,
    val dp2: Dp,
    val dp3: Dp,
    val dp4: Dp,
    val dp5: Dp,
    val dp6: Dp,
    val dp7: Dp,
    val dp8: Dp,
    val dp9: Dp,
    val dp10: Dp,
    val dp11: Dp,
    val dp12: Dp,
    val dp13: Dp,
    val dp14: Dp,
    val dp15: Dp,
    val dp16: Dp,
    val dp32: Dp,
    val dp36: Dp,
    val dp38: Dp,
    val dp45: Dp,
    val dp52: Dp,
    val dp55: Dp,
    val dp58: Dp,
    val dp60: Dp,
    val dp65: Dp,
    val dp70: Dp,
    val dp76: Dp,
    val dp90: Dp,
    val dp95: Dp,
    val dp130: Dp
)