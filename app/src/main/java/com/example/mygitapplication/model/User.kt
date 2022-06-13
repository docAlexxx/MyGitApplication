package com.example.mygitapplication.model

data class User(
    val login: String,
    val id: Long,
    val avatarUrl: String,
    val type: String
)