package com.example.newsapp.common.platform

import android.content.Context
import android.content.Intent
import android.net.Uri

actual class BrowserHelper(
    private val context: Context
) {

    actual fun launchUrlInBrowser(url: String) {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}