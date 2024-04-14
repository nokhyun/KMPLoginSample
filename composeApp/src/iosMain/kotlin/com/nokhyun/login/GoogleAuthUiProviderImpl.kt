package com.nokhyun.login

import GoogleUser
import cocoapods.GoogleSignIn.GIDSignIn
import kotlinx.cinterop.ExperimentalForeignApi
import login.google.GoogleAuthUiProvider
import platform.UIKit.UIApplication
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class GoogleAuthUiProviderImpl : GoogleAuthUiProvider {
    @OptIn(ExperimentalForeignApi::class)
    override suspend fun signIn(): GoogleUser? {
        return suspendCoroutine { continuation ->

            val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController

            if (rootViewController === null) {
                continuation.resume(null)
            } else {
                GIDSignIn.sharedInstance
                    .signInWithPresentingViewController(rootViewController) { gidSignInResult, nsError ->
                        val idToken = gidSignInResult?.user?.idToken?.tokenString
                        val profile = gidSignInResult?.user?.profile
                        if (idToken != null) {
                            val googleUser = GoogleUser(
                                idToken = idToken,
                                displayName = profile?.name ?: "",
                                profilePicUrl = profile?.imageURLWithDimension(320u)?.absoluteString
                            )
                            continuation.resume(googleUser)
                        } else {
                            continuation.resume(null)
                        }
                    }
            }
        }
    }
}