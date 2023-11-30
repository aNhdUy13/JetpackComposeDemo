package com.mvpvn.jetpackcomposedemo.core.navigator

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mvpvn.jetpackcomposedemo.ui.screens.splash.SplashScreen
import com.mvpvn.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import com.mvpvn.jetpackcomposedemo.ui.Screens
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
        NavHost(navController = navController, startDestination = Screens.Splash.route) {
            composable(
                route = Screens.Splash.route,
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -1000 },
                        animationSpec = tween(700)
                    )
                },
            ) {
                SplashScreen(
                    navigateHomeScreen = { navController.navigate(Screens.Home.route) },
                    navigateLoginScreen = { navController.navigate(Screens.Login.route) },
                    navigateSignUpScreen = { navController.navigate(Screens.SignUp.route) }
                )
            }

            composable(
                route = Screens.Login.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -1000 },
                        animationSpec = tween(700)
                    )
                }) {
                LoginScreen()
            }

            composable(route = Screens.SignUp.route) {
                SignUpScreen()
            }

            composable(route = Screens.Home.route) {
                HomeScreen()
            }

            composable(route = Screens.Task.route) {
                TaskScreen()
            }

            composable(route = Screens.CompletedTask.route) {
                CompletedTaskScreen()
            }

            composable(route = Screens.OnGoingTask.route) {
                OnGoingTaskScreen()
            }

            composable(route = Screens.PendingTask.route) {
                PendingTaskScreen()
            }

            composable(route = Screens.CanceledTask.route) {
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