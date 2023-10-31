package com.welldressedmen.nari.feature.onboard

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.welldressedmen.nari.feature.main.MainActivity

enum class TestScreen() {
    Zero,
    One,
    Two,
    Three,
    Four,
    Five,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardScreen(viewModel: OnBoardViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                })
        }
    ) {innerPadding ->
        NavigationGraph(navController = navController, innerPadding = innerPadding, vm = viewModel)
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, innerPadding: PaddingValues, vm: OnBoardViewModel) {
    val context = LocalContext.current
    val actions = remember(navController) { OnBoardAction(navController) }

    NavHost(navController = navController, startDestination = TestScreen.Zero.name,
        modifier = Modifier.padding(innerPadding)) {
        composable(route = TestScreen.Zero.name) {
            SurveyScreen { actions.goToSurveyOneScreen.invoke() }
        }
        composable(route = TestScreen.One.name) {
            SurveyScreenOne(vm) {
                if (vm.userSex != "")
                    actions.goToSurveyTwoScreen.invoke()
            }
        }
        composable(route = TestScreen.Two.name) {
            SurveyScreenTwo(vm) {
                if (vm.userCold != "")
                    actions.goToSurveyThreeScreen.invoke()
            }
        }
        composable(route = TestScreen.Three.name) {
            SurveyScreenThree(vm) {
                if (vm.userHot != "")
                    actions.goToSurveyFour.invoke()
            }
        }
        composable(route = TestScreen.Four.name) {
            SurveyScreenFour(vm) {
                if (vm.userBody != "")
                    actions.goToSurveyFive.invoke()
            }
        }
        composable(route = TestScreen.Five.name) {
            SurveyScreenFive(vm) {
                if (vm.userPreferences != "") {
                    vm.updateUserInfo()
                    Log.d("OnBoardScreen", "NavigationGraph: ${vm.userSex}")
                    Log.d("OnBoardScreen", "NavigationGraph: ${vm.userCold}")
                    Log.d("OnBoardScreen", "NavigationGraph: ${vm.userHot}")
                    Log.d("OnBoardScreen", "NavigationGraph: ${vm.userBody}")
                    Log.d("OnBoardScreen", "NavigationGraph: ${vm.userPreferences}")
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as Activity).finish()
                    vm.state.value.let { state ->
                        when (state) {
                            is OnBoardUiStateReady -> {
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            }
                            else -> {
                                /*no-op*/
                                Log.d("OnBoardScreen", "State: ${state}")
                            }
                        }
                    }
                }
            }
        }
    }
}

class OnBoardAction(private val navController: NavHostController) {
    val goToSurveyOneScreen: () -> Unit = {
        navController.navigate(TestScreen.One.name)
    }
    val goToSurveyTwoScreen: () -> Unit = {
        navController.navigate(TestScreen.Two.name)
    }
    val goToSurveyThreeScreen: () -> Unit = {
        navController.navigate(TestScreen.Three.name)
    }
    val goToSurveyFour: () -> Unit = {
        navController.navigate(TestScreen.Four.name)
    }
    val goToSurveyFive: () -> Unit = {
        navController.navigate(TestScreen.Five.name)
    }
}