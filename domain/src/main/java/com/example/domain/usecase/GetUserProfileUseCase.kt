package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving the current user profile.
 *
 * Encapsulates business logic for accessing user data from the repository.
 */
class GetUserProfileUseCase @Inject constructor(
    private val repository: IUserRepository
) {

    /**
     * Executes the use case.
     *
     * @return Stream of user data.
     */
    operator fun invoke(): Flow<User> {
        return repository.getUserProfile()
    }
}