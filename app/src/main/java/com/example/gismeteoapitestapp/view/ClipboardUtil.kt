package com.example.gismeteoapitestapp.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import com.example.gismeteoapitestapp.R

object ClipboardUtil {

    fun Context.copyTextToClipboard(text: String) {
        val label = getString(R.string.forecast)
        val message = getString(R.string.text_copied)
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText(label, text))

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}