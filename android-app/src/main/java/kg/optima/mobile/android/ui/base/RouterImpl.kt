package kg.optima.mobile.android.ui.base

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kg.optima.mobile.android.ui.features.auth.AuthRouter
import kg.optima.mobile.android.ui.features.main.MainRouter
import kg.optima.mobile.android.ui.features.welcome.WelcomeRouter
import kg.optima.mobile.core.navigation.ScreenModel
import kg.optima.mobile.feature.auth.AuthScreenModel
import kg.optima.mobile.feature.main.MainScreenModel
import kg.optima.mobile.feature.welcome.WelcomeScreenModel

object RouterImpl : Router {
	@SuppressLint("ComposableNaming")
	@Composable
	override fun push(screenModels: List<ScreenModel>) {
		val navigator = LocalNavigator.currentOrThrow
		compose(screenModels = screenModels).forEach {
			if (it.dropBackStack) {
				navigator.replaceAll(it.screen)
			} else {
				navigator.push(it.screen)
			}
		}
	}

	@Composable
	override fun compose(screenModels: List<ScreenModel>): List<RouteInfo> {
		val screens = mutableListOf<RouteInfo>()
		screenModels.forEach {
			val screen = when (it) {
				is WelcomeScreenModel -> RouteInfo(WelcomeRouter.compose(screenModel = it), it.dropBackStack)
				is AuthScreenModel -> RouteInfo(AuthRouter.compose(screenModel = it), it.dropBackStack)
				is MainScreenModel -> RouteInfo(MainRouter.compose(screenModel = it), it.dropBackStack)
				else -> RouteInfo(MainRouter.default(it), it.dropBackStack)
			}
			screens.add(screen)
		}
		return screens
	}
}
