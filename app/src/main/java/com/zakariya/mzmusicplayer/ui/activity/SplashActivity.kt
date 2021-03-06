package com.zakariya.mzmusicplayer.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.zakariya.mzmusicplayer.R
import com.zakariya.mzmusicplayer.util.Constants.REQ_CODE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (!hasPermissions(permissions)) {
            ActivityCompat.requestPermissions(this, permissions, REQ_CODE)
        } else {
            lifecycleScope.launch(Dispatchers.Main) {
//                delay(1000L)

             val intent=   Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                this@SplashActivity.finish()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {

            REQ_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    lifecycleScope.launch() {
                    val intent=    Intent(this@SplashActivity, MainActivity::class.java)
                        this@SplashActivity.finish()
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(
                        this@SplashActivity,
                        "Please Grant all permissions to continue",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                this.finishAffinity()
            }

            else -> {
                Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
                this.finish()
            }
        }
    }

    private fun hasPermissions(permissions: Array<String>): Boolean {
        var hasAllPermissions = true
        for (permission in permissions) {
            val res = this.checkCallingOrSelfPermission(permission)
            if (res != PackageManager.PERMISSION_GRANTED) {
                hasAllPermissions = false
            }
        }
        return hasAllPermissions
    }

//    override fun onPause() {
//        super.onPause()
//        finish()
//    }
}