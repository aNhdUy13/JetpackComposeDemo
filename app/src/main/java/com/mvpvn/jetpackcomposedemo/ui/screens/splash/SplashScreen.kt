package com.mvpvn.jetpackcomposedemo.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SplashScreen(
    navigateHomeScreen: () -> Unit,
    navigateLoginScreen: () -> Unit,
    navigateSignUpScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navigateLoginScreen() }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navigateSignUpScreen() }) {
            Text(text = "Sign Up")
        }

    }
}
