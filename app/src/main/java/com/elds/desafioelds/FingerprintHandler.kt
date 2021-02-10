package com.elds.desafioelds

import android.Manifest
import android.R
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.media.Image
import android.os.CancellationSignal
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat


class FingerprintHandler(val context: Context): FingerprintManager.AuthenticationCallback() {

    private lateinit var cancellationSignal: CancellationSignal
    private var appContext: Context

    init {
        appContext = context
    }

    fun startAuth(
        manager: FingerprintManager,
        cryptoObject: FingerprintManager.CryptoObject?
    ) {
        cancellationSignal = CancellationSignal()
        if (ActivityCompat.checkSelfPermission(
                appContext,
                Manifest.permission.USE_FINGERPRINT
            ) !=
            PackageManager.PERMISSION_GRANTEDhelper.put
        ) {
            return
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null)
    }

    override fun onAuthenticationError(
        errMsgId: Int,
        errString: CharSequence
    ) {
        super.onAuthenticationError(errMsgId,errString)
        Toast.makeText(
            appContext,
            "Authentication error\n$errString",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onAuthenticationHelp(
        helpMsgId: Int,
        helpString: CharSequence
    ) {
        Toast.makeText(
            appContext,
            "Authentication help\n$helpString",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        Toast.makeText(
            appContext,
            "Authentication failed",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onAuthenticationSucceeded(
        result: FingerprintManager.AuthenticationResult?
    ) {
        super.onAuthenticationSucceeded(result)
        Toast.makeText(
            appContext,
            "Authentication succeeded",
            Toast.LENGTH_LONG
        ).show()

        Log.d("Biometrico:",result.toString())
    }
}