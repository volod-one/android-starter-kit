package com.example.data.user.mapper

import com.example.data.user.local.entity.UserEntity
import com.example.data.user.remote.model.UserDto
import com.example.domain.model.User

internal fun UserDto.toEntity(): UserEntity = UserEntity(
    id = this.id,
    name = this.username,
    email = this.email
)

internal fun UserEntity.toDomain(): User = User(
    id = this.id,
    name = this.name,
    email = this.email,
)
