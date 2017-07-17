package com.muravyovdmitr.permissions

import android.app.Application
import android.os.StrictMode


/**
 * Created by Dima Muravyov
 * Date: 18.07.2017
 */
class App : Application() {

	override fun onCreate() {
		super.onCreate()
		configureStrictMode()
	}

	private fun configureStrictMode() {
		StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
				.detectDiskReads()
				.detectDiskWrites()
				.detectAll()
				.penaltyLog()
				.build())
		StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects()
				.detectLeakedClosableObjects()
				.penaltyLog()
				.penaltyDeath()
				.build())

	}
}