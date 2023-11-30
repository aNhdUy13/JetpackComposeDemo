package com.mvpvn.jetpackcomposedemo.ui.screens.splash

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold

@Composable
fun SplashScreen(
    navigateHomeScreen: () -> Unit,
    navigateLoginScreen: () -> Unit,
    navigateSignUpScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp128)))
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "",
            modifier = Modifier.size(dimensionResource(id = R.dimen.dp290))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp70)))
        Image(
            painter = painterResource(id = R.drawable.img_app_title),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp16)))
        Text(
            text = stringResource(id = R.string.splash_greeting),
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dp7)),
            color = colorResource(id = R.color.splash_greeting),
            textAlign = TextAlign.Center,
            style = text
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp65)))
        Button(
            onClick = { navigateLoginScreen() },
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.dp45))
                .padding(horizontal = dimensionResource(id = R.dimen.dp40)),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp14))
        ) {
            Text(
                text = stringResource(id = R.string.login),
                color = colorResource(id = R.color.white),
                fontSize = R.dimen.sp16.toSp(),
                style = textBold
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp20)))
        Text(
            text = stringResource(id = R.string.sign_up),
            color = colorResource(id = R.color.black),
            fontSize = R.dimen.sp16.toSp(),
            style = textBold
        )
//        Button(
//            onClick = { navigateSignUpScreen() },
//            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.white)),
//            border = null
//        ) {
//            Text(
//                text = stringResource(id = R.string.sign_up),
//                color = colorResource(id = R.color.black)
//            )
//        }
    }
}
