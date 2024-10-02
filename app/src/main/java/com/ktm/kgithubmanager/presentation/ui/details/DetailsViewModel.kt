package com.ktm.kgithubmanager.presentation.ui.details

import androidx.lifecycle.viewModelScope
import com.ktm.kgithubmanager.domain.entity.User
import com.ktm.kgithubmanager.domain.repository.UserRepository
import com.ktm.kgithubmanager.domain.repository.result.Result
import com.ktm.kgithubmanager.domain.usecase.UserUseCase
import com.ktm.kgithubmanager.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    userRepository: UserRepository
) : BaseViewModel() {

    private val userUseCase = UserUseCase(userRepository)

    private val _detailsUiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Default)
    val detailsUiState: StateFlow<DetailsUiState> = _detailsUiState

    fun fetchUserDetails(userName: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                // make sure that this action runs in background thread
                userUseCase.fetchUserDetails(userName)
            }
            when (result) {
                is Result.Success -> {
                    _detailsUiState.value = DetailsUiState.Success(result.data)
                }

                is Result.Error -> {
                }
            }
        }
    }
}

sealed class DetailsUiState {
    data object Default : DetailsUiState()

    data class Success(
        val user: User
    ) : DetailsUiState()

    data class Error(
        val errorCode: Int? = null,
        val throwable: Throwable? = null
    ) : DetailsUiState()
}