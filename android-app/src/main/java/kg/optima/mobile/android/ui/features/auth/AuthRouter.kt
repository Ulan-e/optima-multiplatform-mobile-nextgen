package kg.optima.mobile.android.ui.features.auth

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import kg.optima.mobile.android.ui.FeatureRouter
import kg.optima.mobile.android.ui.features.auth.login.LoginScreen
import kg.optima.mobile.android.ui.features.auth.pin.PinEnterScreen
import kg.optima.mobile.android.ui.features.auth.pin.PinSetScreen
import kg.optima.mobile.feature.auth.AuthScreenModel

object AuthRouter : FeatureRouter<AuthScreenModel> {
	@Composable
	override fun compose(screenModel: AuthScreenModel): Screen {
		return when (screenModel) {
			AuthScreenModel.Login -> LoginScreen
			is AuthScreenModel.PinEnter -> PinEnterScreen(screenModel.showBiometry)
			AuthScreenModel.PinSet -> PinSetScreen
		}
	}

}