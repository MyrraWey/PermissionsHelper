package com.muravyovdmitr.permissions

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.muravyovdmitr.permissions.permission_model.PermissionModel
import com.muravyovdmitr.permissions.permission_model.PermissionRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

	companion object {
		private val SETTINGS_REQUEST_CODE = 101

		private val READ_CALENDAR_PERMISSION = Manifest.permission.READ_CALENDAR
		private val READ_CONTACTS_PERMISSION = Manifest.permission.READ_CONTACTS
		private val WRITE_CALENDAR_PERMISSION = Manifest.permission.WRITE_CONTACTS
		private val ACCESS_FINE_LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
		private val RECEIVE_SMS_PERMISSION = Manifest.permission.RECEIVE_SMS
		private val CAMERA_PERMISSION = Manifest.permission.CAMERA
	}

	private val permissionModel: PermissionModel = PermissionModelProvider.permissionModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		permissionModel.requestPermissions(PermissionRequest(READ_CALENDAR_PERMISSION, CAMERA_PERMISSION) {})
		permissionModel.requestPermissions(PermissionRequest(CAMERA_PERMISSION, WRITE_CALENDAR_PERMISSION) {})
		permissionModel.requestPermissions(PermissionRequest(CAMERA_PERMISSION, READ_CONTACTS_PERMISSION) {})
		configureViews()
	}

	override fun onStart() {
		super.onStart()

		permissionModel.requestPermissions(PermissionRequest(READ_CALENDAR_PERMISSION, CAMERA_PERMISSION) {})
		permissionModel.requestPermissions(PermissionRequest(CAMERA_PERMISSION, WRITE_CALENDAR_PERMISSION) {})
		permissionModel.requestPermissions(PermissionRequest(CAMERA_PERMISSION, READ_CONTACTS_PERMISSION) {})

		updatePermissionsStatus()
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		when (requestCode) {
			SETTINGS_REQUEST_CODE -> showMessage("Returned From App Settings")
		}
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)

		updatePermissionsStatus()
	}

	private fun configureViews() {
		bAppSettings?.setOnClickListener {
			openApplicationSettings(SETTINGS_REQUEST_CODE)
		}

		bSingleRequest?.setOnClickListener {
			permissionModel.requestPermissions(
					PermissionRequest(READ_CALENDAR_PERMISSION) { requestResult ->
						if (requestResult) {
							showMessage("Permission granted")
						} else {
							showMessage("Permission denied")
						}
					})
		}

		nMultiplyRequest?.setOnClickListener {
			permissionModel.requestPermissions(
					PermissionRequest(READ_CONTACTS_PERMISSION, ACCESS_FINE_LOCATION_PERMISSION) { requestResult ->
						if (requestResult) {
							showMessage("Permission granted")
						} else {
							showMessage("Permission denied")
						}
					})
		}
	}

	@SuppressLint("SetTextI18n")
	private fun updatePermissionsStatus() {
		tvPermissionsStatus?.text =
				getPermissionStatusText(READ_CALENDAR_PERMISSION) + "\n" +
						getPermissionStatusText(READ_CONTACTS_PERMISSION) + "\n" +
						getPermissionStatusText(ACCESS_FINE_LOCATION_PERMISSION) + "\n" +
						getPermissionStatusText(RECEIVE_SMS_PERMISSION) + "\n"
	}

	fun getPermissionStatusText(permission: String) =
			"$permission: ${if (isPermissionGranted(permission)) "GRANTED" else "DENIED"}"
}
