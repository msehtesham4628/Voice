package com.zentrixa.model
import android.app.Activity
import android.content.Intent
object ModelPicker {
    const val REQUEST_CODE = 7101
    fun open(activity: Activity) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "application/octet-stream"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        activity.startActivityForResult(intent, REQUEST_CODE)
    }
}