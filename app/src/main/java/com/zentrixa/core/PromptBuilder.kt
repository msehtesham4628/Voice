package com.zentrixa.core
object PromptBuilder {
    const val SYSTEM = "You are Zentrixa, a private personal Android assistant. You are concise, calm, helpful and natural. Prefer safe, explicit actions over silently changing important device state. Never claim to have performed an action unless the tool confirms it. Use local tools when available."
    fun build(user: String, memory: List<Pair<String, String>>): String {
        val history = memory.joinToString("\n") { pair -> pair.first + ": " + pair.second }
        return SYSTEM + "\n\nConversation:\n" + history + "\nUser: " + user + "\nZentrixa:"
    }
}