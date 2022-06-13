package com.example.mygitapplication

interface UserRepo {
    fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}