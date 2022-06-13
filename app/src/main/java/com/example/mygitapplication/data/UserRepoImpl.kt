package com.example.mygitapplication.data

import android.os.Handler
import android.os.Looper
import com.example.mygitapplication.model.User
import com.example.mygitapplication.model.UserRepo

class UserRepoImpl: UserRepo {
    private val DATA_LOADING_DELAY = 1000L

    private val userData: List<User> = listOf(
        User("wycats", 4, "https://avatars.githubusercontent.com/u/4?v=4", "User"),
        User("vanpelt", 17, "https://avatars.githubusercontent.com/u/17?v=4", "User"),
        User("caged", 25, "https://avatars.githubusercontent.com/u/25?v=4", "User"),
        User("kevwil", 35, "https://avatars.githubusercontent.com/u/35?v=4","User"),
        User("errfree", 44, "https://avatars.githubusercontent.com/u/44?v=4","Organization"),
    )

    override fun getUsers(onSuccess: (List<User>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(userData)
        }, DATA_LOADING_DELAY)
    }

}