package com.zentrixa.calls
import android.telecom.Call
import android.telecom.CallScreeningService

class ZentrixaCallScreeningService : CallScreeningService() {
    override fun onScreenCall(callDetails: Call.Details) {
        respondToCall(callDetails, CallResponse.Builder().setDisallowCall(false).build())
    }
}
