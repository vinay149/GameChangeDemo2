package com.example.gamechangedemo2

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat


object BiometricUtils {
    val isBiometricPromptEnabled: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P


    val isSdkVersionSupported: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M


    fun isHardwareSupported(context: Context?): Boolean {
        val fingerprintManager = context?.let { FingerprintManagerCompat.from(it) }
        return fingerprintManager!!.isHardwareDetected
    }


    fun isFingerprintAvailable(context: Context?): Boolean {
        val fingerprintManager = context?.let { FingerprintManagerCompat.from(it) }
        return fingerprintManager!!.hasEnrolledFingerprints()
    }

    fun isPermissionGranted(context: Context?): Boolean {
        return context?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.USE_FINGERPRINT) } ==
                PackageManager.PERMISSION_GRANTED
    }
}