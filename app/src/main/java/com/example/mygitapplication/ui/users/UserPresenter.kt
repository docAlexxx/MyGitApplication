package com.example.mygitapplication.ui.users

import com.example.mygitapplication.model.UserRepo

class UserPresenter(private val userRepo: UserRepo):UserContract.Presenter {

    private var view: UserContract.View? = null

    override fun attach(view: UserContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        view?.showProgressBar(true)
        userRepo.getUsers(
            onSuccess = {
                view?.showProgressBar(false)
                view?.showUsers(it)
            },
            onError = {
                view?.showProgressBar(false)
                view?.showError(it)
            }
        )
    }
}