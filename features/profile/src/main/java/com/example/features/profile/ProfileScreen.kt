package com.example.features.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

// TODO: Showcase for screen creation and usage
@Composable
fun ProfileScreen(
    onBack: () -> Unit,
) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ProfileEffect.NavigateBack -> {
                    onBack()
                }

                is ProfileEffect.ShowError -> {
                    // TODO
                }
            }
        }
    }

    ProfileContent(
        state = state,
        onIntent = viewModel::handleIntent
    )
}

@Composable
private fun ProfileContent(
    state: ProfileState,
    onIntent: (ProfileIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        state.user?.let { user ->
            Text(text = "Name: ${user.name}", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Email: ${user.email}")
        }

        Button(onClick = { onIntent(ProfileIntent.RefreshUser) }) {
            Text("Refresh Profile")
        }
    }
}
