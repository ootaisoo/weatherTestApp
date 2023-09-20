package com.example.gismeteoapitestapp.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.widget.Toast
import com.example.gismeteoapitestapp.R
import com.google.gson.Gson
import java.io.File


class CachingRepositoryImpl(
    private val context: Context,
    private val gson: Gson
) : CachingRepository {

    companion object {
        const val FORECAST_FILE = "forecast.txt"
        const val REQUESTS_FILE = "requests.txt"
        const val ERRORS_LOG_FILE = "requests.txt"
    }

    override fun copyToClipboard(text: String) {
        val label = context.getString(R.string.forecast)
        val message = context.getString(R.string.text_copied)
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText(label, text))

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun saveToDownloads(text: String) {
        val file = File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS),
            FORECAST_FILE
        )

        file.writeText(gson.toJson(text))
    }

    override fun saveRequestsInfo(requestsInfo: String) {
        val file = File(context.filesDir, REQUESTS_FILE)
        file.appendText(requestsInfo)
    }

    override fun fetchRequestsInfo(): String {
        val file = File(context.filesDir, REQUESTS_FILE)
        return file.readText()
    }
}