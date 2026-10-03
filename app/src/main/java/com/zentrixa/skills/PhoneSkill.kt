package com.zentrixa.skills

import android.content.Context
import com.zentrixa.phone.PhoneActions

class PhoneSkill(context: Context) : LocalSkill {
    private val actions = PhoneActions(context)

    override fun matches(text: String): Boolean {
        val t = text.lowercase()
        return t == "open settings" || t.startsWith("call ") || t.startsWith("message ")
    }

    override fun run(text: String): String {
        val t = text.trim()
        return when {
            t.equals("open settings", true) -> {
                actions.openSettings()
                "Opening settings."
            }
            t.startsWith("call ", true) -> {
                val number = t.substringAfter(" ").trim()
                if (number.isBlank()) "Tell me the phone number." else {
                    actions.call(number)
                    "Opening the dialer for $number."
                }
            }
            t.startsWith("message ", true) ->
                "Messaging skill is ready; use the format: message NUMBER TEXT."
            else -> "I can't perform that phone action yet."
        }
    }
}
