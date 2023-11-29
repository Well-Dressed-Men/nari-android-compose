package com.welldressedmen.nari.feature.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.welldressedmen.nari.R
import com.welldressedmen.nari.feature.main.board.BoardScreen
import com.welldressedmen.nari.feature.main.home.HomeScreen
import com.welldressedmen.nari.feature.main.profile.ProfileScreen
import com.welldressedmen.nari.global.BOARD
import com.welldressedmen.nari.global.HOME
import com.welldressedmen.nari.global.PROFILE
import com.welldressedmen.nari.ui.theme.NariTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val navItem = listOf(
        BottomNavItem.Home,
        BottomNavItem.Board,
        BottomNavItem.Profile,
    )

    NariTheme {
        Scaffold(
//            bottomBar = {
//                BottomNavigation(
//                    backgroundColor =  Color.White,
//                ) {
//                    val navBackStackEntry = navController.currentBackStackEntryAsState().value
//                    val currentRoute = navBackStackEntry?.destination?.route
//
//                    navItem.forEach { screen ->
//                        Log.d("gaeungaeun", currentRoute.toString())
//                        BottomNavigationItem(
//                            icon = {
//                                val iconImage = if (currentRoute == screen.screenRoute) {
//                                    painterResource(id = screen.clickedIcon)
//                                } else {
//                                    painterResource(id = screen.icon)
//                                }
//                                Icon(
//                                    painter = iconImage,
//                                    contentDescription = screen.screenRoute,
//                                    modifier = Modifier.size(24.dp)
//                                )
//                            },
//                            selected = currentRoute == screen.screenRoute,
//                            onClick = {
//                                navController.navigate(screen.screenRoute) {
//                                    popUpTo(navController.graph.startDestinationId)
//                                    launchSingleTop = true
//                                }
//                            }
//                        )
//                    }
//                }
//            }
        ) { innerPadding ->
            NavigationGraph(navController = navController, innerPadding = innerPadding)
        }
    }
}

sealed class BottomNavItem(
    val title: Int,
    val icon: Int,
    val clickedIcon: Int,
    val screenRoute: String,
) {
    object Home :
        BottomNavItem(R.string.bnb_home, R.drawable.ic_home, R.drawable.ic_home_clicked, HOME)

    object Board :
        BottomNavItem(R.string.bnb_board, R.drawable.ic_board, R.drawable.ic_board_clicked, BOARD)

    object Profile : BottomNavItem(
        R.string.bnb_profile,
        R.drawable.ic_profile,
        R.drawable.ic_profile_clicked,
        PROFILE
    )
}

@Composable
fun NavigationGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screenRoute,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Board.screenRoute) {
            BoardScreen()
        }
        composable(BottomNavItem.Profile.screenRoute) {
            ProfileScreen()
        }
    }
}
