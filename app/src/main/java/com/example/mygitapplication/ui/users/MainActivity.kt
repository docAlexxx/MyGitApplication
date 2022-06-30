package com.example.mygitapplication.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygitapplication.R
import com.example.mygitapplication.app
import com.example.mygitapplication.databinding.ActivityMainBinding
import com.example.mygitapplication.model.User
import com.example.mygitapplication.model.UserRepo

class MainActivity : AppCompatActivity(), UserContract.View {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()
    private val userRepo: UserRepo by lazy { app.userRepo }

    private lateinit var presenter: UserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        presenter.attach(this)

    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun initViews() {
        showProgressBar(false)
        initRefreshButton()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.userListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.userListRecyclerView.adapter = adapter
    }

    private fun initRefreshButton() {
        binding.refreshButton.setOnClickListener {

            showProgressBar(true)
            userRepo.getUsers(
                onSuccess = {
                    showProgressBar(false)
                    showUsers(it)
                },
                onError = {
                    showProgressBar(false)
                    showError(it)
                }
            )
        }
    }

    override fun showUsers(users: List<User>) {
        adapter.setData(users)
    }

    override fun showProgressBar(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.userListRecyclerView.isVisible = !isLoading
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
}