package com.mvpvn.jetpackcomposedemo.core.navigator

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mvpvn.jetpackcomposedemo.ui.screens.splash.SplashScreen
import com.mvpvn.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mvpvn.jetpackcomposedemo.ui.Screens
import com.mvpvn.jetpackcomposedemo.ui.screens.MainScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.first.login.LoginScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.first.signup.SignUpScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.home.HomeScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.TaskScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.canceled.CanceledTaskScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.completed.CompletedTaskScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.ongoing.OnGoingTaskScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.pending.PendingTaskScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.task.add.AddTaskScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.message.MessageScreen
import com.mvpvn.jetpackcomposedemo.ui.screens.profile.ProfileScreen
import com.mvpvn.jetpackcomposedemo.utilities.slideInTransition
import com.mvpvn.jetpackcomposedemo.utilities.slideOutTransition

@Composable
fun AppScreenNavigator() {
    val navController = rememberNavController()

    JetpackComposeDemoTheme {
        NavHost(navController = navController, startDestination = Screens.Splash.route) {
            composable(
                route = Screens.Splash.route,
                enterTransition = { slideInTransition(1000) },
                exitTransition = { slideOutTransition(-1000) },
                popEnterTransition = { slideInTransition(-1000) },
                popExitTransition = { slideOutTransition(1000) }
            ) {
                SplashScreen(
                    navigateMainScreen = { navController.navigateMainScreen() },
                    navigateLoginScreen = { navController.navigate(Screens.Login.route) },
                    navigateSignUpScreen = { navController.navigate(Screens.SignUp.route) }
                )
            }
            composable(
                route = Screens.Login.route,
                enterTransition = { slideInTransition(1000) },
                exitTransition = { slideOutTransition(-1000) },
                popEnterTransition = { slideInTransition(-1000) },
                popExitTransition = { slideOutTransition(1000) }
            ) {
                LoginScreen(
                    navigateMainScreen = { navController.navigateMainScreen() },
                    navigateSignUpScreen = { navController.navigate(Screens.SignUp.route) }
                )
            }
            composable(
                route = Screens.SignUp.route,
                enterTransition = { slideInTransition(1000) },
                exitTransition = { slideOutTransition(-1000) },
                popEnterTransition = { slideInTransition(-1000) },
                popExitTransition = { slideOutTransition(1000) }
            ) {
                SignUpScreen(
                    navigateMainScreen = { navController.navigateMainScreen() },
                    navigateSignInScreen = { navController.navigate(Screens.Login.route) }
                )
            }
            composable(
                route = Screens.Main.route,
                enterTransition = { slideInTransition(1000) },
                exitTransition = { slideOutTransition(-1000) },
                popEnterTransition = { slideInTransition(-1000) },
                popExitTransition = { slideOutTransition(1000) }
            ) {
                MainScreen(
                    switchTabAddTask = { navController.navigate(Screens.AddTask.route) },
                    onClickPendingTask = { navController.navigate(Screens.PendingTask.route) }
                )
            }

            composable(route = Screens.CompletedTask.route) {
                CompletedTaskScreen()
            }

            composable(route = Screens.OnGoingTask.route) {
                OnGoingTaskScreen()
            }

            composable(
                route = Screens.PendingTask.route,
                enterTransition = { slideInTransition(1000) },
                exitTransition = { slideOutTransition(-1000) },
                popEnterTransition = { slideInTransition(-1000) },
                popExitTransition = { slideOutTransition(1000) }
            ) {
                PendingTaskScreen()
            }

            composable(route = Screens.CanceledTask.route) {
                CanceledTaskScreen()
            }
            composable(
                route = Screens.AddTask.route,
                enterTransition = { slideInTransition(1000) },
                exitTransition = { slideOutTransition(-1000) },
                popEnterTransition = { slideInTransition(-1000) },
                popExitTransition = { slideOutTransition(1000) }
            ) {
                AddTaskScreen()
            }
        }
    }
}

@Composable
fun MainScreenNavigator(
    navController: NavHostController,
    currentRoute: String,
    onClickPendingTask: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = currentRoute
    ) {
        composable(route = Screens.Home.route) {
            HomeScreen(
                onClickPendingTask = onClickPendingTask
            )
        }

        composable(route = Screens.Task.route) {
            TaskScreen()
        }

        composable(route = Screens.Chat.route) {
            MessageScreen()
        }

        composable(route = Screens.Profile.route) {
            ProfileScreen()
        }

    }
}

fun NavHostController.navigateMainScreen() {
    navigate(Screens.Main.route) {
        popUpTo(Screens.Splash.route) { inclusive = true }
    }
}

fun NavHostController.navigateFirstScreen() {
    navigate(Screens.Splash.route) {
        popUpTo(Screens.Main.route) { inclusive = true }
    }
}

fun NavHostController.switchTab(route: String) {
    val startDestination = graph.findStartDestination().id
    navigate(route) {
        popUpTo(startDestination) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}