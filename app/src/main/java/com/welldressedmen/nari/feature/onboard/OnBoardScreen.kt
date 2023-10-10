package com.welldressedmen.nari.feature.onboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class TestScreen() {
    Zero,
    One,
    Two,
    Three,
    FourMale,
    FourFemale,
    FiveMale,
    FiveFemale
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("hello") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                })
        }
    ) {innerPadding ->
        NavigationGraph(navController = navController, innerPadding = innerPadding)
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, innerPadding: PaddingValues) {
    val actions = remember(navController) { OnBoardAction(navController) }

    NavHost(navController = navController, startDestination = TestScreen.Zero.name,
        modifier = Modifier.padding(innerPadding)) {
        composable(route = TestScreen.Zero.name) {
            SurveyScreen { actions.goToSurveyOneScreen.invoke() }
        }
        composable(route = TestScreen.One.name) {
            SurveyScreenOne { actions.goToSurveyTwoScreen.invoke() }
        }
        composable(route = TestScreen.Two.name) {
            SurveyScreenTwo { actions.goToSurveyThreeScreen.invoke() }
        }
        composable(route = TestScreen.Three.name) {
            SurveyScreenThree { actions.goToSurveyFourMaleScreen.invoke() }
        }
        composable(route = TestScreen.FourMale.name) {
            SurveyScreenThree { actions.goToSurveyFourFemaleScreen.invoke() }
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
    val goToSurveyFourMaleScreen: () -> Unit = {
        navController.navigate(TestScreen.FourMale.name)
    }
    val goToSurveyFourFemaleScreen: () -> Unit = {
        navController.navigate(TestScreen.FourFemale.name)
    }
    val goToSurveyFiveMaleScreen: () -> Unit = {
        navController.navigate(TestScreen.FiveFemale.name)
    }
}