package com.mvpvn.jetpackcomposedemo.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mvpvn.jetpackcomposedemo.R

val hiraginoSansFont = FontFamily(Font(R.font.hiragino_sans_w4))
val hiraginoSansBoldFont = FontFamily(Font(R.font.hiragino_sans_w6))

val text = TextStyle(
    fontFamily = hiraginoSansFont,
    fontSize = 14.sp,
    fontWeight = FontWeight.Thin
)

val textBold = TextStyle(
    fontFamily = hiraginoSansBoldFont,
    fontSize = 14.sp,
    fontWeight = FontWeight.Bold
)

val button = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Bold
)