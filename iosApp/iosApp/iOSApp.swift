import SwiftUI
import ComposeApp
import KakaoSDKCommon

@main
struct iOSApp: App {
   init() {
           // Kakao SDK 초기화
           KoinHelperKt.doInitKoin()
           KakaoSDK.initSDK(appKey: "NATIVE_APP_KEY")
       }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
