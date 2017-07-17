package com.muravyovdmitr.permissions.permission_model

import android.content.pm.PackageManager


/**
 * Created by Dima Muravyov
 * Date: 05.07.2017
 */
class PermissionModelImpl : PermissionModel {
	private var permissionsQuery = mutableListOf<PermissionRequest>()
	private var processingRequest = false

	override var permissionActivity: PermissionActivity? = null
		set(value) {
			field = value
			processPermissionsQuery()
		}

	override fun requestPermissions(permissionRequest: PermissionRequest) {
		if (isAllRequestedPermissionsGranted(permissionRequest.permissions)) {
			permissionRequest.permissionResultResultListener(true)
		} else {
			permissionsQuery.add(permissionRequest)
			processPermissionsQuery()
		}
	}

	private fun isAllRequestedPermissionsGranted(permissions: Array<out String>): Boolean =
			permissions
					.filter { permission ->
						!(permissionActivity?.isPermissionGranted(permission) ?: false)
					}
					.isEmpty()

	private fun processPermissionsQuery() {
		permissionActivity?.let {
			if (!processingRequest && permissionsQuery.isNotEmpty()) {
				processingRequest = true

				it.requestPermissions(*permissionsQuery.first().permissions)
			}
		}
	}

	override fun requestResult(permissions: Array<out String>, grantResults: IntArray) {
		var requestResult = true
		grantResults.forEach {
			requestResult = requestResult && it == PackageManager.PERMISSION_GRANTED
		}
		permissionsQuery.removeAt(0).permissionResultResultListener(requestResult)

		processingRequest = false
		processPermissionsQuery()
	}
}
