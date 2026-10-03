package com.zentrixa.voice

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class TtsController(context: Context) : TextToSpeech.OnInitListener {
    private val tts = TextToSpeech(context, this)
    private var ready = false
    override fun onInit(status: Int) {
        ready = status == TextToSpeech.SUCCESS
        if (ready) tts.language = Locale.getDefault()
    }
    fun speak(text: String) { if (ready) tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "zentrixa-response") }
    fun shutdown() { tts.shutdown() }
}
