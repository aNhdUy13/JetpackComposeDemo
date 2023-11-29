package com.mvpvn.jetpackcomposedemo.core.navigator

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mvpvn.jetpackcomposedemo.ui.screens.splash.SplashScreen
import com.mvpvn.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import com.mvpvn.jetpackcomposedemo.ui.screens.first.login.LoginScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.first.signup.SignUpScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.home.HomeScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.TaskScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.canceled.CanceledTaskScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.completed.CompletedTaskScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.ongoing.OnGoingTaskScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.PendingTaskScreen

//@ExperimentalAnimationApi
@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    JetpackComposeDemoTheme {
        NavHost(navController = navController, startDestination = Screen.Splash.route) {
            composable(route = Screen.Splash.route) {
                SplashScreen(
                    navigateHomeScreen = { navController.navigate(Screen.Home.route) },
                    navigateLoginScreen = { navController.navigate(Screen.Login.route) },
                    navigateSignUpScreen = { navController.navigate(Screen.SignUp.route) }
                )
            }

            composable(route = Screen.Login.route) {
                LoginScreen()
            }

            composable(route = Screen.SignUp.route) {
                SignUpScreen()
            }

            composable(route = Screen.Home.route) {
                HomeScreen()
            }

            composable(route = Screen.Task.route) {
                TaskScreen()
            }

            composable(route = Screen.CompletedTask.route) {
                CompletedTaskScreen()
            }

            composable(route = Screen.OnGoingTask.route) {
                OnGoingTaskScreen()
            }

            composable(route = Screen.PendingTask.route) {
                PendingTaskScreen()
            }

            composable(route = Screen.CanceledTask.route) {
                CanceledTaskScreen()
            }
        }
    }

//    val navController = rememberAnimatedNavController()
//    AnimatedNavHost(navController = navController, startDestination = Screen.Splash.route) {
//        composable(
//            Screen.Home.route,
//            exitTransition = { _, _ ->
//                slideOutHorizontally(
//                    targetOffsetX = { -300 },
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    )
//                ) + fadeOut(animationSpec = tween(300))
//            },
//            popEnterTransition = { _, _ ->
//                slideInHorizontally(
//                    initialOffsetX = { -300 },
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    )
//                ) + fadeIn(animationSpec = tween(300))
//            },
//        ) {
//            SplashScreen(navController)
//        }
//
//        composable(
//            Screen.Login.route,
//            enterTransition = { _, _ ->
//                slideInHorizontally(
//                    initialOffsetX = { 300 },
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    )
//                ) + fadeIn(animationSpec = tween(300))
//            },
//            exitTransition = { _, _ ->
//                slideOutHorizontally(
//                    targetOffsetX = { 300 },
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    )
//                ) + fadeOut(animationSpec = tween(300))
//            },
//            arguments = listOf(navArgument("id") { type = NavType.IntType })
//        ) {
//            LoginScreen(navController)
//        }
//    }
}