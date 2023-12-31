package com.example.gismeteoapitestapp.data.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.gismeteoapitestapp.R
import com.example.gismeteoapitestapp.model.ForecastResponse
import com.google.gson.Gson
import java.io.File


class CachingRepositoryImpl(
    private val context: Context,
    private val gson: Gson
) : CachingRepository {

    companion object {
        const val FORECAST_FILE = "forecast.txt"
        const val REQUESTS_FILE = "requests.txt"
    }

    override fun copyToClipboard(forecast: ForecastResponse) {
        val text = gson.toJson(forecast)
        val label = context.getString(R.string.forecast)
        val message = context.getString(R.string.text_copied)
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText(label, text))

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun saveToDownloads(forecast: ForecastResponse) {
        val text = gson.toJson(forecast)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            saveToExternalStorage(text)
        } else {
            saveFileUsingMediaStore(text)
        }
    }

    private fun saveToExternalStorage(text: String) {
        val file = File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS),
            FORECAST_FILE
        )

        file.writeText(text)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun saveFileUsingMediaStore(text: String) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, FORECAST_FILE)
            put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }
        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
        if (uri != null) {
            resolver.openOutputStream(uri)?.let {
                it.write(text.encodeToByteArray())
                it.close()
            }
        }
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