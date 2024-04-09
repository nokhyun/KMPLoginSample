package di

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val platformModule = module {
    singleOf(::Platform)
}

expect class Platform {
    val name: String
}

fun appModule() = listOf(platformModule, authModule, utilModule)

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}