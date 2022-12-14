package kg.optima.mobile.auth.data.api.model.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class UserRegistrationResponse(
    @SerialName("jwt")
    val jwt: String? = null,

    @SerialName("refresh_token")
    val refreshToken: String? = null,

    @SerialName("refresh_token_exp")
    val refreshTokenExp: String? = null
)