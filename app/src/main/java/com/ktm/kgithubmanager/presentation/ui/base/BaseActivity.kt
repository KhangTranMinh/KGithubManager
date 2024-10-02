package com.ktm.kgithubmanager.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import com.ktm.kgithubmanager.presentation.util.Logger

abstract class BaseActivity : AppCompatActivity(), DefaultLifecycleObserver {

    protected val logger = Logger.get(this::class.java.simpleName)

    abstract val loadingView: View
    abstract val mainView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }

    protected fun ensureActivityActive(block: () -> Unit) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            kotlin.runCatching {
                block()
            }.onFailure {
                logger.log("Error happens!", it)
            }
        }
    }

    protected fun showLoading() {
        ensureActivityActive {
            loadingView.isVisible = true
            mainView.isVisible = false
        }
    }

    protected fun hideLoading() {
        ensureActivityActive {
            loadingView.isVisible = false
            mainView.isVisible = true
        }
    }
}