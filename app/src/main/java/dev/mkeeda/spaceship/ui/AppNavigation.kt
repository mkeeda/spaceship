package dev.mkeeda.spaceship.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import dev.mkeeda.spaceship.ui.timeline.PostDetailsScreen
import dev.mkeeda.spaceship.ui.timeline.TimeLineScreen

sealed class Screen(val route: String) {
    object TimeLine : Screen("timeline")
    object PostDetails : Screen("post/{postId}") {
        fun createRoute(postId: Int): String {
            return "post/$postId"
        }
    }
}

@Composable
fun AppNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.TimeLine.route
    ) {
        composable(route = Screen.TimeLine.route) {
            TimeLineScreen(openPostDetails = { post ->
                navHostController.navigate(Screen.PostDetails.createRoute(postId = post.id))
            })
        }
        composable(
            route = Screen.PostDetails.route,
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) {
            val postId = requireNotNull(it.arguments?.getInt("postId")) {
                "postId must not be null."
            }
            PostDetailsScreen(postId)
        }
    }
}
