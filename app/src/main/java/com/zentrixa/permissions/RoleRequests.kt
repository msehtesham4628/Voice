package com.zentrixa.permissions
import android.app.Activity
import android.app.role.RoleManager

object RoleRequests {
    fun requestCallScreening(activity: Activity, requestCode: Int) {
        val role = activity.getSystemService(RoleManager::class.java) ?: return
        if (role.isRoleAvailable(RoleManager.ROLE_CALL_SCREENING) && !role.isRoleHeld(RoleManager.ROLE_CALL_SCREENING))
            activity.startActivityForResult(role.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING), requestCode)
    }
    fun requestDialer(activity: Activity, requestCode: Int) {
        val role = activity.getSystemService(RoleManager::class.java) ?: return
        if (role.isRoleAvailable(RoleManager.ROLE_DIALER) && !role.isRoleHeld(RoleManager.ROLE_DIALER))
            activity.startActivityForResult(role.createRequestRoleIntent(RoleManager.ROLE_DIALER), requestCode)
    }
}
