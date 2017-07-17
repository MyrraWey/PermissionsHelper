package com.muravyovdmitr.permissions

import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.muravyovdmitr.permissions.permission_model.PermissionActivity
import com.muravyovdmitr.permissions.permission_model.PermissionModel

/**
 * Created by Dima Muravyov
 * Date: 03.07.2017
 */
abstract class BaseActivity : AppCompatActivity() {

	companion object {
		private val PERMISSIONS_REQUEST_CODE = 1230
	}

	private val permissionModel: PermissionModel = PermissionModelProvider.permissionModel

	override fun onStart() {
		super.onStart()
		permissionModel.permissionActivity = permissionActivity
	}

	override fun onDestroy() {
		permissionModel.permissionActivity = null
		super.onDestroy()
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		when (requestCode) {
			PERMISSIONS_REQUEST_CODE -> permissionModel.requestResult(permissions, grantResults)
		}
	}

	fun showMessage(text: String, duration: Int = Toast.LENGTH_SHORT) {
		Toast.makeText(this, text, duration).show()
	}

	private val permissionActivity = object : PermissionActivity {

		override fun isPermissionGranted(permission: String): Boolean = this@BaseActivity.isPermissionGranted(permission)

		override fun requestPermissions(vararg permissions: String) {
			ActivityCompat.requestPermissions(
					this@BaseActivity,
					permissions,
					PERMISSIONS_REQUEST_CODE)
		}
	}
}