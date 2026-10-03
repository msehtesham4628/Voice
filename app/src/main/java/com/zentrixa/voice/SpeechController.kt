package com.zentrixa.voice

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import java.util.Locale

class SpeechController(private val activity: Activity) {
    private var callback: ((String) -> Unit)? = null
    private var error: ((String) -> Unit)? = null

    fun listen(onResult: (String) -> Unit, onError: (String) -> Unit) {
        callback = onResult; error = onError
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to Zentrixa")
        }
        activity.startActivityForResult(intent, REQUEST_CODE)
    }
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (requestCode != REQUEST_CODE) return false
        if (resultCode == Activity.RESULT_OK) {
            val value = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
            if (!value.isNullOrBlank()) callback?.invoke(value) else error?.invoke("I didn't catch that.")
        } else error?.invoke("Voice input was cancelled.")
        return true
    }
    fun shutdown() { callback = null; error = null }
    companion object { const val REQUEST_CODE = 4101 }
}
