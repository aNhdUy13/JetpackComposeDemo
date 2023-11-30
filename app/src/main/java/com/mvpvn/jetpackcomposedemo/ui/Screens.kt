package com.mvpvn.jetpackcomposedemo.ui

import androidx.annotation.StringRes
import com.mvpvn.jetpackcomposedemo.R

open class Screens(val route: String, @StringRes val resourceId: Int) {
    object Splash : Screens("Splash", R.string.app_name)
    object Login : Screens("Login", R.string.app_name)
    object SignUp : Screens("SignUp", R.string.app_name)
    object Home : Screens("Home", R.string.app_name)
    object Task : Screens("Task", R.string.app_name)
    object CompletedTask : Screens("CompletedTask", R.string.app_name)
    object OnGoingTask : Screens("OnGoingTask", R.string.app_name)
    object PendingTask : Screens("PendingTask", R.string.app_name)
    object CanceledTask : Screens("CanceledTask", R.string.app_name)
}