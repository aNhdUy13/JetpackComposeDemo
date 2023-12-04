package com.mvpvn.jetpackcomposedemo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.navigator.MainScreenNavigator
import com.mvpvn.jetpackcomposedemo.core.navigator.switchTab
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions
import com.mvpvn.jetpackcomposedemo.ui.Screens

const val NOT_TAB = -1
const val TAB_HOME = 0
const val TAB_TASK = 1
const val TAB_CHAT = 2
const val TAB_PROFILE = 3

@Composable
fun MainScreen(switchTabAddTask: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val navController = rememberNavController()
        MainBottomNavigationBar(
            navController = navController,
            switchTab = { _, route ->
                navController.switchTab(route)
            },
            switchTabAddTask = switchTabAddTask
        )
    }
}


@Composable
fun ConstraintLayoutScope.MainBottomNavigationBar(
    navController: NavHostController,
    switchTab: (Int, String) -> Unit,
    switchTabAddTask: () -> Unit,
) {
    val provideDimension = provideDimensions()
    val bottomNavigation = createRef()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screens.Home.route

    val interactionSource = remember { MutableInteractionSource() }

    MainScreenNavigator(navController, currentRoute)
    Row(
        modifier = Modifier
            .padding(horizontal = provideDimension.dp24)
            .navigationBarsPadding()
            .constrainAs(bottomNavigation) {
                bottom.linkTo(
                    anchor = parent.bottom,
                    margin = provideDimension.dp20
                )
            }
    ) {
        Row(
            modifier = Modifier
                .shadow(
                    elevation = provideDimension.dp3,
                    shape = RoundedCornerShape(provideDimension.dp14)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(provideDimension.dp14)
                )

        ) {
            Box(modifier = Modifier.weight(1f)) {
                MainBottomNavItem(
                    index = TAB_HOME,
                    iconEnable = R.drawable.ic_home_enable,
                    iconDisable = R.drawable.ic_home_disable,
                    isSelected = currentRoute == Screens.Home.route
                ) {
                    switchTab(TAB_HOME, Screens.Home.route)
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                MainBottomNavItem(
                    index = TAB_TASK,
                    iconEnable = R.drawable.ic_task_enable,
                    iconDisable = R.drawable.ic_task_disable,
                    isSelected = currentRoute == Screens.Task.route
                ) {
                    switchTab(TAB_TASK, Screens.Task.route)
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = switchTabAddTask
                    )
                    .align(alignment = CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add_task),
                    contentDescription = "",
                    alignment = Center
                )
            }

            Box(modifier = Modifier.weight(1f)) {
                MainBottomNavItem(
                    index = TAB_CHAT,
                    iconEnable = R.drawable.ic_chat_enable,
                    iconDisable = R.drawable.ic_chat_disable,
                    isSelected =  currentRoute == Screens.Chat.route
                ) {
                    switchTab(TAB_CHAT, Screens.Chat.route)
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                MainBottomNavItem(
                    index = TAB_PROFILE,
                    iconEnable = R.drawable.ic_profile_enable,
                    iconDisable = R.drawable.ic_profile_disable,
                    isSelected =  currentRoute == Screens.Profile.route
                ) {
                    switchTab(TAB_PROFILE, Screens.Profile.route)
                }
            }
        }
    }

}

@Composable
fun MainBottomNavItem(
    index: Int,
    iconEnable: Int,
    iconDisable: Int,
    isSelected: Boolean,
    onItemClick: (Int) -> Unit
) {
    val providerDimension = provideDimensions()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onItemClick(index) }
            .fillMaxWidth()
            .height(providerDimension.dp74)
            .padding(horizontal = providerDimension.dp10, vertical = providerDimension.dp8)
    ) {
        Image(
            painter = painterResource(id = if (isSelected) iconEnable else iconDisable),
            contentDescription = ""
        )

        Image(
            painter = painterResource(id = R.drawable.ic_dot_selected),
            contentDescription = "",
            modifier = Modifier.padding(top = providerDimension.dp3),
            alpha = if (isSelected) 1f else 0f
        )
    }
}

