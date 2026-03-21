package com.example.vaxcare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vaxcare.ListScreen

@Composable
fun MainNavHost() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = NavigationDestinations.LIST_SCREEN.name
    ) {
        hostListScreen()
    }
}

private fun NavGraphBuilder.hostListScreen() {
    composable(
        route = NavigationDestinations.LIST_SCREEN.name
    ) {
        ListScreen()
    }
}