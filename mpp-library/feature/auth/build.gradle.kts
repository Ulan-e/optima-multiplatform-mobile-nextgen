/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("multiplatform-library-convention")
}

dependencies {
    commonMainImplementation(libs.coroutines)

    commonMainApi(libs.mokoMvvmLiveData)
    commonMainApi(libs.mokoResources)
    commonMainApi(libs.mokoErrors)
}
