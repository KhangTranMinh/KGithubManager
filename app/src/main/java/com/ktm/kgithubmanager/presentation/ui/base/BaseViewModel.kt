package com.ktm.kgithubmanager.presentation.ui.base

import androidx.lifecycle.ViewModel
import com.ktm.kgithubmanager.presentation.util.Logger

open class BaseViewModel : ViewModel() {

    protected val logger = Logger.get(this::class.java.simpleName)
}