package com.example.mygitapplication.ui.users

import com.example.mygitapplication.model.User

interface UserContract {

    interface View {
        fun showUsers(users: List<User>)
        fun showProgress(inProgress: Boolean)
        fun showError(throwable: Throwable)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh()
    }
}