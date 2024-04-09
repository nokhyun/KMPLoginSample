package di

import platform.UIKit.UIDevice

actual class Platform {
    actual val name: String
        get() = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}