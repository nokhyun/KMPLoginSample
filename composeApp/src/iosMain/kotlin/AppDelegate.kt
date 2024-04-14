import cocoapods.GoogleSignIn.GIDSignIn
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDelegateProtocol
import platform.darwin.NSObject

/**
 *  Test
 * */
class AppDelegate: NSObject(), UIApplicationDelegateProtocol {
    @OptIn(ExperimentalForeignApi::class)
    override fun application(app: UIApplication, openURL: NSURL, options: Map<Any?, *>): Boolean {
        return GIDSignIn.sharedInstance.handleURL(url = openURL)
    }
}