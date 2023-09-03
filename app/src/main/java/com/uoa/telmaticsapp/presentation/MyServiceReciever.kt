package com.uoa.telmaticsapp.presentation

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver


class MyServiceReciever(handler: Handler?): ResultReceiver(handler) {

    private var receiver: Receiver? = null

    // Setter for assigning the receiver
    fun setReceiver(receiver: Receiver?) {
        this.receiver = receiver
    }

    // Defines our event interface for communication
    interface Receiver {
        fun onReceiveResult(resultCode: Int, resultData: Bundle?)
    }

    // Delegate method which passes the result to the receiver if the receiver has been assigned
    protected override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        if (receiver != null) {
            receiver!!.onReceiveResult(resultCode, resultData)
        }
    }
}