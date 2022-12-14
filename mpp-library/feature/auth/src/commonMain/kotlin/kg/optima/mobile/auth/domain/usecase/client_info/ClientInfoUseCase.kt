package kg.optima.mobile.auth.domain.usecase.client_info

import kg.optima.mobile.auth.data.component.AuthPreferences
import kg.optima.mobile.auth.domain.usecase.login.GrantType
import kg.optima.mobile.base.data.model.Either
import kg.optima.mobile.base.domain.BaseUseCase
import kg.optima.mobile.core.error.Failure

class ClientInfoUseCase(
	private val authPreferences: AuthPreferences,
) : BaseUseCase<ClientInfoUseCase.Params, ClientInfo>() {

	override suspend fun execute(model: Params): Either<Failure, ClientInfo> =
		Either.Right(
			ClientInfo(
				isAuthorized = authPreferences.isAuthorized,
				clientId = authPreferences.clientId,
				grantTypes = getGrantTypes(),
			)
		)

	private fun getGrantTypes(): List<GrantType> {
		val grantTypes = mutableListOf<GrantType>()
		if (authPreferences.isAuthorized) {
			grantTypes.add(GrantType.Password)
			if (authPreferences.pin.isNotBlank()) grantTypes.add(GrantType.Pin)
			if (authPreferences.biometry.isNotBlank()) grantTypes.add(GrantType.Biometry)
		}
		return grantTypes
	}

	object Params
}