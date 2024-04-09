package com.nokhyun.login

import GoogleUser
import android.content.Context
import androidx.credentials.Credential
import android.credentials.GetCredentialException
import androidx.credentials.GetCredentialRequest
import androidx.credentials.CredentialManager
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import login.google.GoogleAuthCredentials
import login.google.GoogleAuthUiProvider

internal class GoogleAuthUiProviderImpl(
    private val activityContext: Context,
    private val credentialManager: CredentialManager,
    private val credentials: GoogleAuthCredentials
) : GoogleAuthUiProvider {
    override suspend fun signIn(): GoogleUser? {
        return try{
            val credential = credentialManager.getCredential(
                context = activityContext,
                request = getCredentialRequest()
            ).credential
            
            getGoogleUserFromCredential(credential = credential)
        } catch (e: GetCredentialException){
            null
        } catch (e: NullPointerException){
            null
        }
    }
    
    private fun getGoogleUserFromCredential(credential: Credential): GoogleUser?{
        return when {
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                try{
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    GoogleUser(
                        idToken = googleIdTokenCredential.id,
                        displayName = googleIdTokenCredential.displayName?: "",
                        profilePicUrl = googleIdTokenCredential.profilePictureUri?.toString()
                    )
                }catch (e: GoogleIdTokenParsingException){
                    e.printStackTrace()
                    null
                }
            }
            else -> null
        }
    }
    
    private fun getCredentialRequest(): GetCredentialRequest {
        return GetCredentialRequest.Builder()
            .addCredentialOption(getGoogleIdOption(serverId = credentials.serverId))
            .build()
    }
    
    private fun getGoogleIdOption(serverId: String): GetGoogleIdOption{
        return GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(true)
            .setServerClientId(serverId)
            .build()
    }
}
