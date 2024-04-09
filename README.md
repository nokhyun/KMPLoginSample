This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
----- 
<h5>reference: https://proandroiddev.com/integrating-google-sign-in-into-kotlin-multiplatform-8381c189a891</h5>
-----
Android 사용 시 디바이스에 구글계정이 로그인이 안되어있을 시 동작하지 않으므로 꼭 로그인 후 사용. (현재 코드에는 그거에 대한 예외처리 안되어있음)
IOS ing...
