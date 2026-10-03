package com.zentrixa.calls
import android.telecom.Call
import android.telecom.InCallService

class ZentrixaInCallService : InCallService() {
    override fun onCallAdded(call: Call) { super.onCallAdded(call) }
    override fun onCallRemoved(call: Call) { super.onCallRemoved(call) }
}
