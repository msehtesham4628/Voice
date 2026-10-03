package com.zentrixa.memory

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

data class MemoryMessage(val role: String, val content: String, val time: Long)

class MemoryStore(context: Context) {
    private val prefs = context.getSharedPreferences("zentrixa_memory", Context.MODE_PRIVATE)

    fun add(role: String, content: String) {
        val all = JSONArray(prefs.getString("messages", "[]"))
        all.put(JSONObject().apply {
            put("role", role); put("content", content); put("time", System.currentTimeMillis())
        })
        while (all.length() > 100) {
            val trimmed = JSONArray()
            for (i in 1 until all.length()) trimmed.put(all.getJSONObject(i))
            all.clear()
            for (i in 0 until trimmed.length()) all.put(trimmed.getJSONObject(i))
        }
        prefs.edit().putString("messages", all.toString()).apply()
    }

    fun recent(limit: Int = 20): List<MemoryMessage> {
        val all = JSONArray(prefs.getString("messages", "[]"))
        val start = (all.length() - limit).coerceAtLeast(0)
        return buildList {
            for (i in start until all.length()) {
                val x = all.getJSONObject(i)
                add(MemoryMessage(x.getString("role"), x.getString("content"), x.getLong("time")))
            }
        }
    }

    fun clear() { prefs.edit().remove("messages").apply() }
}
