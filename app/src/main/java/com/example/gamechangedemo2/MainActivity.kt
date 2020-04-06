package com.example.gamechangedemo2

import android.Manifest
import android.Manifest.permission.USE_FINGERPRINT
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.Executor


class MainActivity : AppCompatActivity() {

    val PERMISSION_REQUEST_CODE = 10;
    lateinit var loginButton : Button
    lateinit var excutor:Executor
    lateinit var biomatricPrompt:BiometricPrompt
    lateinit var promtInfo:BiometricPrompt.PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton = login_button
        excutor = ContextCompat.getMainExecutor(this)
        promtInfo = fingerAuthenitcation()
        loginButton.setOnClickListener {
            biomatricPrompt.authenticate(promtInfo)
        }
    }

    fun fingerAuthenitcation():BiometricPrompt.PromptInfo{
        biomatricPrompt = BiometricPrompt(this, excutor, object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {

     super.onAuthenticationError(errorCode, errString)
                Toast.makeText(applicationContext, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(applicationContext, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                navigateToListActivity()
            }
        })
        promtInfo =  BiometricPrompt.PromptInfo.Builder()
            .setTitle("BioMatric login")
            .setSubtitle("login it")
            .setNegativeButtonText("dummy text")
            .build()
        return promtInfo
    }

    fun navigateToListActivity(){
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}
