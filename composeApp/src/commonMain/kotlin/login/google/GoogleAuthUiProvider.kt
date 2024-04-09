package login.google

import GoogleUser

interface GoogleAuthUiProvider {
    suspend fun signIn(): GoogleUser?
}