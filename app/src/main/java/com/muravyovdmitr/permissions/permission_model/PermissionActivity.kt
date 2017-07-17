package com.muravyovdmitr.permissions.permission_model


/**
 * Created by Dima Muravyov
 * Date: 09.07.2017
 */
interface PermissionActivity {

	fun isPermissionGranted(permission: String): Boolean

	fun requestPermissions(vararg permissions: String)
}