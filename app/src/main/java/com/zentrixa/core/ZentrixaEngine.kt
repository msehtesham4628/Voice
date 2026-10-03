package com.zentrixa.core

import com.zentrixa.memory.MemoryStore
import com.zentrixa.skills.LocalSkillRouter

class ZentrixaEngine(private val memory: MemoryStore) {
    private val router = LocalSkillRouter(memory)

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
            "I can talk, remember local context, use phone skills, and grow through local skills."
        else ->
            "I heard you. The local model runtime is the next layer; no cloud AI is required."
    }
}
