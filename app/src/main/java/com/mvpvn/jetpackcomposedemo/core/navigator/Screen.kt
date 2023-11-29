package com.mvpvn.jetpackcomposedemo.core.navigator

import androidx.annotation.StringRes
import com.mvpvn.jetpackcomposedemo.R

open class Screen(val route: String, @StringRes val resourceId: Int) {
    object Splash : Screen("Splash", R.string.app_name)
    object Login : Screen("Login", R.string.app_name)
    object SignUp : Screen("SignUp", R.string.app_name)
    object Home : Screen("Home", R.string.app_name)
    object Task : Screen("Task", R.string.app_name)
    object CompletedTask : Screen("CompletedTask", R.string.app_name)
    object OnGoingTask : Screen("OnGoingTask", R.string.app_name)
    object PendingTask : Screen("PendingTask", R.string.app_name)
    object CanceledTask : Screen("CanceledTask", R.string.app_name)
}