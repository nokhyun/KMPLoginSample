import SwiftUI
import ComposeApp
//import KakaoSDKCommon
//import GoogleSignIn

@main
struct iOSApp: App {

   init() {
       // Kakao SDK 초기화
//       KakaoSDK.initSDK(appKey: "NATIVE_APP_KEY")
       // Koin
       KoinHelperKt.doInitKoin()
       // LOG
       NapierProxyKt.debugBuild()
    }
	var body: some Scene {
		WindowGroup {
            ContentView().onOpenURL(perform: { url in
                GIDSignIn.sharedInstance.handle(url)
            })
		}
	}
}
