package dev.mkeeda.spaceship.ui.foundation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import dev.mkeeda.spaceship.ui.timeline.PostDetailsScreen
import dev.mkeeda.spaceship.ui.timeline.TimelineScreen
import dev.mkeeda.spaceship.ui.timeline.presentation.PostId

sealed class Screen(val route: String, val name: String) {
    object Timeline : Screen(route = "timeline", name = "Timeline")
    object PostDetails : Screen(route = "post/{postId}", name = "PostDetails") {
        fun createRoute(postId: PostId): String {
            return "post/${postId.value}"
        }
    }

    companion object {
        fun fromRoute(route: String?): Screen {
            return when (route?.substringBefore("/")) {
                Timeline.route-> Timeline
                PostDetails.route.substringBefore("/") -> PostDetails
                else -> Timeline
            }
        }
    }
}

@Composable
fun AppNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Timeline.route
    ) {
        composable(route = Screen.Timeline.route) {
            TimelineScreen(openPostDetails = { post ->
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
