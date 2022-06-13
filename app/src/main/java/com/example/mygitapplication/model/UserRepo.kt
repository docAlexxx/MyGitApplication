package com.example.mygitapplication.model

interface UserRepo {
    fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}