package kg.optima.mobile.registration.data.repository

import kg.optima.mobile.base.data.BaseDataSource
import kg.optima.mobile.registration.data.api.RegistrationApi
import kg.optima.mobile.registration.data.api.model.PhoneCheckRequest


class RegistrationRepositoryImpl(
	private val registrationApi: RegistrationApi,
) : RegistrationRepository, BaseDataSource() {
	override suspend fun checkPhoneNumber(phoneNumber: String) = apiCall {
		registrationApi.checkPhoneNumber(PhoneCheckRequest(phoneNumber))
	}

}