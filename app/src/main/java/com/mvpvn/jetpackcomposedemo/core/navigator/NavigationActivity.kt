package com.mvpvn.jetpackcomposedemo.core.navigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mvpvn.jetpackcomposedemo.core.extension.makeFullScreen
import com.mvpvn.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

open class NavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.makeFullScreen()
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    AppScreenNavigator()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    JetpackComposeDemoTheme {
        MainApp()
    }
}