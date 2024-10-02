package com.ktm.kgithubmanager.domain.entity

class User(
    val id: Long = 0L,
    val userName: String = "",
    val avatarUrl: String = "",
    val blogUrl: String = "",
    val location: String = "",
    val followers: Int = 0,
    val following: Int = 0,
)