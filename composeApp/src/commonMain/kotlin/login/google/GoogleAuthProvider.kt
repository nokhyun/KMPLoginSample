package login.google

import androidx.compose.runtime.Composable

/**
 * IOS
 * */
interface GoogleAuthProvider {
    @Composable
    fun getUiProvider(): GoogleAuthUiProvider
    suspend fun signOut()
}
