package di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.nokhyun.login.GoogleAuthProviderImpl
import login.google.GoogleAuthProvider
import org.koin.dsl.bind

actual val googleAuthModule: Module = module {
    factoryOf(::GoogleAuthProviderImpl) bind GoogleAuthProvider::class
}
