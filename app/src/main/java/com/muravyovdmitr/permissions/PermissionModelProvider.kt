package com.muravyovdmitr.permissions

import com.muravyovdmitr.permissions.permission_model.PermissionModelImpl


/**
 * Created by Dima Muravyov
 * Date: 06.07.2017
 */
object PermissionModelProvider {
	val permissionModel by lazy { PermissionModelImpl() }
}