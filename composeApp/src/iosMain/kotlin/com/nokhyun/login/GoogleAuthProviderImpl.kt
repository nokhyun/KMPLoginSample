package com.nokhyun.login

import androidx.compose.runtime.Composable
import cocoapods.GoogleSignIn.GIDSignIn
import kotlinx.cinterop.ExperimentalForeignApi
import login.google.GoogleAuthProvider
import login.google.GoogleAuthUiProvider

/**
 * IOS
 * */
internal class GoogleAuthProviderImpl: GoogleAuthProvider {

    @Composable
    override fun getUiProvider(): GoogleAuthUiProvider {
        return GoogleAuthUiProviderImpl()
    }

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun signOut() {
        GIDSignIn.sharedInstance.signOut()
    }
}