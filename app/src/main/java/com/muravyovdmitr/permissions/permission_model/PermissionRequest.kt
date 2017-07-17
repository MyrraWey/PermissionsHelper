package com.muravyovdmitr.permissions.permission_model


/**
 * Created by Dima Muravyov
 * Date: 06.07.2017
 */
class PermissionRequest(vararg val permissions: String, val permissionResultResultListener: (Boolean) -> Unit)
