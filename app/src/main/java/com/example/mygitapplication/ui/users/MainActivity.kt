package com.example.mygitapplication.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygitapplication.R
import com.example.mygitapplication.app
import com.example.mygitapplication.databinding.ActivityMainBinding
import com.example.mygitapplication.model.UserRepo

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()
    private val userRepo: UserRepo by lazy { app.userRepo }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

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
                    adapter.setData(it)
                },
                onError = {
                    showProgressBar(false)
                    showError(it)
                }
            )
        }
    }

    private fun showProgressBar(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.userListRecyclerView.isVisible = !isLoading
    }

    fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
}