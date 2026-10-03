package com.zentrixa.skills
import android.content.Context
class AppSkill(private val context: Context) : LocalSkill {
    override fun matches(text: String): Boolean = text.lowercase().startsWith("open ")
    override fun run(text: String): String {
        val requested = text.substringAfter("open ").trim().lowercase()
        if (requested == "settings") {
            context.startActivity(android.content.Intent(android.provider.Settings.ACTION_SETTINGS).addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK))
            return "Opening settings."
        }
        val known = mapOf("youtube" to "com.google.android.youtube", "chrome" to "com.android.chrome", "whatsapp" to "com.whatsapp")
        val pkg = known[requested] ?: return "I don't have an app mapping for $requested yet."
        val launch = context.packageManager.getLaunchIntentForPackage(pkg) ?: return "That app isn't installed."
        launch.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(launch)
        return "Opening $requested."
    }
}