package com.example.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.IUserRepository
import com.example.domain.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Showcase for screen creation and usage
@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val userRepository: IUserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ProfileEffect>()
    val effect = _effect.asSharedFlow()

    init {
        observeUser()
    }

    fun handleIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.RefreshUser -> refresh()

            is ProfileIntent.OnBackClicked -> {
                viewModelScope.launch { _effect.emit(ProfileEffect.NavigateBack) }
            }
        }
    }

    private fun observeUser() {
        getUserProfileUseCase()
            .onEach { user ->
                _state.update { it.copy(user = user, isLoading = false) }
            }
            .launchIn(viewModelScope)
    }

    private fun refresh() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                userRepository.refreshUser()
            } catch (e: Exception) {
                _effect.emit(ProfileEffect.ShowError("Failed to update"))
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}
