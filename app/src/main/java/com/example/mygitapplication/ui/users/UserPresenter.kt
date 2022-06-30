package com.example.mygitapplication.ui.users

import com.example.mygitapplication.model.User
import com.example.mygitapplication.model.UserRepo

class UserPresenter(private val userRepo: UserRepo):UserContract.Presenter {

    private var view: UserContract.View? = null

    private var usersList: List<User>? = null
    private var inProgress: Boolean = false

    override fun attach(view: UserContract.View) {
        this.view = view
        view.showProgressBar(inProgress)
        usersList?.let { view.showUsers(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        view?.showProgressBar(true)
        inProgress = true
        userRepo.getUsers(
            onSuccess = {
                view?.showProgressBar(false)
                view?.showUsers(it)
                usersList = it
                inProgress = false
            },
            onError = {
                view?.showProgressBar(false)
                view?.showError(it)
                inProgress = false
            }
        )
    }
}