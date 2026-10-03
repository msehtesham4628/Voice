package com.zentrixa.core

import android.content.Context
import com.zentrixa.memory.MemoryStore
import com.zentrixa.skills.LocalSkillRouter

class ZentrixaEngine(context: Context) {
    private val memory = MemoryStore(context)
    private val router = LocalSkillRouter(memory, context)

    fun handle(text: String): String {
        memory.add("user", text)
        val result = router.route(text) ?: fallback(text)
        memory.add("assistant", result)
        return result
    }

    private fun fallback(text: String): String = when {
        text.lowercase().contains("who are you") ->
            "I'm Zentrixa, your private local assistant. I run without Gemini or OpenAI."
        text.lowercase().contains("what can you do") ->
            "I can talk, remember local context, and use installed local phone skills."
        else ->
            "I heard you. Connect a GGUF local model to the inference layer for full natural-language reasoning."
    }
}
