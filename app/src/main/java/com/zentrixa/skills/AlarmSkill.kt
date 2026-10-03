package com.zentrixa.skills

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock

class AlarmSkill(private val context: Context) : LocalSkill {
    override fun matches(text: String): Boolean =
        Regex("""(?i)^(set )?alarm \d{1,2}:\d{2}(?: (am|pm))?(?: .+)?$""").matches(text.trim())

    override fun run(text: String): String {
        val m = Regex("""(?i)^(?:set )?alarm (\d{1,2}):(\d{2})(?: (am|pm))?(?: (.+))?$""")
            .matchEntire(text.trim()) ?: return "Use: alarm 7:00 am"
        var hour = m.groupValues[1].toInt()
        val minute = m.groupValues[2].toInt()
        val ampm = m.groupValues[3]
        if (ampm.equals("pm", true) && hour < 12) hour += 12
        if (ampm.equals("am", true) && hour == 12) hour = 0
        val label = m.groupValues[4].ifBlank { "Zentrixa alarm" }
        context.startActivity(Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minute)
            putExtra(AlarmClock.EXTRA_MESSAGE, label)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
        return "Opening the alarm setup for %02d:%02d.".format(hour, minute)
    }
}
