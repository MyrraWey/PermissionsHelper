package com.muravyovdmitr.permissions.permission_model


/**
 * Created by Dima Muravyov
 * Date: 09.07.2017
 */
interface PermissionModel {
	var permissionActivity: PermissionActivity?

	fun requestPermissions(permissionRequest: PermissionRequest)

	fun requestResult(permissions: Array<out String>, grantResults: IntArray)
}