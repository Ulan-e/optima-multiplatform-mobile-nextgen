/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package kg.optima.mobile

import com.russhwolf.settings.MockSettings
import com.russhwolf.settings.Settings
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandler

internal fun createSharedFactory(
    settings: Settings = MockSettings(),
    mock: MockRequestHandler
): SharedFactory {
    return SharedFactory(
        settings = settings,
        antilog = TestAntilog(),
        baseUrl = "https://localhost",
        httpClientEngine = MockEngine(mock)
    )
}
