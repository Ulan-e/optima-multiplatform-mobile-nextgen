package kg.optima.mobile.android.ui.features.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import dev.icerock.moko.parcelize.Parcelize
import kg.optima.mobile.android.ui.base.BaseScreen
import kg.optima.mobile.android.ui.features.common.MainContainer
import kg.optima.mobile.auth.AuthFeatureFactory
import kg.optima.mobile.auth.presentation.login.LoginIntent
import kg.optima.mobile.auth.presentation.login.LoginState
import kg.optima.mobile.base.presentation.State
import kg.optima.mobile.base.utils.emptyString
import kg.optima.mobile.core.navigation.ScreenModel
import kg.optima.mobile.design_system.android.ui.buttons.PrimaryButton
import kg.optima.mobile.design_system.android.ui.checkbox.Checkbox
import kg.optima.mobile.design_system.android.ui.input.InputField
import kg.optima.mobile.design_system.android.ui.input.PasswordInput
import kg.optima.mobile.design_system.android.ui.text_fields.TitleTextField
import kg.optima.mobile.design_system.android.ui.toolbars.NavigationIcon
import kg.optima.mobile.design_system.android.ui.toolbars.ToolbarContent
import kg.optima.mobile.design_system.android.ui.toolbars.ToolbarInfo
import kg.optima.mobile.design_system.android.utils.resources.ComposeColors
import kg.optima.mobile.design_system.android.values.Deps

@Parcelize
class LoginScreen(
	private val nextScreenModel: ScreenModel,
) : BaseScreen {

	@OptIn(ExperimentalMaterialApi::class)
	@Composable
	override fun Content() {
		val product = remember {
			AuthFeatureFactory.create<LoginIntent, LoginState>(nextScreenModel)
		}
		val state = product.state
		val intent = product.intent

		val model by state.stateFlow.collectAsState(initial = State.StateModel.Initial)

		val clientIdInputFieldState = remember { mutableStateOf(emptyString) }
		val passwordInputFieldState = remember { mutableStateOf(emptyString) }
		val checkedState = remember { mutableStateOf(true) }

		when (val loginState = model) {
			is State.StateModel.Initial ->
				intent.getClientId()
			is LoginState.LoginStateModel.ClientId ->
				clientIdInputFieldState.value = loginState.clientId.orEmpty()
		}

		val signIn: () -> Unit = {
			intent.signIn(
				info = LoginIntent.SignInInfo.Password(
					clientId = clientIdInputFieldState.value,
					password = passwordInputFieldState.value,
				)
			)
		}

		MainContainer(
			mainState = model,
			toolbarInfo = ToolbarInfo(content = ToolbarContent.Nothing),
			contentModifier = Modifier
				.padding(all = Deps.Spacing.standardPadding)
				.background(ComposeColors.Background),
			contentHorizontalAlignment = Alignment.Start,
		) {
			TitleTextField(
				modifier = Modifier.padding(top = Deps.Spacing.standardMargin * 3),
				text = "??????????????????????"
			)
			InputField(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = Deps.Spacing.marginFromTitle),
				valueState = clientIdInputFieldState,
				hint = "Client ID",
				keyboardType = KeyboardType.Number,
				imeAction = ImeAction.Next,
				bottomActionButton = "?????????????????? Client ID" to {
					// TODO get clientid
				},
			)
			PasswordInput(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = Deps.Spacing.spacing),
				passwordState = passwordInputFieldState,
				hint = "????????????",
				onKeyboardActionDone = signIn,
			)
			Checkbox(
				modifier = Modifier.padding(top = Deps.Spacing.spacing),
				checkedState = checkedState,
				text = "?????????????????? ??????????",
			)
			Spacer(modifier = Modifier.weight(1f))
			PrimaryButton(
				modifier = Modifier
					.fillMaxWidth()
					.padding(all = Deps.Spacing.standardPadding),
				text = "????????????????????",
				onClick = signIn,
			)
		}
	}
}