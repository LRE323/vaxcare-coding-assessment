package com.example.vaxcare.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vaxcare.ListViewModel
import com.example.vaxcare.views.ListScreen
import kotlinx.coroutines.launch

@Composable
fun MainNavHost() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = NavigationDestinations.LIST_SCREEN.name
    ) {
        hostBookListScreen(navHostController)
    }
}

private fun NavGraphBuilder.hostBookListScreen(navController: NavController) {
    composable(
        route = NavigationDestinations.LIST_SCREEN.name
    ) {
        val viewModel: ListViewModel = hiltViewModel()

        LaunchedEffect(viewModel) {
            launch {
                viewModel.detailsScreenNavigationTrigger.collect {
                    // Nav logic here
                }
            }
        }

        ListScreen(
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            onIntent = viewModel::onIntent
        )
    }
}