package dev.mkeeda.spaceship.ui.foundation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.ui.timeline.PostDetailsScreen
import dev.mkeeda.spaceship.ui.timeline.TimelineScreen

sealed class Screen(val route: String, val name: String) {
    object Timeline : Screen(route = "timeline", name = "Timeline")
    object PostDetails : Screen(
        route = "post/{${ScreenArgsKey.PostDetails.PostId}}",
        name = "PostDetails"
    ) {
        fun createRoute(postId: PostId): String {
            return "post/${postId.value}"
        }
    }

    companion object {
        fun fromRoute(route: String?): Screen {
            return when (route?.substringBefore("/")) {
                Timeline.route -> Timeline
                PostDetails.route.substringBefore("/") -> PostDetails
                else -> Timeline
            }
        }
    }
}

object ScreenArgsKey {
    object PostDetails {
        const val PostId = "postId"
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
            arguments = listOf(navArgument(ScreenArgsKey.PostDetails.PostId) { type = NavType.LongType})
        ) {
            val postId = requireNotNull(it.arguments?.getLong(ScreenArgsKey.PostDetails.PostId)) {
                "postId must not be null."
            }
            PostDetailsScreen(postId = postId)
        }
    }
}
