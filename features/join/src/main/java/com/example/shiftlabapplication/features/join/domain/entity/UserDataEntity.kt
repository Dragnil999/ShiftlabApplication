package com.example.shiftlabapplication.features.join.domain.entity

import java.util.Date

data class UserDataEntity(
    val name: String = "",
    val surname: String = "",
    val birthDate: Date = Date(),
    val password: String = "",
)
