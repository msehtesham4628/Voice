package com.zentrixa.phone

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.AlarmClock
import android.provider.Settings

class PhoneActions(private val context: Context) {
    fun openSettings(): Boolean {
        context.startActivity(Intent(Settings.ACTION_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        return true
    }

    fun openApp(packageName: String): Boolean {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName) ?: return false
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        return true
    }

    fun call(number: String): Boolean {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        return true
    }

    fun message(number: String, body: String): Boolean {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$number")).apply {
            putExtra("sms_body", body)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        return true
    }

    fun alarm(hour: Int, minute: Int, label: String): Boolean {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minute)
            putExtra(AlarmClock.EXTRA_MESSAGE, label)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        return true
    }
}
