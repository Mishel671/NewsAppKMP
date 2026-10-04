package com.example.newsapp.common.platform

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class BrowserHelper {

    actual fun launchUrlInBrowser(url: String) {
        val nsUrl = NSURL.URLWithString(url) ?: return
        val application = UIApplication.sharedApplication

        if (application.canOpenURL(nsUrl)) {
            application.openURL(
                url = nsUrl,
                options = emptyMap<Any?, Any>(),
                completionHandler = null
            )
        }
    }
}