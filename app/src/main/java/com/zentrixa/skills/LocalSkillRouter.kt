package com.zentrixa.skills

import com.zentrixa.memory.MemoryStore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface LocalSkill { fun matches(text: String): Boolean; fun run(text: String): String }

class LocalSkillRouter(memory: MemoryStore) {
    private val skills = listOf(GreetingSkill(), TimeSkill(), MemorySkill(memory))
    fun route(text: String): String? = skills.firstOrNull { it.matches(text) }?.run(text)
}

private class GreetingSkill : LocalSkill {
    override fun matches(text: String) = text.trim().lowercase() in setOf("hi","hello","hey","hey zentrixa")
    override fun run(text: String) = "Hello. Zentrixa is online."
}
private class TimeSkill : LocalSkill {
    override fun matches(text: String) = text.lowercase().contains("what time") || text.trim().lowercase() == "time"
    override fun run(text: String) = "It is " + SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date()) + "."
}
private class MemorySkill(private val memory: MemoryStore) : LocalSkill {
    override fun matches(text: String) = text.lowercase().startsWith("remember ") || text.trim().lowercase() == "what do you remember"
    override fun run(text: String): String {
        if (text.lowercase().startsWith("remember ")) {
            memory.add("memory", text.substringAfter("remember ").trim())
            return "I'll keep that in local memory."
        }
        val recent = memory.recent(8)
        return if (recent.isEmpty()) "I don't have local memory yet."
        else recent.joinToString("; ") { it.role + ": " + it.content }
    }
}
