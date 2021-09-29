package com.zakariya.mzmusicplayer.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.zakariya.mzmusicplayer.R
import com.zakariya.mzmusicplayer.util.Constants.REQ_CODE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity : AppCompatActivity() {
    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//
        if (!hasPermissions(permissions)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                lifecycleScope.launch {
//                    delay(1000L)
                    requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE), REQ_CODE)
//                }

            }
//            else
//            ActivityCompat.requestPermissions(this, permissions, REQ_CODE)
        } else {
//            lifecycleScope.launch(Dispatchers.Main) {
//////                delay(1000L)
////
//                val intent=   Intent(this@StartActivity, MainActivity::class.java)
//                startActivity(intent)
//                this@StartActivity.finish()
//            }
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
                        val intent=    Intent(this@StartActivity, MainActivity::class.java)
                        this@StartActivity.finish()
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(
                        this@StartActivity,
                        "Please Grant all permissions to continue",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                this.finishAffinity()
            }

            else -> {
                Toast.makeText(this@StartActivity, "Something went wrong", Toast.LENGTH_SHORT)
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
//    fun checkPermission(permission: String, granted: () -> Unit) {
//        val mContext = this ?: return
//        val options = Permissions.Options()
//        options.setSettingsDialogMessage("Вы навсегда запретили приложению доступ к геопозиции! Разрешите приложению использовать ваши геоданные в настройках!")
//        options.setSettingsText("Настройки")
//        options.setSettingsDialogTitle("Требуются разрешения")
//        options.setCreateNewTask(true)
//        Permissions.check(mContext, arrayOf(permission), null, options, object : PermissionHandler() {
//            override fun onGranted() {
//                granted()
//            }
//        })
//    }
}
