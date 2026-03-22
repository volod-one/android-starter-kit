package com.example.features.profile

import com.example.domain.model.User

// TODO: Showcase for screen creation and usage
// What we can see
internal data class ProfileState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null,
)

// What we can do
internal sealed interface ProfileIntent {

    data object RefreshUser : ProfileIntent
    data object OnBackClicked : ProfileIntent
}

internal sealed interface ProfileEffect {
    data object NavigateBack : ProfileEffect
    data class ShowError(val message: String) : ProfileEffect
}
