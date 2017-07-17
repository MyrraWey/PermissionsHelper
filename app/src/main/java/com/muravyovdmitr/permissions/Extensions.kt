package com.muravyovdmitr.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.support.v4.content.ContextCompat


/**
 * Created by Dima Muravyov
 * Date: 03.07.2017
 */

fun Activity.openApplicationSettings(requestCode: Int) {
	val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + packageName))
	startActivityForResult(intent, requestCode)
}

fun Context.isPermissionGranted(permission: String): Boolean {
	return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}