package com.phongbm.phonecallformymom

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by PhongBM on 08/16/2020
 */

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerListeners()
        checkPermissionCallPhone()
    }

    private fun registerListeners() {
        btnCheckAccountBalance.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnCheckAccountBalance -> checkAccountBalance()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            ActivityRequestCode.PHONE_CALL -> {
                if (!isPermissionGrantedCallPhone()) {
                    finish()
                }
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    private fun checkPermissionCallPhone() {
        if (!isPermissionGrantedCallPhone()) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), ActivityRequestCode.PHONE_CALL)
        }
    }

    private fun isPermissionGrantedCallPhone() =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED

    private fun checkAccountBalance() {
        val uri = Uri.parse("tel:" + Uri.encode("*101#"))
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = uri
        }
        startActivity(intent)
    }

}