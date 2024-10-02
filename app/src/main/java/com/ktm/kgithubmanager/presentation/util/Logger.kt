package com.ktm.kgithubmanager.presentation.util

class Logger(private val clazz: String) {

    companion object {
        private const val TAG = "KGithubManager"

        fun get(clazz: String): Logger = Logger(clazz)
    }


    fun log(message: String) {
        android.util.Log.d(TAG, "$clazz | $message")
    }

    fun log(message: String, throwable: Throwable) {
        android.util.Log.e(TAG, "$clazz | $message", throwable)
    }
}