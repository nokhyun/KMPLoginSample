package di

import login.google.GoogleAuthCredentials
import org.koin.core.module.Module
import org.koin.dsl.module

val authModule = module {
    includes(googleAuthModule)
}

internal expect val googleAuthModule: Module

val utilModule = module {
    factory {
        GoogleAuthCredentials("CLIENT_KEY")
    }
}