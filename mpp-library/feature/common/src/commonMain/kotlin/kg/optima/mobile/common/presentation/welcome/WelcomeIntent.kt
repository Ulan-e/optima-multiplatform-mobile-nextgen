package kg.optima.mobile.common.presentation.welcome

import kg.optima.mobile.auth.domain.usecase.client_info.ClientInfo
import kg.optima.mobile.auth.domain.usecase.client_info.ClientInfoUseCase
import kg.optima.mobile.base.presentation.Intent
import org.koin.core.component.inject

class WelcomeIntent(
	override val state: WelcomeState,
) : Intent<ClientInfo>() {

	private val clientInfoUseCase: ClientInfoUseCase by inject()

	fun checkIsAuthorized() {
		launchOperation {
			clientInfoUseCase.execute(ClientInfoUseCase.Params)
		}
	}
}
