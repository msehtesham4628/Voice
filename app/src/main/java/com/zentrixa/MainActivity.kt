package com.zentrixa

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.zentrixa.core.ZentrixaEngine
import com.zentrixa.voice.SpeechController
import com.zentrixa.voice.TtsController
import com.zentrixa.model.ModelPicker
import com.zentrixa.model.ModelRepository

class MainActivity : Activity() {
    private lateinit var engine: ZentrixaEngine
    private lateinit var speech: SpeechController
    private lateinit var tts: TtsController
    private lateinit var input: EditText
    private lateinit var chat: TextView
    private lateinit var modelRepository: ModelRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        engine = ZentrixaEngine(this)
        speech = SpeechController(this)
        tts = TtsController(this)
        modelRepository = ModelRepository(this)
        input = findViewById(R.id.input)
        chat = findViewById(R.id.chat)
        findViewById<Button>(R.id.send).setOnClickListener { send(input.text.toString()) }
        findViewById<Button>(R.id.mic).setOnClickListener { listen() }
        findViewById<Button>(R.id.model).setOnClickListener { ModelPicker.open(this) }
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), 10)
        append("Zentrixa", "Online. Local-first mode is ready.")
    }

    private fun send(text: String) {
        if (text.isBlank()) return
        input.setText("")
        append("You", text)
        val response = engine.handle(text)
        append("Zentrixa", response)
        tts.speak(response)
    }

    private fun listen() { speech.listen(::send) { append("Zentrixa", it) } }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ModelPicker.REQUEST_CODE && resultCode == RESULT_OK && data?.data != null) {
            val file = modelRepository.import(data.data!!, contentResolver)
            append("Zentrixa", "Imported local model: " + file.name)
            return
        }
        if (!speech.onActivityResult(requestCode, resultCode, data)) super.onActivityResult(requestCode, resultCode, data)
    }

    private fun append(who: String, text: String) { chat.append("$who: $text\n\n") }

    override fun onDestroy() {
        tts.shutdown()
        speech.shutdown()
        super.onDestroy()
    }
}
