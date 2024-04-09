package di

import androidx.credentials.CredentialManager
import com.nokhyun.login.GoogleAuthProviderImpl
import login.google.GoogleAuthProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal actual val googleAuthModule: Module = module {
    factory { CredentialManager.create(androidContext()) } bind CredentialManager::class
    factoryOf(::GoogleAuthProviderImpl) bind GoogleAuthProvider::class
}
